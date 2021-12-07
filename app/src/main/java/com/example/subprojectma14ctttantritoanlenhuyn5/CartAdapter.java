package com.example.subprojectma14ctttantritoanlenhuyn5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyCartHolderFavourites> {

    private LinkedList<MyCart> myCarts;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public CartAdapter(LinkedList<MyCart> myCarts, Context context, Activity activity) {
        this.myCarts = myCarts;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyCartHolderFavourites onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_itemcart, parent, false);
        return new MyCartHolderFavourites(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyCartHolderFavourites holder, int position) {
        MyCart myCart = myCarts.get(position);
        holder.tvName.setText(myCart.getName());
        holder.tvPrice.setText(String.valueOf(myCart.getPrice() + "$"));
        Picasso.get().load(myCart.getImvFood()).into(holder.imv_food);
    }

    @Override
    public int getItemCount() {
        return myCarts.size();
    }

    public class MyCartHolderFavourites extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CartAdapter adapter;
        private ImageView imv_food;
        private TextView tvName, tvPrice;


        public MyCartHolderFavourites(View view, CartAdapter cartAdapter)  {
            super(view);

            imv_food = view.findViewById(R.id.image_item_product);
            tvName = view.findViewById(R.id.tv_name_meal);
            tvPrice = view.findViewById(R.id.tv_price_meal);

            view.setOnClickListener(this);
            this.adapter = adapter;

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Screen_CartList.class);
            MyCart myCart = myCarts.get(getLayoutPosition());

            Bundle bundle = new Bundle();
            bundle.putSerializable("cart", myCart);
            intent.putExtra("mycart", bundle);

            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) context, imv_food,
                            ViewCompat.getTransitionName(imv_food));
            context.startActivity(intent, options.toBundle());
        }
    }
}

