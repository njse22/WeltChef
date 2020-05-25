package appmoviles.com.weltchef.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.EditProfileController;

public class EditProfileActivity extends AppCompatActivity {

    private final static String TAG = "EditProfileActivity";

    private TextView name;
    private EditText password, passwordVerify, descriptionET;
    private Button save;
    private ImageView picture;
    private EditProfileController controller;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        Log.e(TAG, "onCreate ->  true");
        controller = new EditProfileController(this);

    }

    private void init() {
        //Init elements
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        passwordVerify = findViewById(R.id.passwordVerify);
        picture = findViewById(R.id.picture);
        descriptionET = findViewById(R.id.descriptionET);
        save = findViewById(R.id.save);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode,resultCode,data);
    }

    public TextView getName() {
        return name;
    }

    public EditText getPassword() {
        return password;
    }

    public EditText getPasswordVerify() {
        return passwordVerify;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public EditText getDescriptionET() {
        return descriptionET;
    }

    public Button getSave() {
        return save;
    }
}
