package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

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
                String id = FirebaseDatabase.getInstance().getReference().child(Constans.FIREBASE_CHEF_BRANCH).push().getKey();
                Chef chef = new Chef(name,email,phone,password,id,false,true,description);

                FirebaseDatabase.getInstance().getReference()
                        .child(Constans.FIREBASE_CHEF_BRANCH)
                        .child(id)
                        .setValue(chef);

                //firebaseDB.sendUser(chef, Constans.FIREBASE_CHEF_BRANCH);
                break;

        }


    }
}
