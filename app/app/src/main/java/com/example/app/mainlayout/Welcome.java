package com.example.app.mainlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.login.MainActivity;
import com.example.app.login.Profile;

public class Welcome extends AppCompatActivity {
    ImageView imgIntroduce,imgFood,imgMode,imgTower,setting;
    TextView viewProfile,logout,tvsetUsername,tvv;
    String name, surname, username;
    int age,count=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AnhXa();
        getSupportActionBar().hide();

        Intent intent=getIntent();
        //set textview username và lấy age từ SingUp và MainActivity
        age= Integer.parseInt(intent.getStringExtra("age"));
        name=intent.getStringExtra("name");
        surname=intent.getStringExtra("surname");
        username=intent.getStringExtra("username");
        tvsetUsername.setText(username);
        imgIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, Introduce.class);
                startActivity(intent1);
            }
        });
        imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, ListFood.class);
                startActivity(intent1);
            }
        });
        imgMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, ModeEat.class);
                startActivity(intent1);
            }
        });

        imgTower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, TowerDD.class);
                intent1.putExtra("age",String.valueOf(age));//đâu,phải để ns sang string,vì age là int
                startActivity(intent1);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(count%2==0)
            {
                viewProfile.setVisibility(View.VISIBLE);
                logout.setVisibility(View.VISIBLE);
                tvsetUsername.setVisibility(View.INVISIBLE);
                tvv.setVisibility(View.INVISIBLE);
            }
            else
            {
                viewProfile.setVisibility(View.INVISIBLE);
                logout.setVisibility(View.INVISIBLE);
                tvsetUsername.setVisibility(View.VISIBLE);
                tvv.setVisibility(View.VISIBLE);
            }
            count++;
            }
        });
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, Profile.class);
                intent1.putExtra("username",String.valueOf(username));
                intent1.putExtra("age",String.valueOf(age));
                intent1.putExtra("surname",String.valueOf(surname));
                intent1.putExtra("name",String.valueOf(name));

                startActivity(intent1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void AnhXa(){
        imgIntroduce=findViewById(R.id.imgIntroduce);
        imgFood=findViewById(R.id.imgFood);
        imgMode=findViewById(R.id.imgMode);
        imgTower=findViewById(R.id.imgTower);
        tvsetUsername=findViewById(R.id.tvsetUsername);
        tvv=findViewById(R.id.tvv);
        setting=findViewById(R.id.setting);
        viewProfile=findViewById(R.id.view_profile);
        logout=findViewById(R.id.logout);
        viewProfile.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.INVISIBLE);

    }

}
