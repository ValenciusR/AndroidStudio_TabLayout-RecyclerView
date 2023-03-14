package com.example.pertemuan_10;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pertemuan_10.Controller.FoodAdapter;
import com.example.pertemuan_10.Model.Food;

import java.util.ArrayList;

public class FirstFragment extends Fragment {
    TextView foodName, foodDesc, foodPrice;
    FoodAdapter foodAdapter;
    ArrayList<Food> foodArrayList;
    RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        foodName = view.findViewById(R.id.tv_foodName);
        foodDesc = view.findViewById(R.id.tv_foodDesc);
        foodPrice = view.findViewById(R.id.tv_foodPrice);
        recyclerView = view.findViewById(R.id.rv_foodList);

        foodArrayList = new ArrayList<>();
//        foodArrayList.add(new Food("Makanan 1", "Makanan enak","10000"));
//        foodArrayList.add(new Food("Makanan 2", "Makanan enak","20000"));
//        foodArrayList.add(new Food("Makanan 3", "Makanan enak","30000"));

        foodAdapter = new FoodAdapter(foodArrayList, this.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(foodAdapter);

        return view;
    }

    public void addNewFood(String foodName, String foodDesc, String foodPrice){
        foodArrayList.add(new Food(foodName, foodDesc, foodPrice));
        foodAdapter.setFoodArrayList(foodArrayList);
    }
}