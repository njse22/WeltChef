package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.PlateImageAdapter;
import appmoviles.com.weltchef.control.viewcontrollers.DishInfoController;


public class DishInfoActivity extends AppCompatActivity {

    private DishInfoController controller;

    private ImageButton chefPicture;
    private TextView nameChef;
    private TextView email;
    private TextView description;
    private TextView telephone;
    private RecyclerView listPlates;
    private PlateImageAdapter plateImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_info);

        this.nameChef = findViewById(R.id.nomUsuarioTxt);
        this.email = findViewById(R.id.correoTxt);
        this.telephone = findViewById(R.id.telefonoTxt);
        this.chefPicture = findViewById(R.id.chefPicture);
        this.description = findViewById(R.id.descriptionTxt);

        this.listPlates = findViewById(R.id.platesList);
        this.plateImageAdapter = new PlateImageAdapter(this);
        this.listPlates.setLayoutManager(new GridLayoutManager(this,3));
        this.listPlates.setAdapter(plateImageAdapter);

        controller = new DishInfoController(this);

    }

    public ImageButton getChefPicture() {
        return chefPicture;
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

    public RecyclerView getListPlates() {
        return listPlates;
    }

    public PlateImageAdapter getPlateImageAdapter() {
        return plateImageAdapter;
    }
}
