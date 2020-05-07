package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.view.DishViewActivity;
import appmoviles.com.weltchef.view.MakeOrderActivity;

public class MakeOrderController implements View.OnClickListener, AdapterView.OnItemSelectedListener, ChildEventListener {

    private MakeOrderActivity activity;
    private FirebaseDB firebaseDB;
    private ArrayList<Menu> menus;

    public MakeOrderController(MakeOrderActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        this.menus = new ArrayList<>();
        init();
    }

    public void init(){
        activity.getSearchService().setOnClickListener(this);
        activity.getSpinnerTypes().setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchService:
                Intent i = new Intent(activity, DishViewActivity.class);
                i.putExtra("menus", menus);
                i.putExtra("user", (User)activity.getIntent().getExtras().get("user"));
                activity.startActivity(i);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getSelectedItemPosition()){
            case Menu.COLOMBIANA:
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
}
