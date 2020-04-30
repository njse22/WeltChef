package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.ClientProfileController;

public class ClientProfileActivity extends AppCompatActivity{

    private TextView clientName;
    private Button searchChef;
    private Button askService;
    private NestedScrollView lastServices;
    private NestedScrollView likedChefs;
    private ClientProfileController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_profile_activity);

        this.clientName = findViewById(R.id.clientName);
        searchChef = findViewById(R.id.searchChef);
        askService = findViewById(R.id.askService);

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
}
