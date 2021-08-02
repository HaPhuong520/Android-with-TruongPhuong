package com.example.appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void reg(View view){
        String username=findViewById(R.id.username).getText().toString();
        ProgressBar progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        String url="";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                TextView msg=findViewById(R.id.msg);
                msg.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",username);
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
        progressBar.setVisibility(View.GONE);
    }
    public void login(View view){
        String username=findViewById(R.id.username).getText().toString();
        ProgressBar progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        String url="";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            progressBar.setVisibility(View.GONE);
            if(!response.equals("invalid")){
                Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                bundle.putInt("id",Integer.parseInt(response));
                intent.putExtra("user",bundle);
                startActivity(intent);
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",username);
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }
}