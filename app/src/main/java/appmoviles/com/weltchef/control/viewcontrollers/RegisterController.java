package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.view.View;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.RegisterActivity;
import appmoviles.com.weltchef.view.RegisterChefActivity;

public class RegisterController implements View.OnClickListener {

    private RegisterActivity activity;

    public RegisterController(RegisterActivity register) {
        this.activity = register;
        init();
    }

    private void init() {
        activity.getChefBtn().setOnClickListener(this);
        activity.getClientBtn().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chefBtn:
                Intent intent = new Intent(activity, RegisterChefActivity.class);
                activity.startActivity(intent);
                break;

            case R.id.clientBtn:
                //Intent i = new Intent(activity, )
                break;



        }

    }
}
