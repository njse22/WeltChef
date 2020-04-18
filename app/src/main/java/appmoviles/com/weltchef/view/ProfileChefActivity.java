package appmoviles.com.weltchef.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.app.MainActivity;

public class ProfileChefActivity  extends AppCompatActivity{

    private EditText nombreUsuario, correo, telefono, descripcion;
    private TextView infochef;
    private Button receta1;
    private ImageView showImageView, imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_chef);
        nombreUsuario = findViewById(R.id.nomUsuarioTxt);
        correo = findViewById(R.id.correoTxt);
        telefono = findViewById(R.id.telefonoTxt);
        infochef = findViewById(R.id.infochefTxt);
    }


}
