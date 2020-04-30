package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.RegisterChefActivity;

public class RegisterChefController implements View.OnClickListener {

    private RegisterChefActivity activity;
    private FirebaseDB firebaseDB;

    public RegisterChefController(RegisterChefActivity activity) {
        this.activity = activity;
        init();
    }

    public void init(){
        activity.getRegisterBtn().setOnClickListener(this);
        firebaseDB = new FirebaseDB();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerChefBtn:
                String name = activity.getNameET().getText().toString();
                String email = activity.getEmailET().getText().toString();
                String phone = activity.getPhoneET().getText().toString();
                String password = activity.getPasswordET().getText().toString();
                String description = activity.getDescriptionET().getText().toString();
                String id = firebaseDB.createId(Constans.FIREBASE_USER_BRANCH);
                Chef chef = new Chef(true, false, description, email, name, password, phone, id);

                firebaseDB.sendInfo(chef, id ,Constans.FIREBASE_USER_BRANCH);

                Intent intent = new Intent(activity, ChefProfileActivity.class);
                intent.putExtra("name", activity.getNameET().getText().toString());
                intent.putExtra("phone", activity.getPhoneET().getText().toString());
                intent.putExtra("email", activity.getEmailET().getText().toString());
                intent.putExtra("description", activity.getDescriptionET().getText().toString());
                activity.startActivity(intent);
                break;
        }
    }
}
