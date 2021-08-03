package com.example.contrycodepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        CountryCodePicker countryCodePicker = findViewById(R.id.ccp);
        countryCodePicker.showFullName(true);
    }
}