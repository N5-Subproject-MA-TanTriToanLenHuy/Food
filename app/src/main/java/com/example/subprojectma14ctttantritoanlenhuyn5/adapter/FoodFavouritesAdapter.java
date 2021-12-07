package com.example.subprojectma14ctttantritoanlenhuyn5.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subprojectma14ctttantritoanlenhuyn5.R;
import com.example.subprojectma14ctttantritoanlenhuyn5.Screen_FoodDetail;
import com.example.subprojectma14ctttantritoanlenhuyn5.entity.Food;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class FoodFavouritesAdapter extends RecyclerView.Adapter<FoodFavouritesAdapter.FoodFavouritesHolder> implements Filterable {

    private LinkedList<Food> foods;
    private LinkedList<Food> foodsOld;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public FoodFavouritesAdapter(LinkedList<Food> foods, Context context, Activity activity) {
        this.foods = foods;
        this.foodsOld = foods;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FoodFavouritesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleview_food_favorites, parent, false);
        return new FoodFavouritesHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodFavouritesHolder holder, int position) {
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty()){
                    foods = foodsOld;
                }else {
                    LinkedList<Food> foodList = new LinkedList<>();
                    for(Food food : foodsOld){
                        if(food.getName().toLowerCase().contains(s.toLowerCase())){
                            foodList.add(food);
                        }
                    }

                    foods = foodList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = foods;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                foods = (LinkedList<Food>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class FoodFavouritesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private FoodFavouritesAdapter adapter;
        private ImageView imv_food;
        private TextView tvName;

        public FoodFavouritesHolder(@NonNull View view, FoodFavouritesAdapter adapter) {
            super(view);

            imv_food = view.findViewById(R.id.imv_foodFavourites);
            tvName = view.findViewById(R.id.tvNameFavourites);

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
