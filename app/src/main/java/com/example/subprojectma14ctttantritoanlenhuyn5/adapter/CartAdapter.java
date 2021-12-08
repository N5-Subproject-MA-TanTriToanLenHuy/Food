package com.example.subprojectma14ctttantritoanlenhuyn5.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subprojectma14ctttantritoanlenhuyn5.entity.MyCart;
import com.example.subprojectma14ctttantritoanlenhuyn5.R;
import com.example.subprojectma14ctttantritoanlenhuyn5.Screen_CartList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
        double p = (double) Math.round(myCart.getPrice() * 1000) / 1000;
        holder.tvPrice.setText(String.valueOf(p));
        Picasso.get().load(myCart.getImvFood()).into(holder.imv_food);
        holder.tvQuantity.setText(String.valueOf(myCart.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return myCarts.size();
    }

    public class MyCartHolderFavourites extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CartAdapter adapter;
        private ImageView imv_food;
        private TextView tvName, tvPrice;
        Button btnDelete;

        TextView tvQuantity;

        public MyCartHolderFavourites(View view, CartAdapter cartAdapter) {
            super(view);

            imv_food = view.findViewById(R.id.image_item_product);
            tvName = view.findViewById(R.id.tv_name_meal);
            tvQuantity = view.findViewById(R.id.tv_quantity_item);
            tvPrice = view.findViewById(R.id.tv_price_meal);
            btnDelete = view.findViewById(R.id.btn_remove_item);

            view.setOnClickListener(this);

            String url = "https://sub-ma-food.herokuapp.com/api/cart/";
            this.adapter = adapter;

//--------------------DEL Function--------------------------------
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    MyCart element = myCarts.get(position);


                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are You Sure To Delete Cart");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    DeleteDataToJsonAPI(url, element.getId());

                                }
                            });

                    builder.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });





//------------End Delete Function---------------

        }


//------------End MyCartHolderFavourites----------------


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
//        --------

        private void DeleteDataToJsonAPI(String url, int id) {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.DELETE, url + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    myCarts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    ((Activity)context).recreate();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "ERROOR", Toast.LENGTH_SHORT).show();
                    VolleyLog.d("TAG", "Error: " + error.getMessage());
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }

    }


}

