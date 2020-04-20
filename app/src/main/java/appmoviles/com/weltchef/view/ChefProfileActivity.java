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
import appmoviles.com.weltchef.control.viewcontrollers.ChefProfileController;

public class ChefProfileActivity extends AppCompatActivity{

    private TextView infochef, recipes, nameChef, email, description, telephone;
    private ImageView photochef, titleapp, showrecipe;
    private ChefProfileController controller;
    private Button whatsapp,facebook,instagram,twitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_chef);

        this.nameChef = findViewById(R.id.nomUsuarioTxt);
        this.email = findViewById(R.id.correoTxt);
        this.telephone = findViewById(R.id.telefonoTxt);
        this.infochef = findViewById(R.id.infochefTxt);
        this.recipes = findViewById(R.id.recetasTxt);
        this.showrecipe = findViewById(R.id.showrecipView);
        this.photochef = findViewById(R.id.imageView);
        this.titleapp = findViewById(R.id.tituloTxt);

        whatsapp = findViewById(R.id.whatsappBtn);
        facebook = findViewById(R.id.facebookBtn);
        instagram = findViewById(R.id.instagramBtn);
        twitter = findViewById(R.id.twitterBtn);

        controller = new ChefProfileController(this);
    }

    public TextView getInfochef() {
        return infochef;
    }

    public TextView getRecipes() {
        return recipes;
    }

    public TextView getNameChef() {
        return nameChef;
    }

    public TextView getEmail() {
        return email;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getTelephone() {
        return telephone;
    }

    public ImageView getPhotochef() {
        return photochef;
    }

    public ImageView getTitleapp() {
        return titleapp;
    }

    public ImageView getShowrecipe() {
        return showrecipe;
    }

    public ChefProfileController getController() {
        return controller;
    }

    public Button getWhatsapp() {
        return whatsapp;
    }

    public Button getFacebook() {
        return facebook;
    }

    public Button getInstagram() {
        return instagram;
    }

    public Button getTwitter() {
        return twitter;
    }
}
