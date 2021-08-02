package com.example.app.mainlayout;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.app.login.MainActivity;

public class Progresss extends Animation {
    Context context;
    ProgressBar progressBar;
    TextView textView;
    Float from,to;

    public Progresss(Context context, ProgressBar progressBar, TextView textView, Float from, Float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        int value= (int) (from+(to-from)*interpolatedTime);
        progressBar.setProgress((int) value);
        textView.setText(value+" %");
        if(value==to){
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }



}
