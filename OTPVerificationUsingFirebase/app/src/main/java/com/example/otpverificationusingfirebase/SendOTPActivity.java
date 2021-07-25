package com.example.otpverificationusingfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);
        getSupportActionBar().hide();
        final EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        Button btnGetOTP = findViewById(R.id.btnGetOTP);

        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPhoneNumber.getText().toString().trim().isEmpty()){
                    Toast.makeText(SendOTPActivity.this,"Enter your phone number",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),VerifyOTPActivity.class);
                intent.putExtra("phoneNumber",edtPhoneNumber.getText().toString());
                startActivity(intent);
            }
        });
    }
}