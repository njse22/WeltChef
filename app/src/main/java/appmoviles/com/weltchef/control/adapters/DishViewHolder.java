package appmoviles.com.weltchef.control.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import appmoviles.com.weltchef.R;

public class DishViewHolder extends RecyclerView.ViewHolder {

    private View dishList;
    private TextView dishName;
    private TextView dishCost;
    private TextView dishChef;
    private ImageView dishImage;

    public DishViewHolder(@NonNull View dishList) {
        super(dishList);
        this.dishList = dishList;
        this.dishName = dishList.findViewById(R.id.dishName);
        this.dishCost = dishList.findViewById(R.id.dishCost);
        this.dishChef = dishList.findViewById(R.id.dishChef);
        this.dishImage = dishList.findViewById(R.id.dishImage);

    }

    public View getDishList() {
        return dishList;
    }
}
