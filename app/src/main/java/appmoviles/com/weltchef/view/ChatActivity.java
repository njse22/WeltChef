package appmoviles.com.weltchef.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.ChatController;

public class ChatActivity extends AppCompatActivity {

    private final String[] status = {
            "Aceptada",
            "Completa",};

    private TextView usernameTV;
    private ListView messagesList;
    private EditText messageET;
    private Button galBtn, sendBtn;
    private ImageView messageIV;
    private ConstraintLayout controlsContainer;
    private ArrayAdapter<String> statusAdapter;
    private Spinner spinnerStatus;

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

        spinnerStatus = findViewById(R.id.spinnerStatus);
        statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, status);
        statusAdapter.notifyDataSetChanged();
        spinnerStatus.setAdapter(statusAdapter);

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

    public ArrayAdapter<String> getStatusAdapter() {
        return statusAdapter;
    }

    public Spinner getSpinnerStatus() {
        return spinnerStatus;
    }

    //Mi actividad pierde el primer plano
    @Override
    protected void onPause() {
        controller.beforePause();
        super.onPause();
    }

    //Mi actividad recupera el primer plano
    @Override
    protected void onResume() {
        controller.beforeResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode, resultCode, data);
    }

    public void hideImage() {
        messageIV.setVisibility(View.GONE);
    }

    public void showImage(){
        messageIV.setVisibility(View.VISIBLE);
    }

    public void hideSpinner(){ spinnerStatus.setVisibility(View.GONE); }

    public void showSpinner(){ spinnerStatus.setVisibility(View.VISIBLE); }


}

