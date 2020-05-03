package appmoviles.com.weltchef.control.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.db.FirebaseDB;
import appmoviles.com.weltchef.entity.Menu;

public class DishViewAdapter extends RecyclerView.Adapter<DishViewHolder> {

    private ArrayList<Menu> dishes;
    private Context context;
    private DishViewHolder holder;

    public DishViewAdapter(Context context) {
        this.dishes = new ArrayList<>(10);
        this.context = context;
    }

    public ArrayList<Menu> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Menu> dishes) {
        this.dishes = dishes;
    }

    public DishViewHolder getHolder() {
        return holder;
    }

    public Object getItem(int position){return dishes.get(position); }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dish_view_holder, parent,false);
        holder = new DishViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        TextView dishName = holder.getDishList().findViewById(R.id.dishName);
        TextView dishCost = holder.getDishList().findViewById(R.id.dishCost);
        TextView dishChef = holder.getDishList().findViewById(R.id.dishChef);
        ImageView dishImage = holder.getDishList().findViewById(R.id.dishImage);

        dishName.setText(dishes.get(position).getName());
        dishCost.setText("" +dishes.get(position).getPrice());
        dishChef.setText( dishes.get(position).getChefId() );

        holder.getDishList().setActivated(true);

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }


}
