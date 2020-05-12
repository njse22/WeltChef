package appmoviles.com.weltchef.control.viewcontrollers;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Order;
import appmoviles.com.weltchef.entity.User;
import appmoviles.com.weltchef.view.ClientProfileActivity;
import appmoviles.com.weltchef.view.FoodOrderActivity;

public class FoodOrderController implements View.OnClickListener, ValueEventListener {

    private FoodOrderActivity view;
    private FirebaseDB firebaseDB;
    private Order order;
    private User user;
    private User chef;

    public FoodOrderController(FoodOrderActivity view) {
        this.view = view;
        this.firebaseDB = new FirebaseDB();
        init();
    }

    public void init(){
        this.user = (User) view.getIntent().getExtras().get("user");
        this.order = (Order) view.getIntent().getExtras().get("order");
        view.getCancel().setOnClickListener(this);
        view.getConfirm().setOnClickListener(this);
        int index = order.getPlates().size()-1;

        view.getNameDish().setText(order.getPlates().get(index).getName());
        view.getCost().setText(order.getTotalPrice()+"");

        firebaseDB.searchUserByid(order.getPlates().get(index).getChefId());
        firebaseDB.getQuerySearch().addListenerForSingleValueEvent(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:

                break;
            case R.id.confirm:
                Intent confirm = new Intent(view, ClientProfileActivity.class);
                confirm.putExtra("user", user);
                confirm.putExtra("order", order);
                view.startActivity(confirm);
                view.finish();
                break;
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getChildrenCount() == 0){

        }else {
            for (DataSnapshot data: dataSnapshot.getChildren()) {
                chef = (User)dataSnapshot.getValue(User.class);
                break;
            }
            view.getChef().setText(chef.getName());
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) { }

}
