package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.RegisterChefActivity;

public class RegisterChefController implements View.OnClickListener {

    private RegisterChefActivity chefActivity;

    private FirebaseDB firebaseDB;

    public RegisterChefController(RegisterChefActivity activity) {
        this.chefActivity = activity;
        init();
    }

    public void init(){
        chefActivity.getRegisterBtn().setOnClickListener(this);
        firebaseDB = new FirebaseDB();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.registerChefBtn:
                String name = chefActivity.getNameET().getText().toString();
                String email = chefActivity.getEmailET().getText().toString();
                String phone = chefActivity.getPhoneET().getText().toString();
                String password = chefActivity.getPasswordET().getText().toString();
                String description = chefActivity.getDescriptionET().getText().toString();
                String id = firebaseDB.createId(Constans.FIREBASE_CHEF_BRANCH);
                Chef chef = new Chef(name,email,phone,password,id,false,true,description);

                firebaseDB.sendInfo(chef, id ,Constans.FIREBASE_CHEF_BRANCH);
                break;
        }


    }
}
