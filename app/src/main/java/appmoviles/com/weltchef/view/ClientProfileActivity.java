package appmoviles.com.weltchef.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.interfaces.OnDialogListener;
import appmoviles.com.weltchef.control.viewcontrollers.ClientProfileController;
import appmoviles.com.weltchef.entity.Menu;


public class ClientProfileActivity extends AppCompatActivity{

    private View dishLayau;
    private TextView clientName;
    private ImageButton clientPicture;
    private Button searchChef;
    private Button askService;
    private NestedScrollView lastServices;
    private NestedScrollView likedChefs;
    private ListView listOrders;


    private ClientProfileController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        dishLayau = findViewById(R.id.dishLayau);
        this.clientName = findViewById(R.id.clientName);
        searchChef = findViewById(R.id.searchChef);
        askService = findViewById(R.id.askService);
        clientPicture = findViewById(R.id.clientPicture);
        lastServices = findViewById(R.id.lastServices);
        likedChefs = findViewById(R.id.likedChefs);
        listOrders = findViewById(R.id.listOrders);
        controller = new ClientProfileController(this);

    }

    public TextView getClientName() {
        return clientName;
    }

    public Button getSearchChef() {
        return searchChef;
    }

    public Button getAskService() {
        return askService;
    }

    public ImageButton getClientPicture() {
        return clientPicture;
    }

    public void setBackgroundLayau(){
        dishLayau.setBackgroundColor(234567);
    }

    public NestedScrollView getLastServices() {
        return lastServices;
    }

    public NestedScrollView getLikedChefs() {
        return likedChefs;
    }

    public ListView getListOrders() {
        return listOrders;
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

}
