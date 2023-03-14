package com.example.pertemuan_10.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuan_10.Model.Food;
import com.example.pertemuan_10.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> foodArrayList = new ArrayList<>();
    Context context;

    public FoodAdapter(ArrayList<Food> foodArrayList, Context context) {
        this.foodArrayList = foodArrayList;
        this.context = context;
    }

    public void setFoodArrayList(ArrayList<Food> arrayList){
        this.foodArrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        Food food = foodArrayList.get(position);
        holder.foodName.setText(food.getFoodName());
        holder.foodDesc.setText(food.getFoodDesc());
        holder.foodPrice.setText(food.getFoodPrice());
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodDesc, foodPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tv_foodName);
            foodDesc = itemView.findViewById(R.id.tv_foodDesc);
            foodPrice = itemView.findViewById(R.id.tv_foodPrice);
        }
    }
}
