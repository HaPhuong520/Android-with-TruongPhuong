package com.example.updateapiusingretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceholder {
    @GET("api/food?fbclid=IwAR04OxxQ12XTd-OKbWlfJFzpo_Z_IrkPYfiDmVFM-C1vBYLShzeTQm3i144")
    Call<List<Food>> getFood();

    @PUT("foods/{id}")
    Call<Food> putFood(@Path("id") int id, @Body Food food);
}
