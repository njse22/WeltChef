package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import appmoviles.com.weltchef.R;

public class Register extends AppCompatActivity {

    private Button chefBtn;
    private TextView welcomeTxt;
    private TextView chefOrClientTxt;
    private Button clientBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        chefBtn=findViewById(R.id.chefBtn);
        welcomeTxt=findViewById(R.id.welcomeTxt);
        chefOrClientTxt= findViewById(R.id.chefOrClientTxt);
        clientBtn = findViewById(R.id.clientBtn);
    }
}
