package com.ics.mcb.API;

import com.ics.mcb.Object.Value;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ichsan.Fatiha on 1/4/2018.
 */

public interface McbAPI {
    @GET("show_food.php")
    Call<Value> show_food();
}
