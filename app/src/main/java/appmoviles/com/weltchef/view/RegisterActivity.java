package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.RegisterChefController;
import appmoviles.com.weltchef.control.viewcontrollers.RegisterController;

public class RegisterActivity extends AppCompatActivity {

    private Button chefBtn;
    private TextView welcomeTxt;
    private TextView chefOrClientTxt;
    private Button clientBtn;
    private RegisterController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        chefBtn=findViewById(R.id.chefBtn);
        welcomeTxt=findViewById(R.id.welcomeTxt);
        chefOrClientTxt= findViewById(R.id.chefOrClientTxt);
        clientBtn = findViewById(R.id.clientBtn);

        controller = new RegisterController(this);
    }

    public Button getChefBtn() {
        return chefBtn;
    }

    public void setChefBtn(Button chefBtn) {
        this.chefBtn = chefBtn;
    }

    public TextView getWelcomeTxt() {
        return welcomeTxt;
    }

    public void setWelcomeTxt(TextView welcomeTxt) {
        this.welcomeTxt = welcomeTxt;
    }

    public TextView getChefOrClientTxt() {
        return chefOrClientTxt;
    }

    public void setChefOrClientTxt(TextView chefOrClientTxt) {
        this.chefOrClientTxt = chefOrClientTxt;
    }

    public Button getClientBtn() {
        return clientBtn;
    }

    public void setClientBtn(Button clientBtn) {
        this.clientBtn = clientBtn;
    }
}
