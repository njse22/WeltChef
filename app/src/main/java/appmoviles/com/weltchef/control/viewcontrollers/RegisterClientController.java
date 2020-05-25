package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Chef;
import appmoviles.com.weltchef.entity.Client;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.ChefProfileActivity;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.RegisterClientActivity;

public class RegisterClientController implements View.OnClickListener {

    private RegisterClientActivity activity;
    private FirebaseDB firebaseDB;
    private boolean facebookCredential;

    public RegisterClientController(RegisterClientActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        init();
    }

    public void init(){
        activity.getRegisterBtn().setOnClickListener(this);

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
            case R.id.registerBtn:
                if(facebookCredential){
                    facebookSingUp();
                }else {
                    emailSingUp();
                }
        }
    }

    public void facebookSingUp(){

        String name = activity.getNameET().getText().toString();
        String email = activity.getEmailET().getText().toString();
        String phone = activity.getPhoneET().getText().toString();
        String password = activity.getPasswordET().getText().toString();
        String conPassword = activity.getCoPasswordET().getText().toString();
        if(!password.equals(conPassword)){
            Toast.makeText(activity, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
        }

        String id = FirebaseAuth.getInstance().getUid();

        Client client = new Client(name,email,phone,password,id);

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_USER_BRANCH)
                .child(client.getId())
                .setValue(client);

        Intent intent = new Intent(activity, ClientProfileActivity.class);
        intent.putExtra("name", activity.getNameET().getText().toString());
        intent.putExtra("phone", activity.getPhoneET().getText().toString());
        intent.putExtra("email", activity.getEmailET().getText().toString());
        activity.startActivity(intent);
        activity.finish();

    }

    public void emailSingUp(){
        String name = activity.getNameET().getText().toString();
        String email = activity.getEmailET().getText().toString();
        String phone = activity.getPhoneET().getText().toString();
        String password = activity.getPasswordET().getText().toString();
        String conPassword = activity.getCoPasswordET().getText().toString();
        if(!password.equals(conPassword)){
            Toast.makeText(activity, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(
                        authResult -> {
                            String id = FirebaseAuth.getInstance().getUid();
                            Client client = new Client(name,email,phone,password,id);
                            FirebaseDatabase.getInstance().getReference()
                                    .child(Constants.FIREBASE_USER_BRANCH)
                                    .child(client.getId())
                                    .setValue(client);
                            Intent intent = new Intent(activity, ClientProfileActivity.class);
                            intent.putExtra("name", activity.getNameET().getText().toString());
                            intent.putExtra("phone", activity.getPhoneET().getText().toString());
                            intent.putExtra("email", activity.getEmailET().getText().toString());
                            intent.putExtra("user", client);
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
