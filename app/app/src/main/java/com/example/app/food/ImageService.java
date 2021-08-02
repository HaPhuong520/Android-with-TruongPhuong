package com.example.app.food;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {

    public static final String  DOMAIN="https://haui-hit-food.herokuapp.com/api/";
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ImageService imageservice = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ImageService.class);
    @POST("food")
    @Multipart
    Call<Food> addNewFood(
            @Part(Constants.KEY_foodName) RequestBody foodName,
            @Part MultipartBody.Part img,
            @Part(Constants.KEY_material) RequestBody material,
            @Part(Constants.KEY_recipes) RequestBody recipes,
            @Part(Constants.KEY_nutrition) RequestBody nutrition) ;
}
