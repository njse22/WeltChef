package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.MessageAdapter;
import appmoviles.com.weltchef.db.FCMService;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.FCMMessage;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;
import appmoviles.com.weltchef.util.UtilDomi;
import appmoviles.com.weltchef.view.ChatActivity;

import static android.app.Activity.RESULT_OK;

public class ChatController implements View.OnClickListener, ChildEventListener, AdapterView.OnItemSelectedListener {

    private ChatActivity activity;
    private User user;
    private Uri uri;
    private MessageContainer messageContainer;
    private MessageAdapter adapter;
    private FirebaseDB firebaseDB;
    private Gson gson;

    public ChatController(ChatActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        this.adapter = new MessageAdapter();
        this.gson = new Gson();
        init();
    }

    public void init(){
        activity.getMessagesList().setAdapter(adapter);

        user = (User) activity.getIntent().getExtras().get("user");
        messageContainer = (MessageContainer) activity.getIntent().getExtras().get("messageContainer");
        activity.getSendBtn().setOnClickListener(this);
        activity.getGalBtn().setOnClickListener(this);
        adapter.setUserId(user.getId());

        if(user.isChef()){
            activity.showSpinner();
        }else if (!user.isChef()){
            activity.hideSpinner();
        }

        activity.getSpinnerStatus().setOnItemSelectedListener(this);

        Query query = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_CHATS_BRANCH)
                .child(messageContainer.getId())
                .child("messages");

        query.addChildEventListener(this);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Message message = dataSnapshot.getValue(Message.class);
        adapter.addMessage(message);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendBtn:
                String body = activity.getMessageET().getText().toString();

                String pushId = FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FIREBASE_CHATS_BRANCH)
                        .child(messageContainer.getId()).push().getKey();

                String pushIdMessage = FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FIREBASE_CHATS_BRANCH)
                        .child(messageContainer.getId())
                        .child("messages")
                        .push()
                        .getKey();

                Message message = new Message(
                        uri == null ? Message.TYPE_TEXT : Message.TYPE_IMAGE,
                        pushIdMessage,
                        body,
                        Calendar.getInstance().getTime().getTime(),
                        user.getId()
                );

                FCMMessage fcm = new FCMMessage();
                fcm.setTo("/topics/"+messageContainer.getId());
                fcm.setData(message);
                String json = gson.toJson(fcm);

                new Thread(
                        () -> {
                            HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                            utilDomi.POSTtoFCM(FCMService.API_KEY, json);
                        }
                ).start();

                if(uri != null){
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    storage.getReference()
                            .child(Constants.FIREBASE_CHATS_BRANCH).child(message.getId())
                            .putFile(uri).addOnCompleteListener(
                            task -> {
                                if(task.isSuccessful()){
                                    Log.e(">>","Foto subida con éxito");
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("chats")
                                            .child(messageContainer.getId())
                                            .child("messages")
                                            .child(pushId)
                                            .setValue(message);
                                }
                            }
                    );
                }
                else {

                    FirebaseDatabase.getInstance().getReference()
                            .child(Constants.FIREBASE_CHATS_BRANCH)
                            .child(messageContainer.getId())
                            .child("messages").child(pushIdMessage)
                            .setValue(message);
                }
                activity.hideImage();
                uri = null;
                break;

            case R.id.galBtn:
                Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                gal.setType("image/*");
                activity.startActivityForResult(gal, CameraController.GALLERY_CALLBACK);
                break;
        }
    }

    public void beforePause() {
        //Suscribirme a un topic
        FirebaseMessaging.getInstance().subscribeToTopic(messageContainer.getId()).addOnCompleteListener(
                task->{
                    if(task.isSuccessful()){
                        Log.e(">>>","Suscrito!");
                    }
                }
        );
    }

    public void beforeResume() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(messageContainer.getId());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CameraController.GALLERY_CALLBACK && resultCode == RESULT_OK){
            uri = data.getData();
            File file = new File(UtilDomi.getPath(activity, uri));
            Bitmap image = BitmapFactory.decodeFile(file.toString());
            activity.getMessageIV().setImageBitmap(image);
            activity.showImage();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:

                break;
            case 1:
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.FIREBASE_CHATS_BRANCH)
                        .child(messageContainer.getId())
                        .setValue(null);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
