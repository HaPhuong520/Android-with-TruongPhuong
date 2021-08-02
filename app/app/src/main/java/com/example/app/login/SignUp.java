package com.example.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.R;
import com.example.app.mainlayout.Welcome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText edtName,edtSurname,edtUsername,edtPassword,edtConFirmPassword,edtAge;
    Button btnSignUp1;
    JSONArray jsonArray;
    List<Person>mListPerson=new ArrayList<>();

    private static final String URL ="https://haui-hit-food.herokuapp.com/api/person";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         // có ít nhất 01 ký tự số
                    "(?=.*[a-z])" +         // có ít nhất 01 ký chữ thường
                   "(?=.*[!@#$%^&*=+])" +  // có ít nhất 01 ký tự đặc biệt
                    "(?=\\S+$)" +           // không được có khoảng trắng (space)
                    ".{6,}" +               // có ít nhất 6 ký tự
                    "$");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng ký");
        AnhXa();

        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);

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
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent username sang Welcome
                Intent intent = new Intent(SignUp.this, Welcome.class);
                //intent age sang TowerDD
                //Intent intentAge = new Intent(SignUp.this, TowerDD.class);

                String password=edtPassword.getText().toString().trim();
                String ConfirmPassword=edtConFirmPassword.getText().toString().trim();
                String username = edtUsername.getText().toString().trim();
                if (!validatePassword(password)) {
                    return;
                }
                if(!test(password,ConfirmPassword))
                {
                    return;
                }
                for(Person p: mListPerson)
                {
                    if(username.equals(p.getUsername()))
                    {
                        Toast.makeText(SignUp.this,"Tên đăng nhập đã tồn tại! ",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", edtUsername.getText().toString().trim());
                    jsonObject.put("password", edtPassword.getText().toString().trim());
                    jsonObject.put("name", edtName.getText().toString().trim());
                    jsonObject.put("surname", edtSurname.getText().toString().trim());
                    jsonObject.put("age", edtAge.getText().toString().trim());
                   // jsonObject.put("avt_img","https://res.cloudinary.com/dzsi7dmey/image/upload/v1625249398/bchzwwgwt7rbhw7cfrer.png");
                    //put extra username sang Welcome
                    intent.putExtra("username",edtUsername.getText().toString().trim());
                    intent.putExtra("age",edtAge.getText().toString().trim());
                    intent.putExtra("surname",edtSurname.getText().toString().trim());
                    intent.putExtra("name",edtName.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue1 = Volley.newRequestQueue(SignUp.this);
                String requestbody1 = jsonObject.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUp.this, "Đăng kí thành công !", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this, "Lỗi rồi !", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if (requestbody1 == null) return null;
                        else {
                            try {
                                return requestbody1.getBytes("utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }
                };
                requestQueue1.add(stringRequest);

                startActivity(intent);
               // startActivity(intentAge);
            }


        });
}


    private boolean test(String password,String ConfirmPassword) {

        if (ConfirmPassword.isEmpty()) {
            Toast.makeText(SignUp.this,"không thể để trống!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(ConfirmPassword)) {
            Toast.makeText(SignUp.this,"không đúng mật khẩu!",Toast.LENGTH_SHORT).show();
            return false;
        }else {

            return true;
        }
    }

    public void AnhXa()
    {
        edtName=findViewById(R.id.edtName);
        edtSurname=findViewById(R.id.edtSurname);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        edtConFirmPassword=findViewById(R.id.edtConFirmPassword);
        btnSignUp1=findViewById(R.id.btnSignUp1);
        edtAge=findViewById(R.id.edtAge);
    }
    private boolean validatePassword(String passwordInput ) {
        passwordInput = edtPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {

            edtPassword.setError("Mật khẩu không thể bỏ trống.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            edtPassword.setError("Mật khẩu bao gồm ít nhất 01 ký tự chữ thường, 01 ký tự chữ hoa, 01 ký tự số và 01 ký tự đặc biệt.");
            return false;
        } else {
            edtPassword.setError(null);
            return true;
        }
    }

    private boolean thu(String passwordInput,String ConfirmPassword ) {
        //passwordInput = edtPassword.getText().toString().trim();

        if (!passwordInput.equals(ConfirmPassword)) {

             edtConFirmPassword.setError("Mật khẩu không khớp !");
            return false;
        } else {
        edtConFirmPassword.setError(null);
        return true;

        }
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


}
