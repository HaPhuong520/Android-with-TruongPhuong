package com.example.app.mainlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.modeeat.Adults;
import com.example.app.modeeat.Chidren;
import com.example.app.modeeat.Erderly;
import com.example.app.modeeat.LuaChonBenh;

public class ModeEat extends AppCompatActivity {
    ImageView imgChildren,imgAdults,imgElderly,imgSick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_eat);
        getSupportActionBar().setTitle("Chế độ ăn ");
//        Spannable text = new SpannableString(actionBar.getTitle());
//        text.setSpan(new ForegroundColorSpan(Color.BLUE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        actionBar.setTitle(text);

        AnhXa();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this, Chidren.class);
                startActivity(intent);
            }
        });
        imgAdults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this, Adults.class);
                startActivity(intent);
            }
        });
        imgElderly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this, Erderly.class);
                startActivity(intent);
            }
        });
        imgSick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeEat.this, LuaChonBenh.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa(){

        imgChildren=findViewById(R.id.imgChildren);
        imgAdults=findViewById(R.id.imgAdults);
        imgElderly=findViewById(R.id.imgElderly);
        imgSick=findViewById(R.id.imgSick);

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
