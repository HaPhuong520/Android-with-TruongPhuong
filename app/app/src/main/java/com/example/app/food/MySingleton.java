package com.example.app.food;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private  static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    public MySingleton(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

   private MySingleton(Context context){
        mCtx=context;
        requestQueue=getReQuestQueue();
   }

    private RequestQueue getReQuestQueue() {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }
    public static synchronized MySingleton getInstance(Context context){
        if(mInstance==null){
            mInstance=new MySingleton(context);

        }
        return  mInstance;

    }
    public<T> void AddToRequestQueue(Request<T> request){
        getReQuestQueue().add(request);
    }
}
