package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.ChatController;

public class ChatActivity extends AppCompatActivity {

    private TextView usernameTV;
    private ListView messagesList;
    private EditText messageET;
    private Button galBtn, sendBtn;
    private ImageView messageIV;
    private ConstraintLayout controlsContainer;

    private ChatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        usernameTV = findViewById(R.id.usernameTV);
        messagesList = findViewById(R.id.messagesList);
        messageET = findViewById(R.id.messageET);
        galBtn = findViewById(R.id.galBtn);
        sendBtn = findViewById(R.id.sendBtn);
        messageIV = findViewById(R.id.messageIV);
        controlsContainer = findViewById(R.id.controlsContainer);

        controller = new ChatController(this);

    }

    public TextView getUsernameTV() {
        return usernameTV;
    }

    public void setUsernameTV(TextView usernameTV) {
        this.usernameTV = usernameTV;
    }

    public ListView getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(ListView messagesList) {
        this.messagesList = messagesList;
    }

    public EditText getMessageET() {
        return messageET;
    }

    public void setMessageET(EditText messageET) {
        this.messageET = messageET;
    }

    public Button getGalBtn() {
        return galBtn;
    }

    public void setGalBtn(Button galBtn) {
        this.galBtn = galBtn;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public void setSendBtn(Button sendBtn) {
        this.sendBtn = sendBtn;
    }

    public ImageView getMessageIV() {
        return messageIV;
    }

    public void setMessageIV(ImageView messageIV) {
        this.messageIV = messageIV;
    }

    public ConstraintLayout getControlsContainer() {
        return controlsContainer;
    }

    public void setControlsContainer(ConstraintLayout controlsContainer) {
        this.controlsContainer = controlsContainer;
    }
}

