package com.example.updateapiusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    https://haui-hit-food.herokuapp.com/api/food?fbclid=IwAR04OxxQ12XTd-OKbWlfJFzpo_Z_IrkPYfiDmVFM-C1vBYLShzeTQm3i144
    RecyclerView rcvFood;
    FoodAdapter foodAdapter;
    JSONPlaceholder jsonPlaceholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        rcvFood = findViewById(R.id.rcvFood);
        rcvFood.setHasFixedSize(true);
        rcvFood.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://haui-hit-food.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
//        update food
//        updateFood();
//        get food
        getListFood();
    }

    private void getListFood() {
        Call<List<Food>> call = jsonPlaceholder.getFood();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"oh noooo",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Food> foodList = response.body();
                FoodAdapter foodAdapter = new FoodAdapter(foodList,MainActivity.this);
                rcvFood.setAdapter(foodAdapter);


                foodAdapter.setIOnClickItem(new IOnClickItem() {
                    @Override
                    public void iClickItemImage(Food food) {
                        Toast.makeText(MainActivity.this,food.getFoodName(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,MainFood.class);
                        intent.putExtra("imgFood",food.getImgFood());
                        intent.putExtra("foodName",food.getFoodName());
                        intent.putExtra("material",food.getMaterial());
                        intent.putExtra("recipes",food.getRecipes());
                        intent.putExtra("nutrition",food.getNutrition());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFood() {
        Food food = new Food("abc","https://res.cloudinary.com/dzsi7dmey/image/upload/v1625332950/inobghna1nkrc7cbn7c9.png","abcd","abfsdjf","beo");
        Call<Food> call = jsonPlaceholder.putFood(52,food);
        call.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"oh nooooo111",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this,"okkkk111",Toast.LENGTH_SHORT).show();
                List<Food> postFood = new ArrayList<>();
                postFood.add(response.body());
                FoodAdapter foodAdapter = new FoodAdapter(postFood,MainActivity.this);
                rcvFood.setAdapter(foodAdapter);
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                Toast.makeText(MainActivity.this,"update failed",Toast.LENGTH_SHORT).show();

            }
        });

    }

}