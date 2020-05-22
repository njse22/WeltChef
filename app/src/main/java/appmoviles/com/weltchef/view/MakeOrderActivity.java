package appmoviles.com.weltchef.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.MakeOrderController;

public class MakeOrderActivity extends AppCompatActivity {

    private CheckBox chefHomeCheck;
    private CheckBox chefKitchenCheck;
    private CheckBox ovenCheck;
    private EditText numPeopleET;
    private EditText placeET;
    private Button searchService;
    private Spinner spinnerTypes;
    private CalendarView calendar;
    private MakeOrderController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        chefHomeCheck = findViewById(R.id.chefHomeCheck);
        chefKitchenCheck = findViewById(R.id.chefKitchenCheck);
        ovenCheck = findViewById(R.id.ovenCheck);

        numPeopleET = findViewById(R.id.numPeopleET);
        placeET = findViewById(R.id.placeET);
        searchService = findViewById(R.id.searchService);
        spinnerTypes = findViewById(R.id.spinnerTypes);

        calendar = findViewById(R.id.calendarSelect);


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

    public EditText getPlaceET() {
        return placeET;
    }

    public Button getSearchService() {
        return searchService;
    }

    public Spinner getSpinnerTypes() {
        return spinnerTypes;
    }

    public CalendarView getCalendar() {
        return calendar;
    }
}
