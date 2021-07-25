package com.example.otpverificationusingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);
        getSupportActionBar().hide();
        final EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        Button btnGetOTP = findViewById(R.id.btnGetOTP);
        final ProgressBar progressBar = findViewById(R.id.progressBar);


        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPhoneNumber.getText().toString().trim().isEmpty()){
                    Toast.makeText(SendOTPActivity.this,"Enter your phone number",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                btnGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84-"+edtPhoneNumber.getText().toString(),60,
                        TimeUnit.SECONDS,
                        SendOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                btnGetOTP.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                btnGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(SendOTPActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull  String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                btnGetOTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(),VerifyOTPActivity.class);
                                intent.putExtra("phoneNumber",edtPhoneNumber.getText().toString());
                                intent.putExtra("verificationId",s);
                                startActivity(intent);
                            }
                        }
                );


            }
        });
    }
}