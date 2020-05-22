package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.RegisterChefController;


public class RegisterChefActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText emailET;
    private EditText phoneET;
    private EditText passwordET;
    private EditText conPasswordET;
    private EditText descriptionET;
    private Button registerBtn;
    private CheckBox chefAtHomeCheck;
    private CheckBox chefKitchenCheck;
    private RegisterChefController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_chef);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        phoneET = findViewById(R.id.name);
        passwordET = findViewById(R.id.password);
        conPasswordET = findViewById(R.id.conPasswordET);
        registerBtn = findViewById(R.id.registerChefBtn);
        descriptionET = findViewById(R.id.descriptionET);
        chefAtHomeCheck = findViewById(R.id.chefAtHomeCheck);
        chefKitchenCheck = findViewById(R.id.chefKitchenCheck);

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

    public EditText getConPasswordET() {
        return conPasswordET;
    }

    public void setConPasswordET(EditText conPasswordET) {
        this.conPasswordET = conPasswordET;
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

    public CheckBox getChefAtHomeCheck() {
        return chefAtHomeCheck;
    }

    public CheckBox getChefKitchenCheck() {
        return chefKitchenCheck;
    }
}
