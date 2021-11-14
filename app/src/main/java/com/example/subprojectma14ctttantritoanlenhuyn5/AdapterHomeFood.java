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

public class AdapterHomeFood extends RecyclerView.Adapter<AdapterHomeFood.HomeFoodHolder> {

    private LinkedList<HomeFood> homeFoods;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public AdapterHomeFood(LinkedList<HomeFood> homeFoods, Context context, Activity activity) {
        this.homeFoods = homeFoods;
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
        HomeFood homeFood = homeFoods.get(position);
        holder.imv_background.setImageResource(homeFood.getImvBackground());
        holder.imv_food.setImageResource(homeFood.getImvFood());
        holder.tv_title.setText(homeFood.getNameTitle());
        holder.tv_title1.setText(homeFood.getNameTitle1());
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return homeFoods.size();
    }

    public class HomeFoodHolder extends RecyclerView.ViewHolder {

        private AdapterHomeFood adapter;
        ImageView imv_background, imv_food;
        TextView tv_title, tv_title1;

        public HomeFoodHolder(@NonNull View itemView, AdapterHomeFood adapterHomeFood) {
            super(itemView);

            imv_background = itemView.findViewById(R.id.imv_backgound);
            imv_food = itemView.findViewById(R.id.imv_food);
            tv_title = itemView.findViewById(R.id.tv_title1);
            tv_title1 = itemView.findViewById(R.id.tv_title);

            this.adapter = adapterHomeFood;
        }
    }
}
