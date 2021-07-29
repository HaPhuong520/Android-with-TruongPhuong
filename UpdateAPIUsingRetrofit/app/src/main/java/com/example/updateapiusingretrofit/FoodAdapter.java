package com.example.updateapiusingretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter <FoodAdapter.ViewHolder>{

    List<Food> list = new ArrayList<>();
    Context context;
    IOnClickItem iOnClickItem;
    public void setIOnClickItem(IOnClickItem iOnClickItem){
        this.iOnClickItem = iOnClickItem;
    }

    public FoodAdapter(List<Food> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.demo_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        Food food = list.get(position);
        Glide.with(context).load(food.getImgFood()).into(holder.image_Food);
        holder.foodName.setText(String.valueOf(food.getFoodName()));
        holder.image_Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               iOnClickItem.iClickItemImage(food);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_Food;
        TextView foodName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Food = itemView.findViewById(R.id.img_food_demo);
            foodName = itemView.findViewById(R.id.tvFoodName);
        }
    }
}
