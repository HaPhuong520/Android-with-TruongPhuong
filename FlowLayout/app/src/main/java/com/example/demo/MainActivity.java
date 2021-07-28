package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FlowLayout mflowLayout;
    private ArrayList<KeyWord> mListKeyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mflowLayout=findViewById(R.id.flow_layout);
        initData();
        setData();
    }



    private void initData() {
        mListKeyword=new ArrayList<>();
        mListKeyword.add(new KeyWord(1,"Truong Phuong"));
        mListKeyword.add(new KeyWord(2,"Ha Phuong"));
        mListKeyword.add(new KeyWord(3,"Tra My"));
        mListKeyword.add(new KeyWord(4,"abcxyzuvtttttt"));
        mListKeyword.add(new KeyWord(5,"oh noooooooo!"));
    }
    private void setData() {
        if (mflowLayout==null)
        {
            return;
        }
        mflowLayout.removeAllViews();
        if (mListKeyword!=null && mListKeyword.size()>0)
        {
            for(int i=0;i<mListKeyword.size();i++)
            {
                KeyWord keyWord=mListKeyword.get(i);
                TextView textView=new TextView(this);
                FlowLayout.LayoutParams params=new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,FlowLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,10,20,10);
                textView.setLayoutParams(params);
                textView.setId(keyWord.getId());
                textView.setText(keyWord.getName());
                textView.setPadding(30,10,30,10);
                textView.setBackgroundResource(R.drawable.custom_layout_item);
                textView.setTextColor(getResources().getColor(R.color.design_default_color_primary));
                textView.setOnClickListener(this);
                mflowLayout.addView(textView);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:
                Toast.makeText(this,"Click item 1",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"Click item 2",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"Click item 3",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"Click item 4",Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this,"Click item 5",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}