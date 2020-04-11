package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.ClientProfileActivity;

public class ClientProfileController implements View.OnClickListener {

    private ClientProfileActivity view;

    public ClientProfileController(ClientProfileActivity view) {
        this.view = view;

        view.getAskService().setOnClickListener(this);
        view.getSearchChef().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.askService:
                break;
            case R.id.searchChef:
                break;
        }
    }
}
