package com.example.app.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter <FoodAdapter.ViewHolder>{
    List<Food> list = new ArrayList<>();
    List<Food> listFilter = new ArrayList<>();
    Context context;
    IOnClickMenuItem mIOnClickMenuItem;

    public void setmIOnClickMenuItem(IOnClickMenuItem mIOnClickMenuItem) {
        this.mIOnClickMenuItem = mIOnClickMenuItem;
    }
    public FoodAdapter(List<Food> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_demo, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food item = list.get(position);
        Glide.with(context).load(item.getImage()).into(holder.imageFood);
        holder.TitleBook.setText(String.valueOf(item.getFoodName()));
        holder.imageFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        mIOnClickMenuItem.iClickImageFood(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                String StrS=Vietnamese.removeAccent(strSearch.trim());
                if(StrS.isEmpty()){
                    list=listFilter;
                }
                else{
                    List<Food> temp=new ArrayList<>();
                    for (Food x:list){
                        if(Vietnamese.removeAccent(x.FoodName.toLowerCase().trim()).contains(StrS.toLowerCase())){
                            temp.add(x);
                        }

                    }
                    list=temp;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=list;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list=(List<Food>)results.values;
                notifyDataSetChanged();
            }

        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageFood;
        TextView TitleBook;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFood = itemView.findViewById(R.id.img_menu_book);
            TitleBook = itemView.findViewById(R.id.name_of_book);
        }
    }
}