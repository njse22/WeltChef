package appmoviles.com.weltchef.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.PlateImageAdapter;
import appmoviles.com.weltchef.control.viewcontrollers.ChefProfileController;

public class ChefProfileActivity extends AppCompatActivity {

    private ChefProfileController controller;
    private TextView recipes;
    private TextView nameChef;
    private TextView email;
    private TextView description;
    private TextView telephone;
    private Button singoutBtn;
    private ImageView titleapp;
    private ImageButton chefPicture;
    private FloatingActionButton mainFab;
    private FloatingActionButton fabEditProfile;
    private FloatingActionButton fabAddDish;
    private FloatingActionButton  fabCheckSchedule;
    private boolean isFabMainOpen;
    private RecyclerView listPlates;
    private PlateImageAdapter plateImageAdapter;
    private ListView listChefs;

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
        this.singoutBtn = findViewById(R.id.singoutBtn);

        this.listPlates = findViewById(R.id.platesList);
        this.plateImageAdapter = new PlateImageAdapter(this);
        this.listPlates.setLayoutManager(new GridLayoutManager(this,3));
        this.listPlates.setAdapter(plateImageAdapter);

        fabEditProfile = findViewById(R.id.fabEditProfile);
        fabAddDish = findViewById(R.id.fabAddDish);
        fabCheckSchedule = findViewById(R.id.fabCheckSchedule);
        mainFab = findViewById(R.id.mainFab);

        mainFab.setOnClickListener(
                v -> {
                    if (!isFabMainOpen) {
                        showFabMenu();
                    } else {
                        closeFabMenu();
                    }
                }
        );

        controller = new ChefProfileController(this);
    }

    public Button getSingoutBtn() {
        return singoutBtn;
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

    public ImageView getTitleapp() {
        return titleapp;
    }

    public ChefProfileController getController() {
        return controller;
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

    private void showFabMenu() {

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

    private void closeFabMenu() {
        isFabMainOpen = false;
        fabAddDish.animate().translationY(0);
        fabCheckSchedule.animate().translationY(0);
        fabEditProfile.animate().translationY(0);

    }


    public static class PhotoViewFragmentAC extends DialogFragment {

        private View.OnClickListener listener;
        private Dialog dialog;
        private ImageView imageView;

        public PhotoViewFragmentAC(View.OnClickListener listener) {
            this.listener = listener;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.fragment_photo_view, null);
            imageView = view.findViewById(R.id.picture);
            builder.setView(view);
            this.dialog = builder.create();
            return this.dialog;
        }

    }

}