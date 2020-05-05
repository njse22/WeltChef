package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.ChefProfileActivity;

public class ChefProfileController implements View.OnClickListener {

    private ChefProfileActivity view;

    public ChefProfileController(ChefProfileActivity view) {
        this.view = view;

        view.getWeltChef().setOnClickListener(this);
        view.getFacebook().setOnClickListener(this);
        view.getInstagram().setOnClickListener(this);
        view.getTwitter().setOnClickListener(this);


        FirebaseDatabase.getInstance().getApp().getOptions().getDatabaseUrl();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.weltChefBtn:
                break;
            case R.id.facebookBtn:
                break;
            case R.id.instagramBtn:
                break;
            case R.id.twitterBtn:
                break;
        }
    }
}
