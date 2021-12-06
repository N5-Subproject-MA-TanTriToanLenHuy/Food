package com.example.subprojectma14ctttantritoanlenhuyn5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class FoodAdapterTrending extends RecyclerView.Adapter<FoodAdapterTrending.FoodHolderTrending> {

    private LinkedList<Food> foods;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public FoodAdapterTrending(LinkedList<Food> foods, Context context, Activity activity) {
        this.foods = foods;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FoodHolderTrending onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleview_home, parent, false);
        return new FoodHolderTrending(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolderTrending holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        Food food = foods.get(position);
        holder.tvName.setText(food.getName());
        holder.itemView.startAnimation(animation);
        Picasso.get().load(food.getImvFood()).into(holder.imv_food);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodHolderTrending extends RecyclerView.ViewHolder implements View.OnClickListener{

        private FoodAdapterTrending adapter;
        private ImageView imv_food;
        private TextView tvName;

        public FoodHolderTrending(@NonNull View view, FoodAdapterTrending adapter) {
            super(view);

            imv_food = view.findViewById(R.id.imv_food);
            tvName = view.findViewById(R.id.tvName);

            view.setOnClickListener(this);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Screen_FoodDetail.class);
            Food food = foods.get(getLayoutPosition());

            Bundle bundle = new Bundle();
            bundle.putSerializable("food", food);
            intent.putExtra("entity", bundle);

            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) context, imv_food,
                            ViewCompat.getTransitionName(imv_food));
            context.startActivity(intent, options.toBundle());
        }
    }
}
