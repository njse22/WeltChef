package appmoviles.com.weltchef.view;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;

import java.io.File;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.interfaces.OnDialogListener;
import appmoviles.com.weltchef.control.viewcontrollers.ClientProfileController;
import appmoviles.com.weltchef.entity.User;

public class ClientProfileActivity extends AppCompatActivity{

    private TextView clientName;
    private ImageButton clientPicture;
    private Button searchChef;
    private Button askService;
    private NestedScrollView lastServices;
    private NestedScrollView likedChefs;
    private ClientProfileController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        this.clientName = findViewById(R.id.clientName);
        searchChef = findViewById(R.id.searchChef);
        askService = findViewById(R.id.askService);
        clientPicture = findViewById(R.id.clientPicture);

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
}
