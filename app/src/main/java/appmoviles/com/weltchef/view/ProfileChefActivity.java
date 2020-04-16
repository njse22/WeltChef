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

public class ProfileChefActivity extends Fragment implements View.OnClickListener {

    private EditText nombreUsuario, correo, telefono, descripcion, receta;
    private TextView titulo, infochef;
    private Button receta1;
    private ImageView showImageView, imageView;
    private OnDataSubmitted listener;

    public ProfileChefActivity(){

    }

    public void setListener(MainActivity listener){
        this.listener = (OnDataSubmitted) listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_chef, container, false);

        nombreUsuario = view.findViewById(R.id.nomUsuarioTxt);
        titulo = view.findViewById(R.id.tituloTxt);
        correo = view.findViewById(R.id.correoTxt);
        telefono = view.findViewById(R.id.telefonoTxt);
        infochef = view.findViewById(R.id.infochefTxt);
        receta = view.findViewById(R.id.recetasTxt);

        view.findViewById(R.id.receta1Btn).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.receta1Btn:
                if(listener != null){
                    listener.onData(this);
                }
                break;
        }

    }
}
