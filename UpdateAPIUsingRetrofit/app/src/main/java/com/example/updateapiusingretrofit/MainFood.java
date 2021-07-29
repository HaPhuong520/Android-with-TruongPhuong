package com.example.updateapiusingretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainFood extends AppCompatActivity {
    TextView tv_material, tv_nutrition, tv_recipes, tv_food_name;
    ImageView imageFood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_food);
        AnhXa();
        Intent intent=getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(String.valueOf(intent.getStringExtra("foodName")));
        Glide.with(getBaseContext()).load(intent.getStringExtra("imgFood")).into(imageFood);
        tv_recipes.setText(String.valueOf(intent.getStringExtra("recipes")));
        tv_nutrition.setText(String.valueOf(intent.getStringExtra("nutrition")));
        tv_material.setText(String.valueOf(intent.getStringExtra("material")));
        tv_food_name.setText(String.valueOf(intent.getStringExtra("foodName")));

    }

    private void AnhXa() {
        tv_food_name = findViewById(R.id.tv_food_name);
        tv_material = findViewById(R.id.tv_material);
        tv_nutrition = findViewById(R.id.tv_nutrition);
        tv_recipes = findViewById(R.id.tv_recipes);
        imageFood = findViewById(R.id.imageFood);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
