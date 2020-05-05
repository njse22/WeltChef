package appmoviles.com.weltchef.control.viewcontrollers;

import android.view.View;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.util.Constans;
import appmoviles.com.weltchef.view.FoodOrderActivity;

public class FoodOrderController implements View.OnClickListener {

    private FoodOrderActivity view;
    private FirebaseDB firebaseDB;
    private Order order;
    private User user;


    public FoodOrderController(FoodOrderActivity view) {
        this.view = view;
        this.firebaseDB = new FirebaseDB();
        this.user = (User) view.getIntent().getExtras().get("user");
        view.getCancel().setOnClickListener(this);
        view.getConfirm().setOnClickListener(this);
    }

    public void init(){
        String id = firebaseDB.createId(Constans.FIREBASE_ORDER_BRANCH);
        order = new Order(
                (ArrayList<Menu>)view.getIntent().getExtras().get("order"),
                id,
                user.getId());

        view.getCost().setText(order.getTotalPrice()+"");
        view.getChef();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                break;
            case R.id.confirm:
                break;
        }
    }
}
