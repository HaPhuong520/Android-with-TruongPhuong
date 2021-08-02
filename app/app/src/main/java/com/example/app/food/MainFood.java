package com.example.app.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.app.R;

public class MainFood extends AppCompatActivity {
    TextView TenMon,NguyenLieu,CachCheBien,ChatDinhDuong;
    ImageView imageFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        AnhXa();
        Intent intent=getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(String.valueOf(intent.getStringExtra("FoodName")));

        Glide.with(getBaseContext()).load(intent.getStringExtra("imageLink")).into(imageFood);
        TenMon.setText(String.valueOf(intent.getStringExtra("FoodName")));
        NguyenLieu.setText(String.valueOf(intent.getStringExtra("Material")));
        CachCheBien.setText(String.valueOf(intent.getStringExtra("Recipes")));
        ChatDinhDuong.setText(String.valueOf(intent.getStringExtra("Nutrition")));

    }
    void AnhXa()
    {
        TenMon=findViewById(R.id.TenMon);
        NguyenLieu=findViewById(R.id.NguyenLieu);
        CachCheBien=findViewById(R.id.CachCheBien);
        ChatDinhDuong=findViewById(R.id.ChatDinhDuong);

        imageFood=findViewById(R.id.image);
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