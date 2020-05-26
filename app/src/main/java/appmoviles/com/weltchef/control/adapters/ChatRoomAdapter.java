package appmoviles.com.weltchef.control.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.MessageContainer;
import appmoviles.com.weltchef.entity.User;

public class ChatRoomAdapter extends BaseAdapter implements ValueEventListener {

    private ArrayList<MessageContainer> messageContainers;
    private FirebaseDB firebaseDB;
    private User user;

    public ChatRoomAdapter() {
        messageContainers = new ArrayList<>(10);
        firebaseDB = new FirebaseDB();
    }

    public ArrayList<MessageContainer> getMessageContainers() {
        return messageContainers;
    }

    public void setMessageContainers(ArrayList<MessageContainer> messageContainers) {
        this.messageContainers = messageContainers;
        notifyDataSetChanged();
    }

    public void addChat(MessageContainer message){
        this.messageContainers.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messageContainers.size();
    }

    @Override
    public Object getItem(int position) {
        return messageContainers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View chatList = inflater.inflate(R.layout.chats_adapter, null);
        ImageView userImage = chatList.findViewById(R.id.userImage);
        TextView userName = chatList.findViewById(R.id.userName);

        userName.setText("name: "+ messageContainers.get(position).getUserIDChef());

        return chatList;

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            user = data.getValue(User.class);
            break;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
