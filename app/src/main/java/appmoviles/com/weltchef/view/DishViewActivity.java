package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.viewcontrollers.DishViewController;

public class DishViewActivity extends AppCompatActivity {

    private DishViewController controller;

    private RecyclerView dishList;
    private DishViewAdapter adapter;
    private Button askDish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_view);

        dishList = findViewById(R.id.dishList);
        askDish = findViewById(R.id.askDish);
    }
}
