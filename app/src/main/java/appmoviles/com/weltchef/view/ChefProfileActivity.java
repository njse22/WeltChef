package appmoviles.com.weltchef.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.PlateImageAdapter;
import appmoviles.com.weltchef.control.viewcontrollers.ChefProfileController;

public class ChefProfileActivity extends AppCompatActivity{


    private TextView recipes, nameChef, email, description, telephone;
    private ImageView titleapp;
    private ChefProfileController controller;
    private Button weltChef,facebook,instagram,twitter;
    private ImageButton chefPicture;
    private FloatingActionButton mainFab, fabEditProfile, fabAddDish, fabCheckSchedule;
    private boolean isFabMainOpen;
    private RecyclerView listPlates;
    private PlateImageAdapter plateImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_profile);

        this.nameChef = findViewById(R.id.nomUsuarioTxt);
        this.email = findViewById(R.id.correoTxt);
        this.telephone = findViewById(R.id.telefonoTxt);
        this.recipes = findViewById(R.id.recetasTxt);
        this.chefPicture = findViewById(R.id.chefPicture);
        this.titleapp = findViewById(R.id.tituloTxt);
        this.description = findViewById(R.id.descriptionTxt);
        this.listPlates = findViewById(R.id.platesList);
        plateImageAdapter = new PlateImageAdapter();
        listPlates.setAdapter(plateImageAdapter);


        fabEditProfile = (FloatingActionButton) findViewById(R.id.fabEditProfile);
        fabAddDish = (FloatingActionButton) findViewById(R.id.fabAddDish);
        fabCheckSchedule = (FloatingActionButton) findViewById(R.id.fabCheckSchedule);
        mainFab = (FloatingActionButton) findViewById(R.id.mainFab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFabMainOpen){
                    showFabMenu();
                }else{
                    closeFabMenu();
                }
            }
        });

        weltChef = findViewById(R.id.weltChefBtn);
        facebook = findViewById(R.id.facebookBtn);
        instagram = findViewById(R.id.instagramBtn);
        twitter = findViewById(R.id.twitterBtn);

        controller = new ChefProfileController(this);
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

    public ImageButton getPhotochef() {
        return chefPicture;
    }

    public ImageView getTitleapp() {
        return titleapp;
    }

    public ChefProfileController getController() {
        return controller;
    }

    public Button getWeltChef() {
        return weltChef;
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

    public ImageButton getChefPicture() {
        return chefPicture;
    }

    public FloatingActionButton getMainFab() {
        return mainFab;
    }

    public FloatingActionButton getFabEditProfile() {
        return fabEditProfile;
    }

    public FloatingActionButton getFabAddDish() {
        return fabAddDish;
    }

    public FloatingActionButton getFabCheckSchedule() {
        return fabCheckSchedule;
    }

    private void showFabMenu(){

        isFabMainOpen = true;
        fabAddDish.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabCheckSchedule.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fabEditProfile.animate().translationY(-getResources().getDimension(R.dimen.standard_155));

    }

    public PlateImageAdapter getPlateImageAdapter() {
        return plateImageAdapter;
    }

    public RecyclerView getListPlates() {
        return listPlates;
    }

    private void closeFabMenu(){

        isFabMainOpen = false;
        fabAddDish.animate().translationY(0);
        fabCheckSchedule.animate().translationY(0);
        fabEditProfile.animate().translationY(0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode,resultCode, data);
    }
}