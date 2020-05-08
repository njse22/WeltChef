package appmoviles.com.weltchef.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDB firebaseDB = new FirebaseDB();



        Intent intent = new Intent(this, LogingActivity.class);
        this.startActivity(intent);


    }




}
