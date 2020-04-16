package appmoviles.com.weltchef.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import appmoviles.com.weltchef.view.LogingActivity;
import appmoviles.com.weltchef.view.MapsActivity;
import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.ProfileChefActivity;

public class MainActivity extends AppCompatActivity implements OnDataSubmitted {

    private LinearLayout fragmentContainer;
    private ProgressBar progressBar;
    private Fragment ProfileChefActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragmentContainer);
        ProfileChefActivity = new ProfileChefActivity();
        ((ProfileChefActivity)ProfileChefActivity).setListener(this);
        loadFragment(ProfileChefActivity);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, 11);

        if(ContextCompat.checkSelfPermission(
                this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Intent i = new Intent(this, LogingActivity.class);
            startActivity(i);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 11){
            if(ContextCompat.checkSelfPermission(
                    this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                    this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    @Override
    public void onData(Fragment fragment, String... args) {
        loadFragment(ProfileChefActivity);
    }

    private void loadFragment(Fragment profileChefActivity) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, ProfileChefActivity);
        transaction.commit();
    }
}
