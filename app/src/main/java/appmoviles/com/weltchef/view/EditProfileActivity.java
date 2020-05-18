package appmoviles.com.weltchef.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.ImageView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.EditProfileController;
import appmoviles.com.weltchef.entity.User;

public class EditProfileActivity extends AppCompatActivity {
    private EditText name, password, passwordVerify;
    private ImageView picture;
    private EditProfileController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_edit_profile);
        init();
        controller = new EditProfileController(this);

    }

    private void init() {
        //Init elements
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        passwordVerify = findViewById(R.id.passwordVerify);
        picture = findViewById(R.id.picture);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode,resultCode,data);
    }

    public EditText getName() {
        return name;
    }

    public void setName(EditText name) {
        this.name = name;
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
}
