package appmoviles.com.weltchef.app;

import com.facebook.FacebookSdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;

import appmoviles.com.weltchef.db.FirebaseDB;
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

        FirebaseDB firebaseDB = new FirebaseDB();

        Intent intent = new Intent(this, LogingActivity.class);
        intent.putExtra("facebookLogin", isLoggedIn);
        this.startActivity(intent);

    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
