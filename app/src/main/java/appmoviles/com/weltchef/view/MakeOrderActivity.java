package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.MakeOrderController;

public class MakeOrderActivity extends AppCompatActivity {

    private CheckedTextView chefHomeCheck;
    private CheckedTextView chefKitchenCheck;
    private CheckedTextView ovenCheck;
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

    public CheckedTextView getChefHomeCheck() {
        return chefHomeCheck;
    }

    public CheckedTextView getChefKitchenCheck() {
        return chefKitchenCheck;
    }

    public CheckedTextView getOvenCheck() {
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
