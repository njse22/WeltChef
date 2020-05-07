package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.interfaces.OnProfileRequest;
import appmoviles.com.weltchef.control.viewcontrollers.FoodOrderController;

public class FoodOrderActivity extends AppCompatActivity {

    private FoodOrderController controller;
    private ImageView foodImage;
    private TextView nameDish;
    private TextView chef;
    private TextView cost;
    private Button cancel;
    private Button confirm;
    private OnProfileRequest listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);

        foodImage = findViewById(R.id.foodImage);
        nameDish = findViewById(R.id.nameDish);
        chef = findViewById(R.id.chef);
        cost = findViewById(R.id.cost);
        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);

        controller = new FoodOrderController(this);
    }

    public ImageView getFoodImage() {
        return foodImage;
    }

    public TextView getChef() {
        return chef;
    }

    public TextView getCost() {
        return cost;
    }

    public Button getCancel() {
        return cancel;
    }

    public Button getConfirm() {
        return confirm;
    }

    public TextView getNameDish() {
        return nameDish;
    }

    public void setListener(OnProfileRequest listener) {
        this.listener = listener;
    }

    public OnProfileRequest getListener() { return listener; }
}
