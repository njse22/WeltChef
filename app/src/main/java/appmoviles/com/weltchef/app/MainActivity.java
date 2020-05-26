package appmoviles.com.weltchef.app;

import com.facebook.FacebookSdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.firebase.storage.FirebaseStorage;

import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.R;

public class MainActivity extends AppCompatActivity {

    private boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        isLoggedIn = accessToken != null && !accessToken.isExpired();


        FirebaseStorage.getInstance().getReference();


        FirebaseDB firebaseDB = new FirebaseDB();

        Intent intent = new Intent(this, LogingActivity.class);
        this.startActivity(intent);

    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
