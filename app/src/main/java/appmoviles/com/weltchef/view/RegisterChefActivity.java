package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.RegisterChefController;


public class RegisterChefActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText emailET;
    private EditText phoneET;
    private EditText passwordET;
    private EditText descriptionET;
    private Button registerBtn;

    private RegisterChefController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_chef);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        phoneET = findViewById(R.id.phoneET);
        passwordET = findViewById(R.id.passwordET);
        registerBtn = findViewById(R.id.registerBtn);
        descriptionET = findViewById(R.id.descriptionET);

        controller = new RegisterChefController(this);
    }

    public EditText getNameET() {
        return nameET;
    }

    public void setNameET(EditText nameET) {
        this.nameET = nameET;
    }

    public EditText getEmailET() {
        return emailET;
    }

    public void setEmailET(EditText emailET) {
        this.emailET = emailET;
    }

    public EditText getPhoneET() {
        return phoneET;
    }

    public void setPhoneET(EditText phoneET) {
        this.phoneET = phoneET;
    }

    public EditText getPasswordET() {
        return passwordET;
    }

    public void setPasswordET(EditText passwordET) {
        this.passwordET = passwordET;
    }

    public EditText getDescriptionET() {
        return descriptionET;
    }

    public void setDescriptionET(EditText descriptionET) {
        this.descriptionET = descriptionET;
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(Button registerBtn) {
        this.registerBtn = registerBtn;
    }
}