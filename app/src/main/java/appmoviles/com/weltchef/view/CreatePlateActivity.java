package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.CreatePlateController;

public class CreatePlateActivity extends AppCompatActivity {

    private EditText namePlateET;
    private TextView nameChefTV;
    private EditText priceET;
    private Spinner typeSpinner;
    private TextView infoPlateTV;
    private EditText descriptionET;
    private Button saveBtn;
    private Button cancelBtn;
    private ImageButton plateIB;
    private CreatePlateController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plate);
        namePlateET = findViewById(R.id.namePlateET);
        nameChefTV = findViewById(R.id.nameChefET);
        priceET = findViewById(R.id.priceET);
        typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.types_array,
                R.layout.activity_create_plate);
        adapter.setDropDownViewResource(R.layout.activity_create_plate);
        typeSpinner.setAdapter(adapter);
        infoPlateTV = findViewById(R.id.priceET);
        descriptionET = findViewById(R.id.descriptionET);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        plateIB = findViewById(R.id.plateIB);
        controller = new CreatePlateController(this);

    }

    public EditText getNamePlateET() {
        return namePlateET;
    }

    public void setNamePlateET(EditText namePlateET) {
        this.namePlateET = namePlateET;
    }

    public TextView getNameChefTV() {
        return nameChefTV;
    }

    public void setNameChefTV(TextView nameChefTV) {
        this.nameChefTV = nameChefTV;
    }

    public EditText getPriceET() {
        return priceET;
    }

    public void setPriceET(EditText priceET) {
        this.priceET = priceET;
    }

    public Spinner getTypeET() {
        return typeSpinner;
    }

    public void setTypeET(Spinner typeSpinner) {
        this.typeSpinner = typeSpinner;
    }

    public TextView getInfoPlateTV() {
        return infoPlateTV;
    }

    public void setInfoPlateTV(TextView infoPlateTV) {
        this.infoPlateTV = infoPlateTV;
    }

    public EditText getDescriptionET() {
        return descriptionET;
    }

    public void setDescriptionET(EditText descriptionET) {
        this.descriptionET = descriptionET;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public ImageButton getPlateIB() {
        return plateIB;
    }

    public void setPlateIB(ImageButton plateIB) {
        this.plateIB = plateIB;
    }
}
