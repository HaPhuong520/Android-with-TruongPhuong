package com.example.sendotptoemaill;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SendOTPActivity extends AppCompatActivity {
    int code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random random=new Random();
        code=random.nextInt(8999)+1000;
        EditText editText=findViewById(R.id.edtEmail);

    }
}
