package appmoviles.com.weltchef.control.viewcontrollers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.entity.UsersManager;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.CreatePlateActivity;

public class CreatePlateController implements View.OnClickListener {

    private final static String TAG = "CreatePlateController>>>";

    private CreatePlateActivity activity;
    private FirebaseDB firebaseDB;
    private User chef;
    private Menu menu;

    public CreatePlateController(CreatePlateActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        this.menu = new Menu();
        this.chef = (User)activity.getIntent().getExtras().get("user");
        init();
    }

    public void init(){
        activity.getPlateIB().setOnClickListener(this);
        activity.getSaveBtn().setOnClickListener(this);
        activity.getCancelBtn().setOnClickListener(this);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plateIB:

                break;

            case R.id.saveBtn:
                Log.e(TAG, "onClick::saveBtn -> true" );
                int type =  activity.getTypeSpinner().getSelectedItemPosition();
                int price = Integer.parseInt(activity.getPriceET().getText().toString());
                String description =  activity.getDescriptionET().getText().toString();
                String name = activity.getNamePlateET().getText().toString();
                String nameChef = activity.getNameChefTV().getText().toString();
                activity.getPlateIB();
                String id = firebaseDB.createId(Constants.FIREBASE_MENU_BRANCH);
                menu = new Menu(type, price, description, name, id, chef.getId());
                firebaseDB.sendInfo(menu,menu.getId(), Constants.FIREBASE_MENU_BRANCH);
                Log.e(TAG, "onClick::firebaseDB -> true" );
                break;

            case R.id.cancelBtn:

                break;
        }
    }
}
