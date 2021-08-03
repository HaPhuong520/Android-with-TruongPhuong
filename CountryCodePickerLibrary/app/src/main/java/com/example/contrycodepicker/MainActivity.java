package com.example.contrycodepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    private  CountryCodePicker countryCodePicker;
    private EditText edtPhoneNumber;
    private ImageView imgCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        AnhXa();

        countryCodePicker.registerCarrierNumberEditText(edtPhoneNumber);



        countryCodePicker.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
               if (isValidNumber){
                   imgCheck.setImageDrawable(R.drawable.dung);
               } else {
                   imgCheck.setImageDrawable(R.drawable.sai);
               }
            }
        });

        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString().trim();
                if (input.length() > 0){
                    imgCheck.setVisibility(View.VISIBLE);
                } else {
                    imgCheck.setVisibility(View.GONE);
                }

            }
        });
    }

    private void AnhXa() {
        countryCodePicker = findViewById(R.id.ccp);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
        imgCheck = findViewById(R.id.img_check);
    }
}