package com.example.app.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.R;
import com.example.app.mainlayout.Welcome;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String SHARE_PRE_NAME = "Person";
    TextInputEditText edtUsername;
    EditText edtPassword;
    Button btnSignUp,btnSignIn;
    CheckBox cbSave;
    SQLHelper sqlHelper;
    JSONArray jsonArray;
    List<Person> mListPerson;
   // String avt_img="https://res.cloudinary.com/dzsi7dmey/image/upload/v1625249398/bchzwwgwt7rbhw7cfrer.png";
    private static final String URL ="https://haui-hit-food.herokuapp.com/api/person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListPerson=new ArrayList<>();
        AnhXa();
        sqlHelper = new SQLHelper(this);
       // getListPerson();
        getSupportActionBar().hide();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectFood = jsonArray.getJSONObject(i);
                        String userName1 = jsonObjectFood.getString("username");
                        String Password1 = jsonObjectFood.getString("password");
                        String name = jsonObjectFood.getString("name");
                        String surname = jsonObjectFood.getString("surname");
                        String age = jsonObjectFood.getString("age");
                        mListPerson.add(new Person(surname,name,userName1,Password1,age));
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

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickSigIn();

            }

            private void ClickSigIn() {
                //intent username sang Welcome
                Intent intent1=new Intent(MainActivity.this, Welcome.class);
                //intent age sang TowerDD
                //Intent intentAge = new Intent(MainActivity.this, TowerDD.class);
                String username=edtUsername.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();

                boolean isHasUser=false;
                for(Person person:mListPerson)
                if(username.equals(person.getUsername()) && password.equals(person.getPassword()))
                {
                    isHasUser=true;
                    //put extra username sang Welcome
                    intent1.putExtra("username",String.valueOf(person.getUsername()));
                    intent1.putExtra("age",String.valueOf(person.getAge()));
                    intent1.putExtra("surname",String.valueOf(person.getSurname()));
                    intent1.putExtra("name",String.valueOf(person.getName()));
                    intent1.putExtra("id",String.valueOf(person.getName()));

                    break;
                }
                if(isHasUser){
                    if(cbSave.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PRE_NAME,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", String.valueOf(edtUsername.getText()));
                        editor.putString("password", String.valueOf(edtPassword.getText()));
                        editor.commit();

                    }else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    }

                    startActivity(intent1);
                    //startActivity(intentAge);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Tài khoản hoặc mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                }
//"Username or password no valid !"

            }
        });

    }


    public void AnhXa()
    {
        edtUsername=findViewById(R.id.Username);
        edtPassword=findViewById(R.id.Password);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);
        cbSave=findViewById(R.id.cbSave);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PRE_NAME,MODE_PRIVATE);
        String username =sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        edtUsername.setText(username);
        edtPassword.setText(password);
    }
}