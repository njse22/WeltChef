package appmoviles.com.weltchef.control.viewcontrollers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.facebook.FacebookContentProvider;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.RegisterChefActivity;

public class RegisterChefController implements View.OnClickListener {

    private final static String TAG = "RegisterChefController";

    private RegisterChefActivity activity;
    private boolean facebookCredential;
    private FirebaseDB firebaseDB;

    public RegisterChefController(RegisterChefActivity activity) {
        this.activity = activity;
        init();
    }

    public void init(){
        activity.getRegisterBtn().setOnClickListener(this);

        firebaseDB = new FirebaseDB();
        facebookCredential = (Boolean) activity.getIntent().getExtras().get("facebookLogin");

        String name = (String) activity.getIntent().getExtras().get("name");
        String email = (String) activity.getIntent().getExtras().get("email");
        String phone = (String) activity.getIntent().getExtras().get("phone");

        activity.getNameET().setText( name != null ? name : "" );
        activity.getEmailET().setText( email != null ? name : "" );
        activity.getPhoneET().setText( phone != null ? name : "" );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerChefBtn:
                Log.e(TAG, "onClick::facebookCredential --> " + facebookCredential );
                if(facebookCredential){
                    Log.e(TAG, "onClick::emailSingUp --> false" );
                    facebookSingUp();
                }else {
                    Log.e(TAG, "onClick::emailSingUp --> true" );
                    emailSingUp();
                }
                break;
        }
    }

    public void facebookSingUp(){

        String name = activity.getNameET().getText().toString();
        String email = activity.getEmailET().getText().toString();
        String phone = activity.getPhoneET().getText().toString();
        String password = activity.getPasswordET().getText().toString();
        String conPassword = activity.getConPasswordET().getText().toString();
        if(!password.equals(conPassword)){
            Toast.makeText(activity, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
        }
        String description = activity.getDescriptionET().getText().toString();
        boolean chefAtHomeCheck = activity.getChefAtHomeCheck().isChecked();
        boolean chefKitchenCheck = activity.getChefKitchenCheck().isChecked();
        String id = FirebaseAuth.getInstance().getUid();

        Chef chef = new Chef(chefAtHomeCheck, chefKitchenCheck, description, email, name, password, phone, id);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(chef.getId())
                .setValue(chef);

        Intent intent = new Intent(activity, ChefProfileActivity.class);
        intent.putExtra("name", activity.getNameET().getText().toString());
        intent.putExtra("phone", activity.getPhoneET().getText().toString());
        intent.putExtra("email", activity.getEmailET().getText().toString());
        intent.putExtra("description", activity.getDescriptionET().getText().toString());
        activity.startActivity(intent);
        activity.finish();
    }

    public void emailSingUp(){
        String name = activity.getNameET().getText().toString();
        String email = activity.getEmailET().getText().toString();
        String phone = activity.getPhoneET().getText().toString();
        String password = activity.getPasswordET().getText().toString();
        String conPassword = activity.getConPasswordET().getText().toString();
        if(!password.equals(conPassword)){
            Toast.makeText(activity, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
        }
        String description = activity.getDescriptionET().getText().toString();
        boolean chefKitchenCheck = activity.getChefKitchenCheck().isChecked();
        boolean chefAtHomeCheck = activity.getChefAtHomeCheck().isChecked();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(
                        authResult -> {
                            String id = FirebaseAuth.getInstance().getUid();
                            Chef chef = new Chef(chefAtHomeCheck, chefKitchenCheck, description, email, name, password, phone, id);
                            FirebaseDatabase.getInstance().getReference()
                                    .child(Constants.FIREBASE_USER_BRANCH)
                                    .child(chef.getId())
                                    .setValue(chef);
                            Intent intent = new Intent(activity, ChefProfileActivity.class);
                            intent.putExtra("name", activity.getNameET().getText().toString());
                            intent.putExtra("phone", activity.getPhoneET().getText().toString());
                            intent.putExtra("email", activity.getEmailET().getText().toString());
                            intent.putExtra("description", activity.getDescriptionET().getText().toString());
                            intent.putExtra("user", chef);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                ).addOnFailureListener(
                e -> {
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG);
                }
        );
    }



}
