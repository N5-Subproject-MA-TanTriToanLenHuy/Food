package com.example.subprojectma14ctttantritoanlenhuyn5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.HomeFoodHolder> {

    private LinkedList<Food> foods;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public FoodAdapter(LinkedList<Food> foods, Context context, Activity activity) {
        this.foods = foods;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HomeFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleview_home, parent, false);
        return new HomeFoodHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFoodHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        Food food = foods.get(position);
        holder.imv_food.setImageResource(food.getImvFood());
        holder.tvName.setText(food.getName());
        holder.tvDescription.setText(food.getDescription());
        holder.tvPrice.setText(String.valueOf(food.getPrice()) + "$");
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class HomeFoodHolder extends RecyclerView.ViewHolder {

        private FoodAdapter adapter;
        private ImageView imv_food;
        private TextView tvName, tvDescription, tvPrice;

        public HomeFoodHolder(@NonNull View view, FoodAdapter foodAdapter) {
            super(view);

            imv_food = view.findViewById(R.id.imv_food);
            tvName = view.findViewById(R.id.tvDescription);
            tvDescription = view.findViewById(R.id.tvName);
            tvPrice = view.findViewById(R.id.tvPrice);

            this.adapter = foodAdapter;
        }
    }
}
