package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.RegisterClientController;

public class RegisterClientActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText coPasswordET;
    private EditText phoneET;
    private Button registerBtn;
    private RegisterClientController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        coPasswordET = findViewById(R.id.coPasswordET);
        phoneET = findViewById(R.id.phoneET);
        registerBtn = findViewById(R.id.registerBtn);

        controller = new RegisterClientController(this);

    }

    public EditText getNameET() {
        return nameET;
    }

    public EditText getEmailET() {
        return emailET;
    }

    public EditText getPasswordET() {
        return passwordET;
    }

    public EditText getCoPasswordET() {
        return coPasswordET;
    }

    public EditText getPhoneET() {
        return phoneET;
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }
}
