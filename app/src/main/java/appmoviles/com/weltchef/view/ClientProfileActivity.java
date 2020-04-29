package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.ClientProfileController;

public class ClientProfileActivity extends AppCompatActivity implements View.OnClickListener {

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

        clientPicture.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clientPicture:
                DialogFragment dialog = new PhotoDialogFragment();
                dialog.show(getSupportFragmentManager(), "photo_dialog");
                break;
        }
    }
}
