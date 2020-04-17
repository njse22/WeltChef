package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.RegisterChefActivity;

public class RegisterChefController implements View.OnClickListener {

    private RegisterChefActivity activity;
    private FirebaseDB firebaseDB;

    public RegisterChefController(RegisterChefActivity activity) {
        this.activity = activity;
        firebaseDB = new FirebaseDB();
    }

    public void init(){
        activity.getRegisterBtn().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn:
                String name = activity.getNameET().getText().toString();
                String email = activity.getEmailET().getText().toString();
                String phone = activity.getPhoneET().getText().toString();
                String password = activity.getPasswordET().getText().toString();
                String description = activity.getDescriptionET().getText().toString();
                Chef chef = new Chef(name,email,phone,password,false,true,description);
                firebaseDB.sendUser(chef, Constans.FIREBASE_CHEF_BRANCH);
                break;

        }


    }
}
