package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Calendar;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.MessageAdapter;
import appmoviles.com.weltchef.db.FCMService;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.Client;
import appmoviles.com.weltchef.entity.FCMMessage;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;
import appmoviles.com.weltchef.view.ChatActivity;

public class ChatController implements View.OnClickListener, ValueEventListener, ChildEventListener {

    private ChatActivity activity;
    private User user;
    private Chef chef;
    private Client client;
    private MessageContainer messageContainer;
    private MessageAdapter adapter;
    private String clientEmail;
    private String chefEmail;
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
        clientEmail = activity.getIntent().getExtras().getString("clientEmail");
        chefEmail = activity.getIntent().getExtras().getString("chefEmail");
        activity.getSendBtn().setOnClickListener(this);
        activity.getGalBtn().setOnClickListener(this);

        // get chef
        firebaseDB.searchUserByEmail(chefEmail,Constans.FIREBASE_USER_BRANCH);
        // get client
        firebaseDB.searchUserByEmail(clientEmail,Constans.FIREBASE_USER_BRANCH);
        firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);

        String idChat = firebaseDB.createId(Constans.FIREBASE_CHATS_BRANCH);

        messageContainer = new MessageContainer(idChat);

        firebaseDB.sendInfo(messageContainer, messageContainer.getId(), Constans.FIREBASE_CHATS_BRANCH);

        Query query = FirebaseDatabase.getInstance().getReference()
                .child(Constans.FIREBASE_CHATS_BRANCH)
                .child(messageContainer.getId())
                .limitToLast(10);
        query.addChildEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getChildrenCount() == 0){

        } else {
            for (DataSnapshot coincidencia : dataSnapshot.getChildren()) {
                user = coincidencia.getValue(User.class);
                break;
            }
        }
        if(user.isChef()){
            chef = (Chef)user;
        }
        else {
            client = (Client)user;
        }

        adapter.setUserId(user.getName());
        activity.getUsernameTV().setText(user.getName());

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Message message = dataSnapshot.getValue(Message.class);
        adapter.addMessage(message);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendBtn:
                String body = activity.getMessageET().getText().toString();

                String pushId = FirebaseDatabase.getInstance().getReference()
                        .child(Constans.FIREBASE_CHATS_BRANCH)
                        .child(messageContainer.getId()).push().getKey();

                Message message = new Message(
                        pushId,
                        client.getId(),
                        chef.getId(),
                        body,
                        Calendar.getInstance().getTime().getTime()
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
                break;

            case R.id.galBtn:

                break;
        }
    }

}
