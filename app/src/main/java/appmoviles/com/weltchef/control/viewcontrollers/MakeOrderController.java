package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.view.DishViewActivity;
import appmoviles.com.weltchef.view.MakeOrderActivity;

public class MakeOrderController implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        ChildEventListener,
        CompoundButton.OnCheckedChangeListener, CalendarView.OnDateChangeListener {

    private final static String TAG = "MakeOrderController";

    private MakeOrderActivity activity;
    private FirebaseDB firebaseDB;
    private ArrayList<Menu> menus;
    private Order order;
    private String body;

    public MakeOrderController(MakeOrderActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        this.menus = new ArrayList<>();
        this.order = new Order();
        init();
    }

    public void init(){
        activity.getSearchService().setOnClickListener(this);
        activity.getSpinnerTypes().setOnItemSelectedListener(this);
        activity.getChefHomeCheck().setOnCheckedChangeListener(this);
        activity.getCalendar().setOnDateChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchService:
                Intent i = new Intent(activity, DishViewActivity.class);
                i.putExtra("menus", menus);
                i.putExtra("order", order);
                i.putExtra("user", (User)activity.getIntent().getExtras().get("user"));
                i.putExtra("body", body);
                activity.startActivity(i);
                activity.finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemSelected::parent.getSelectedItemPosition() -> " + (parent.getSelectedItemPosition()));
        switch (parent.getSelectedItemPosition()){
            case Menu.COLOMBIANA:
                Log.e(TAG, "onItemSelected::Menu.COLOMBIANA -> true ");
                firebaseDB.getMenus(Menu.COLOMBIANA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
            case Menu.MEXICANA:
                firebaseDB.getMenus(Menu.MEXICANA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
            case Menu.MEDITERRANEA:
                firebaseDB.getMenus(Menu.MEDITERRANEA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
            case Menu.CHINA:
                firebaseDB.getMenus(Menu.CHINA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
            case Menu.JAPONESA:
                firebaseDB.getMenus(Menu.JAPONESA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
            case Menu.FRANCESA:
                firebaseDB.getMenus(Menu.FRANCESA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
            case Menu.ITALIANA:
                firebaseDB.getMenus(Menu.ITALIANA);
                firebaseDB.getQuerySearch().addChildEventListener(this);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Menu menu = dataSnapshot.getValue(Menu.class);
        menus.add(menu);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            body =  "Fecha: " + dayOfMonth + " / " + month + " / " + year + "\n" +
                    "Número de personas: " + activity.getNumPeopleET().getText().toString() + "\n" +
                    "Lugar: " + activity.getPlaceET().getText().toString();

            order.setNumPersonas(Integer.parseInt(activity.getNumPeopleET().getText().toString()));
    }

}
