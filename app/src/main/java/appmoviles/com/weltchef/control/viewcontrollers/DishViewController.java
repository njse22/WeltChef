package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.view.DishViewActivity;
import appmoviles.com.weltchef.view.FoodOrderActivity;

public class DishViewController implements View.OnClickListener, RecyclerTouchListener.ClickListener {

    private DishViewActivity activity;
    private ArrayList<Menu> menus;
    private ArrayList<Menu> order;

    public DishViewController(DishViewActivity activity) {
        this.activity = activity;
        this.order = new ArrayList<>();
        init();
    }

    public void init(){
        activity.getAskDish().setOnClickListener(this);
        activity.getRecyclerView().addOnItemTouchListener(new RecyclerTouchListener(
                activity.getApplicationContext(), activity.getRecyclerView(), this
        ));
        menus = (ArrayList<Menu>)activity.getIntent().getExtras().get("menus");

        activity.runOnUiThread(
                () -> {
                    for (Menu item : menus) {
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
                i.putExtra("user", (User)activity.getIntent().getExtras().get("user"));
                activity.startActivity(i);
                break;
        }

    }

    @Override
    public void onClick(View view, int position) {
        order.add(menus.get(position));

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
