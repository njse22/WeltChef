package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.view.DishViewActivity;
import appmoviles.com.weltchef.view.FoodOrderActivity;

public class DishViewController implements
        View.OnClickListener,
        RecyclerTouchListener.ClickListener,
        ValueEventListener {

    private final static String TAG = "DishViewController";

    private DishViewActivity activity;
    private ArrayList<Menu> menus;
    private User user;
    private Order order;
    private FirebaseDB firebaseDB;

    public DishViewController(DishViewActivity activity) {
        this.activity = activity;
        this.firebaseDB = new FirebaseDB();
        init();
    }

    public void init(){
        activity.getAskDish().setOnClickListener(this);
        activity.getRecyclerView().addOnItemTouchListener(new RecyclerTouchListener(
                activity.getApplicationContext(), activity.getRecyclerView(), this
        ));
        menus = (ArrayList<Menu>)activity.getIntent().getExtras().get("menus");
        order = (Order) activity.getIntent().getExtras().get("order");
        order.setPlates(menus);
        user = (User) activity.getIntent().getExtras().get("user");

        firebaseDB.searchOrder(user.getId());
        firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);

        activity.runOnUiThread(
                () -> {
                    for (Menu item : menus) {
                        Log.e(TAG, "init::item -> " + item);
                        activity.getAdapter().getDishes().add(item);
                        activity.getAdapter().notifyDataSetChanged();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.askDish:
                Intent i = new Intent(activity, FoodOrderActivity.class);
                i.putExtra("order", order);
                i.putExtra("user", user);
                i.putExtra("body", (String) activity.getIntent().getExtras().get("body"));
                activity.startActivity(i);
                activity.finish();
                break;
        }
    }

    @Override
    public void onClick(View view, int position) {
        order.getPlates().add(menus.get(position));
        firebaseDB.sendInfo(order, order.getId(), Constants.FIREBASE_ORDER_BRANCH);
        Intent i = new Intent(activity, FoodOrderActivity.class);
        i.putExtra("order", order);
        i.putExtra("user", user);
        i.putExtra("body", (String) activity.getIntent().getExtras().get("body"));
        activity.startActivity(i);
        activity.finish();
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getChildrenCount() == 0){
            order = new Order(menus, firebaseDB.createId(Constants.FIREBASE_ORDER_BRANCH), user.getId());
            firebaseDB.sendInfo(order, order.getId(), Constants.FIREBASE_ORDER_BRANCH);
        }
        else {
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //order = data.getValue(Order.class);
                break;
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
