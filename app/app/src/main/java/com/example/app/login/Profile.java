package com.example.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity  {
    String name, surname, username,avt_img;
    int age;
    TextView setName,setSurName, tv_pro_ursername;
    ImageView imgAvt,imgSetAvt;
    Button btnFix;
    List<Person> ListPerson=new ArrayList<>();
    JSONArray jsonArray;
    int id;
    private static final String URL ="https://haui-hit-food.herokuapp.com/api/person";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Thông tin người dùng");

        Intent intent=getIntent();
        //set textview username và lấy age từ SingUp và MainActivity
        age= Integer.parseInt(intent.getStringExtra("age"));
        name=intent.getStringExtra("name");
        surname=intent.getStringExtra("surname");
        username=intent.getStringExtra("username");

        imgAvt=findViewById(R.id.setAvt);
        imgSetAvt=findViewById(R.id.selectAvt);
        tv_pro_ursername = findViewById(R.id.tv_pro_username);
        setName=findViewById(R.id.setName);
        setSurName=findViewById(R.id.setSurname);

        setName.setText(String.valueOf(name));
        setSurName.setText(String.valueOf(surname));
        tv_pro_ursername.setText(String.valueOf(username));


     //   btnFix=findViewById(R.id.fixInfor);
        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectFood = jsonArray.getJSONObject(i);
                        String userName1 = jsonObjectFood.getString("username");
                        String Password1 = jsonObjectFood.getString("password");

                        String name1 = jsonObjectFood.getString("name");
                        String surname1 = jsonObjectFood.getString("surname");
                        String age1 = jsonObjectFood.getString("age");
                        if(userName1.equals(username))
                        {
                            id= Integer.parseInt(jsonObjectFood.getString("id"));
                            break;
                        }
                   //     ListPerson.add(new Person(surname1,name1,userName1,Password1,age1,avt_img));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
//        btnFix.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1=new Intent(Profile.this, Welcome.class);
//                Toast.makeText(Profile.this,String.valueOf(id),Toast.LENGTH_SHORT).show();
//                startActivity(intent1);
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

//
//     btnPut.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("password","hihi");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String requestbody = jsonObject.toString();
//            StringRequest stringRequest = new StringRequest(Request.Method.PATCH, url+"/7", new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    textView.setText(response);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    textView.setText("Lỗi rồi :(");
//                }
//            }){
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset = utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    if(requestbody==null) return null;
//                    else {
//                        try {
//                            return requestbody.getBytes("utf-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    }
//                }
//            };
//            requestQueue.add(stringRequest);
//        }
//    });



}
