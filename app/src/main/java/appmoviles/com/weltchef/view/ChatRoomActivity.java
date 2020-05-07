package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.ChatRoomAdapter;
import appmoviles.com.weltchef.control.viewcontrollers.ChatController;
import appmoviles.com.weltchef.control.viewcontrollers.ChatRoomController;

public class ChatRoomActivity extends AppCompatActivity {

    private ImageView userImage;
    private TextView userName;
    private ChatRoomAdapter adapter;
    private ChatRoomController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);

        adapter = new ChatRoomAdapter();
        controller = new ChatRoomController(this);

    }

    public ImageView getUserImage() {
        return userImage;
    }

    public TextView getUserName() {
        return userName;
    }

    public ChatRoomAdapter getAdapter() {
        return adapter;
    }
}
