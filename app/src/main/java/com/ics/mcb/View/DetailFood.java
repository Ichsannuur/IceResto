package com.ics.mcb.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ics.mcb.R;
import com.squareup.picasso.Picasso;

public class DetailFood extends AppCompatActivity {
    TextView food_name;
    FloatingActionButton fab;
    ImageView imageFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_detail_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageFood = (ImageView)findViewById(R.id.imageFood);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        food_name = (TextView)findViewById(R.id.food_title);
        food_name.setText(i.getStringExtra("session_food_name"));
        Picasso.with(DetailFood.this).load("http://10.0.2.2/iceresto2/src_admin/image/"+i.getStringExtra("session_food_image")).resize(600,450).centerCrop().into(imageFood);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Di tambahkan ke favorit", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                changeIconFav();
            }
        });
    }

    private void changeIconFav() {
        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
