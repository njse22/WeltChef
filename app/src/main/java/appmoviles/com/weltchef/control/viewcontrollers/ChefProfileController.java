package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.CameraActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;

public class ChefProfileController implements View.OnClickListener {

    private ChefProfileActivity view;

    public ChefProfileController(ChefProfileActivity view) {
        this.view = view;

        view.getWhatsapp().setOnClickListener(this);
        view.getFacebook().setOnClickListener(this);
        view.getInstagram().setOnClickListener(this);
        view.getTwitter().setOnClickListener(this);

     //   FirebaseDatabase.getInstance().getApp().getOptions().getDatabaseUrl(); // ??
        init();
    }

    public void init(){
        view.getNameChef().setText((String)view.getIntent().getExtras().get("name"));
        view.getTelephone().setText((String)view.getIntent().getExtras().get("phone"));
        view.getEmail().setText((String)view.getIntent().getExtras().get("email"));
        view.getDescription().setText((String)view.getIntent().getExtras().get("description"));

        view.getPhotochef().setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.whatsappBtn:
                break;
            case R.id.facebookBtn:
                break;
            case R.id.instagramBtn:
                break;
            case R.id.twitterBtn:
                break;

            case R.id.imageView:
               // Intent intent = new Intent(this.view, ChefProfileActivity.class);
               // this.view.startActivity(intent);
                break;


        }
    }
}
