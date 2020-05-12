package appmoviles.com.weltchef.control.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.MessageContainer;

public class ChatRoomAdapter extends BaseAdapter {

    private ArrayList<MessageContainer> messageContainers;

    public ChatRoomAdapter() {
        messageContainers = new ArrayList<>(10);
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
        userName.setText("name: "+ messageContainers.get(position).getId());

        return chatList;
    }



}
