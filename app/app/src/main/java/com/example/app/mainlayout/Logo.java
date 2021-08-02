package com.example.app.mainlayout;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;

public class Logo extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar=findViewById(R.id.PBLoad);
        textView =findViewById(R.id.TextLoad);
        getSupportActionBar().hide();
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        xyz();
    }
    public void xyz(){
        Progresss anim=new Progresss(this,progressBar,textView,0f,100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }
}
