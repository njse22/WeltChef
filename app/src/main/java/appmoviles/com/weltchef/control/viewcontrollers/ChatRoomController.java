package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
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

import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.ChatActivity;
import appmoviles.com.weltchef.view.ChatRoomActivity;

public class ChatRoomController implements ValueEventListener, AdapterView.OnItemClickListener, ChildEventListener {

    private final static String TAG = "ChatRoomController";

    private ChatRoomActivity activity;
    private User user;
    private FirebaseDB firebaseDB;
    private MessageContainer messageContainer;

    public ChatRoomController(ChatRoomActivity activity) {
        this.activity = activity;
        this.user = (User)activity.getIntent().getExtras().get("user");
        this.firebaseDB = new FirebaseDB();
        this.activity.getListViewChats().setOnItemClickListener(this);
        this.messageContainer = new MessageContainer();
        init();
    }

    private void init(){
        if(user.isChef()){
            Query query = FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIREBASE_CHATS_BRANCH)
                    .orderByChild("userIDChef")
                    .equalTo(user.getId());

            query.addChildEventListener(this);
        }
        else {
            firebaseDB.searchChatByClient(user.getId());
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getChildrenCount() == 0){
            Log.e(TAG, "onDataChange::dataSnapshot --> " + dataSnapshot);
        }
        else {
            Log.e(TAG, "onDataChange::dataSnapshot --> " + dataSnapshot);
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                Log.e(TAG, "onDataChange::dataSnapshot::for --> " + dataSnapshot);
                messageContainer = dataSnapshot.getValue(MessageContainer.class);
                activity.getAdapter().addChat(messageContainer);
                break;
            }
        }
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        String id = dataSnapshot.child("id").getValue(String.class);
        String idChef = dataSnapshot.child("userIDChef").getValue(String.class);
        String idClient = dataSnapshot.child("userIDClient").getValue(String.class);

        Log.e(TAG, "onChildAdded::id --> " + id);
        Log.e(TAG, "onChildAdded::idChef --> " + idChef);
        Log.e(TAG, "onChildAdded::idClient --> " + idClient);

        messageContainer.setId(id);
        messageContainer.setUserIDChef(idChef);
        messageContainer.setUserIDClient(idClient);
        activity.getAdapter().addChat(messageContainer);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        new Thread(
                () ->{
                    Intent intent = new Intent(activity, ChatActivity.class);
                    intent.putExtra("messageContainer", messageContainer);
                    intent.putExtra("user", user);
                    activity.startActivity(intent);
                }
        ).start();

        Log.e(">>>", "Message Container");
    }
}
