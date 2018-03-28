package com.ics.mcb.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ics.mcb.Object.Food;
import com.ics.mcb.R;
import com.ics.mcb.View.DetailFood;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ichsan.Fatiha on 1/4/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    Context context;
    List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.food_name.setText(food.getNama_makanan());
        String str = NumberFormat.getNumberInstance(Locale.UK).format(food.getHarga()).replace(',','.');
        holder.food_price.setText("Rp " + str);
        holder.image_hidden.setText(food.getImage());
        Picasso.with(context).load("http://10.0.2.2/iceresto2/src_admin/image/"+food.getImage()).placeholder(R.drawable.mcb_marker).resize(600,450).centerCrop().into(holder.imageFood);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView food_name;
        TextView food_price;
        TextView image_hidden;
        ImageView imageFood;
        TextView diskon;
        TextView food_diskon;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            food_name = (TextView)itemView.findViewById(R.id.foodTitle);
            image_hidden = (TextView)itemView.findViewById(R.id.image_hidden);
            diskon = (TextView)itemView.findViewById(R.id.diskon);
            food_diskon = (TextView)itemView.findViewById(R.id.food_diskon);
            food_price = (TextView)itemView.findViewById(R.id.food_price);
            imageFood = (ImageView)itemView.findViewById(R.id.imageFood);
        }
        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, DetailFood.class);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,(View)imageFood,"food_transition");
            i.putExtra("session_food_name",food_name.getText().toString().trim());
            i.putExtra("session_food_image",image_hidden.getText().toString().trim());
            context.startActivity(i, activityOptionsCompat.toBundle());
        }
    }
}
