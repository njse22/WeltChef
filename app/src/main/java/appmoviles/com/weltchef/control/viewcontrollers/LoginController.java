package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;

import android.util.Log;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.RegisterActivity;

public class LoginController implements View.OnClickListener, ValueEventListener, FacebookCallback<LoginResult> {

    private final static String TAG = "LoginController >>>";

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
        activity.getLoginTxt().setOnClickListener(this);

        manager = new UsersManager();
        firebaseDB = new FirebaseDB();
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginBtn:
                Log.e(TAG, "onClick::loginBtn -> true");
                String email = activity.getUserNameEditText().getText().toString();
                String password = activity.getPasswordEditText().getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(
                                authResult -> {
                                    Log.e(TAG, "onClick::addOnSuccessListener -> true");
                                    firebaseDB.searchUserByid(FirebaseAuth.getInstance().getUid());
                                    Log.e(TAG, "onClick::uid -> "+ FirebaseAuth.getInstance().getUid() );
                                    firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);
                                }
                        )
                        .addOnFailureListener(
                            e -> {
                                Log.e(TAG, "onClick::addOnFailureListener -> true");
                                Toast.makeText(activity,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                );
                activity.finish();
                break;
            case R.id.registerTextButton:
                Intent intent = new Intent(activity, RegisterActivity.class);
                intent.putExtra("name","");
                intent.putExtra("email","");
                intent.putExtra("phone","");
                intent.putExtra("facebookLogin", activity.isLoggedInFacebook());
                activity.startActivity(intent);
                activity.finish();
                break;

            case R.id.loginTxt:


                break;
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.e(TAG, "onDataChange::dataSnapshot -> "+ dataSnapshot );
        user = dataSnapshot.getValue(User.class);

        if (user.isChef()){
            Intent i = new Intent(activity, ChefProfileActivity.class);
            i.putExtra("user",user);
            Log.e(TAG, "onDataChange::user -> "+ user.getName() );
            activity.startActivity(i);
        }else {
            Intent i = new Intent(activity, ClientProfileActivity.class);
            i.putExtra("user",user);
            activity.startActivity(i);
        }
        activity.finish();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {  }

    @Override
    public void onSuccess(LoginResult loginResult) {
        handleFacebookAccessToken(loginResult.getAccessToken());
    }

    @Override
    public void onCancel() { }

    @Override
    public void onError(FacebookException error) { }

    public void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(activity,
                       authResult -> {
                            Intent i = new Intent(activity, RegisterActivity.class);
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            i.putExtra("name",firebaseUser.getDisplayName());
                            i.putExtra("email",firebaseUser.getEmail());
                            i.putExtra("phone",firebaseUser.getPhoneNumber());
                            i.putExtra("facebookLogin",true);

                            User user = new User(
                                    firebaseUser.getDisplayName(),
                                    firebaseUser.getEmail(),
                                    firebaseUser.getPhoneNumber(),
                                    "",
                                    firebaseUser.getUid(),
                                    false);

                           FirebaseDatabase.getInstance().getReference()
                                   .child(Constants.FIREBASE_USER_BRANCH)
                                   .child(firebaseUser.getUid())
                                   .setValue(user);

                            activity.startActivity(i);
                            activity.finish();
                       }
                ).addOnFailureListener(
                e -> {
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG);
                }
        );
    }

    public void onResume() {
        if (activity.isLoggedInFacebook()){
            activity.getLoginTxt().setText("CONTINUA COMO "+
                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toUpperCase()  );
            FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIREBASE_USER_BRANCH)
                    .child(FirebaseAuth.getInstance().getUid())
                    .addListenerForSingleValueEvent(this);
        }

    }
}
