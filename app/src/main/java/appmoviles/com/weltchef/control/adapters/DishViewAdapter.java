package appmoviles.com.weltchef.control.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.view.DishViewActivity;

public class DishViewAdapter extends RecyclerView.Adapter<DishViewHolder> {

    private DishViewActivity context;

    //Dish array

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dish_view_holder, parent,false);

        DishViewHolder holder = new DishViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
