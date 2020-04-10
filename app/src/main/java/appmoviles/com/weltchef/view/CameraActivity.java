package appmoviles.com.weltchef.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.CameraController;

public class CameraActivity  extends AppCompatActivity {

    private CameraController controller;
    private ImageView photoIV;
    private Button photoBtn;
    private Button galeryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoIV = findViewById(R.id.phothoIV);
        photoBtn = findViewById(R.id.photoBtn);
        galeryBtn = findViewById(R.id.galeryBtn);


        controller = new CameraController(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode, requestCode, data);
    }

    public ImageView getPhotoIV() {
        return photoIV;
    }

    public Button getPhotoBtn() {
        return photoBtn;
    }

    public Button getGaleryBtn() {
        return galeryBtn;
    }


}
