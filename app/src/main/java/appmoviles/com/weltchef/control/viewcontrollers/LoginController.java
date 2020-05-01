package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.Client;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.exceptions.UserNotRegisterException;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.RegisterActivity;

public class LoginController implements View.OnClickListener, ValueEventListener {

    private LogingActivity activity;
    private UsersManager manager;
    private FirebaseDB firebaseDB;
    private User user;

    public LoginController(LogingActivity activity) {
        this.activity = activity;
        init();
    }

    public void init(){
        activity.getLoginBtn().setOnClickListener(this);
        activity.getRegisterTextButton().setOnClickListener(this);
        manager = new UsersManager();
        firebaseDB = new FirebaseDB();
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginBtn:
                firebaseDB.searchUserByEmail(
                        activity.getUserNameEditText().getText().toString(),
                        Constans.FIREBASE_CHEF_BRANCH,
                        Constans.FIREBASE_CLIENT_BRANCH);

                firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);

                Log.e(">>>", "tag: " + (user instanceof Chef) );

                if(user instanceof Chef){
                    Intent i = new Intent(activity, ChefProfileActivity.class);
                    i.putExtra("user", user);
                    activity.startActivity(i);
                }
                else if (user instanceof Client) {
                    Intent i = new Intent(activity, ClientProfileActivity.class);
                    i.putExtra("user", user);
                    activity.startActivity(i);
                    activity.startActivity(i);
                }
                break;
            case R.id.registerTextButton:
                Intent intent = new Intent(activity, RegisterActivity.class);
                activity.startActivity(intent);
                break;
        }

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.e(">>>>", "data: "+ dataSnapshot.getValue().toString());

        if(dataSnapshot.getChildrenCount() == 0){

        } else {
            for (DataSnapshot coincidencia : dataSnapshot.getChildren()) {
                if (firebaseDB.getUsersManager().getCunrrently() == Constans.CHEF_INSTANCE)
                    user = coincidencia.getValue(Chef.class);
                else
                    user = coincidencia.getValue(Client.class);

                break;
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
