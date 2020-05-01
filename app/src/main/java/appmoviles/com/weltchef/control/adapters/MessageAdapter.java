package appmoviles.com.weltchef.control.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Message;

public class MessageAdapter extends BaseAdapter {

    private ArrayList<Message> messages;
    private String userID;

    public MessageAdapter() {
        messages = new ArrayList<>();
        userID = " ";
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root ;

        if (userID.equals(messages.get(position).getUserIDChef()) || userID.equals(messages.get(position).getUserIDClient())){
            root = inflater.inflate(R.layout.messages_row_mine, null);
        }else {
            root = inflater.inflate(R.layout.messages_row_others, null);
        }

        TextView messageRow = root.findViewById(R.id.message_row);
        messageRow.setText(messages.get(position).getBody());

        return root;
    }

    public void addMessage(Message message){
        messages.add(message);
        notifyDataSetChanged();
    }

    public void setUserId(String userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }



}
