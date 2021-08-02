package com.example.app.mainlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.R;
import com.example.app.food.AddFood;
import com.example.app.food.Food;
import com.example.app.food.FoodAdapter;
import com.example.app.food.IOnClickMenuItem;
import com.example.app.food.MainFood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class    ListFood extends AppCompatActivity {
    private static final String URL = "https://haui-hit-food.herokuapp.com/api/food";
    RecyclerView reImageFood;
    JSONArray jsonArray;
    SearchView searchView;
    List<Food> listFood=new ArrayList<>();
    FoodAdapter foodAdapter;
    TextView tvChatBeo, tvChatDam, tvVtmKc, tvChatBotDuong;
    ImageView addFood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        getSupportActionBar().setTitle("Món ăn ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AnhXa();
        reImageFood=findViewById(R.id.RecycleviewFood);
        RequestQueue requestQueue = Volley.newRequestQueue(ListFood.this);
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
//        listFood.add(new Food(R.drawable.diet,"abc","abc","abc","axxx"));
        foodAdapter=new FoodAdapter(listFood, ListFood.this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectFood = jsonArray.getJSONObject(i);
                        String foodName = jsonObjectFood.getString("foodName");
                        String img = jsonObjectFood.getString("img");
                        String material = jsonObjectFood.getString("material");
                        String recipes = jsonObjectFood.getString("recipes");
                        String nutrition = jsonObjectFood.getString("nutrition");
                        listFood.add(new Food(img, foodName, material, recipes, nutrition));
                        listFood.size();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListFood.this, RecyclerView.VERTICAL, false);
                        reImageFood.setLayoutManager(layoutManager);
                        reImageFood.setAdapter(foodAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest);



        foodAdapter.setmIOnClickMenuItem(new IOnClickMenuItem() {
            @Override
            public void iClickImageFood(Food item) {
                Toast.makeText(ListFood.this, item.getFoodName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ListFood.this, MainFood.class);
                intent.putExtra("imageLink",item.getImage());
                intent.putExtra("FoodName",item.getFoodName());
                intent.putExtra("Material",item.getMaterial());
                intent.putExtra("Recipes",item.getRecipes());
                intent.putExtra("Nutrition",item.getNutrition());
                startActivity(intent);
            }
        });


        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFood.this, AddFood.class);

                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                foodAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    void AnhXa(){

        addFood=findViewById(R.id.addFood);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}