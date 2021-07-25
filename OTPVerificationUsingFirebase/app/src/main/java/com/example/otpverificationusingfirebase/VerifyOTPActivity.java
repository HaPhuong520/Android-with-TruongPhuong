package com.example.otpverificationusingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {
    private EditText edt_inputCode1,edt_inputCode2,edt_inputCode3,edt_inputCode4,edt_inputCode5,edt_inputCode6;
    private String verificationId;
//    private TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);
        getSupportActionBar().hide();
        TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setText(String.format("+84-%s", getIntent().getStringExtra("phoneNumber")));
        AnhXa();
        setupOTPInputs();

        final ProgressBar progressBar = findViewById(R.id.progressBar1);
        final Button btnVerify = findViewById(R.id.btnVerify);

        verificationId = getIntent().getStringExtra("verificationId");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_inputCode1.getText().toString().trim().isEmpty()
                || edt_inputCode2.getText().toString().trim().isEmpty()
                || edt_inputCode3.getText().toString().trim().isEmpty()
                || edt_inputCode4.getText().toString().trim().isEmpty()
                || edt_inputCode5.getText().toString().trim().isEmpty()
                || edt_inputCode6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOTPActivity.this,"Please enter valid code!!!",Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = edt_inputCode1.getText().toString() +
                        edt_inputCode2.getText().toString() +
                        edt_inputCode3.getText().toString() +
                        edt_inputCode4.getText().toString() +
                        edt_inputCode5.getText().toString() +
                        edt_inputCode6.getText().toString();

                if(verificationId != null){
                    progressBar.setVisibility(View.VISIBLE);
                    btnVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    btnVerify.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(VerifyOTPActivity.this,"The verification code entered was invalid!!!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        findViewById(R.id.tvResendOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84-"+getIntent().getStringExtra("phoneNumber"),60,
                        TimeUnit.SECONDS,
                        VerifyOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyOTPActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull  String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               verificationId = s;
                               Toast.makeText(VerifyOTPActivity.this,"OTP Sent",Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

    }

    private void setupOTPInputs(){
        edt_inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edt_inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edt_inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edt_inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edt_inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edt_inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edt_inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edt_inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edt_inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edt_inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void AnhXa() {
        edt_inputCode1 = findViewById(R.id.edt_inputCode1);
        edt_inputCode2 = findViewById(R.id.edt_inputCode2);
        edt_inputCode3 = findViewById(R.id.edt_inputCode3);
        edt_inputCode4 = findViewById(R.id.edt_inputCode4);
        edt_inputCode5 = findViewById(R.id.edt_inputCode5);
        edt_inputCode6 = findViewById(R.id.edt_inputCode6);
    }
}