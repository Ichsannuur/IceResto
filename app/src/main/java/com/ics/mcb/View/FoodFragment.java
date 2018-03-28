package com.ics.mcb.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ics.mcb.API.McbAPI;
import com.ics.mcb.Adapter.FoodAdapter;
import com.ics.mcb.Object.Food;
import com.ics.mcb.Object.Value;
import com.ics.mcb.R;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {
    RecyclerView recyclerView;
    FoodAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    SpotsDialog spotsDialog;
    private List<Food> foodList = new ArrayList<>();
    public static final String BASE_URL = "http://10.0.2.2/iceresto2/mobileAPI/";
    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.refresh);
        //Set RecyclerView
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spotsDialog = new SpotsDialog(getContext());
        spotsDialog.show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadFood();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                spotsDialog.show();
                loadFood();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void loadFood() {
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        McbAPI api = retrofit.create(McbAPI.class);
        Call<Value> call = api.show_food();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")){
                    spotsDialog.dismiss();
                    foodList = response.body().getFoodList();
                    adapter = new FoodAdapter(getContext(),foodList);
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(getContext(), "Tidak ada data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
