package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.view.ProfileChefActivity;
import appmoviles.com.weltchef.view.RegisterActivity;

public class LoginController implements View.OnClickListener {

    private LogingActivity activity;
    private UsersManager manager;
    private FirebaseDB firebaseDB;

    public LoginController(LogingActivity activity) {
        this.activity = activity;
        init();
    }

    public void init(){
        activity.getLoginBtn().setOnClickListener(this);
        activity.getRegisterTextButton().setOnClickListener(this);
        manager = new UsersManager();
        firebaseDB = new FirebaseDB();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginBtn:
                User user = this.manager.getUserByEmail(activity.getUserNameEditText().getText().toString());
                if(manager.identifyUser(user)){
                    firebaseDB.readInfo(Constans.FIREBASE_CHEF_BRANCH, "");

                    Intent i = new Intent(activity, ProfileChefActivity.class);
                    activity.startActivity(i);
                }
                break;
            case R.id.registerTextButton:
                Intent intent = new Intent(activity, RegisterActivity.class);
                activity.startActivity(intent);
                break;
        }

    }


}
