package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.view.ChatActivity;
import appmoviles.com.weltchef.view.ChatRoomActivity;

public class ChatRoomController implements ValueEventListener, AdapterView.OnItemClickListener {

    private ChatRoomActivity activity;
    private User user;
    private FirebaseDB firebaseDB;
    private MessageContainer messageContainer;

    public ChatRoomController(ChatRoomActivity activity) {
        this.activity = activity;
        this.user = (User)activity.getIntent().getExtras().get("user");
        this.firebaseDB = new FirebaseDB();
        init();
    }

    private void init(){
        if(user.isChef()){
            firebaseDB.searchChatByChef(user.getId());
        }
        else {
            firebaseDB.searchChatByClient(user.getId());
        }
        firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getChildrenCount() == 0){

        }
        else {
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                messageContainer = dataSnapshot.getValue(MessageContainer.class);
                activity.getAdapter().addChat(messageContainer);
                break;
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra("messageContainer", messageContainer);
        intent.putExtra("user", user);
        activity.startActivity(intent);

    }
}
