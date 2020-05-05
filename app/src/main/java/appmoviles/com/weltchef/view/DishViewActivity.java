package appmoviles.com.weltchef.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.control.adapters.DishViewAdapter;
import appmoviles.com.weltchef.control.viewcontrollers.DishViewController;

public class DishViewActivity extends AppCompatActivity {

    private Button askDish;
    private RecyclerView recyclerView;
    private DishViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DishViewController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_view);

        askDish = findViewById(R.id.askDish);
        recyclerView = findViewById(R.id.dishList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DishViewAdapter(this);
        recyclerView.setAdapter(adapter);

        controller = new DishViewController(this);

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public DishViewAdapter getAdapter() {
        return adapter;
    }

    public Button getAskDish() {
        return askDish;
    }
}
