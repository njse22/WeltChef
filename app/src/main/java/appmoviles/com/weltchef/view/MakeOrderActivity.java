package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.MakeOrderController;

public class MakeOrderActivity extends AppCompatActivity {

    private CheckBox chefHomeCheck;
    private CheckBox chefKitchenCheck;
    private CheckBox ovenCheck;
    private EditText numPeopleET;
    private Button searchService;
    private Spinner spinnerTypes;
    private MakeOrderController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        chefHomeCheck = findViewById(R.id.chefHomeCheck);
        chefKitchenCheck = findViewById(R.id.chefKitchenCheck);
        ovenCheck = findViewById(R.id.ovenCheck);

        numPeopleET = findViewById(R.id.numPeopleET);
        searchService = findViewById(R.id.searchService);
        spinnerTypes = findViewById(R.id.spinnerTypes);

        controller = new MakeOrderController(this);
    }


    public CheckBox getChefHomeCheck() {
        return chefHomeCheck;
    }

    public CheckBox getChefKitchenCheck() {
        return chefKitchenCheck;
    }

    public CheckBox getOvenCheck() {
        return ovenCheck;
    }

    public EditText getNumPeopleET() {
        return numPeopleET;
    }

    public Button getSearchService() {
        return searchService;
    }

    public Spinner getSpinnerTypes() {
        return spinnerTypes;
    }

    public void setSpinnerTypes(Spinner spinnerTypes) {
        this.spinnerTypes = spinnerTypes;
    }

}
