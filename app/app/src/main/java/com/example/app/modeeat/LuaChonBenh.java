package com.example.app.modeeat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.mainlayout.ModeEat;
import com.example.app.modeeat.sick.Gout;
import com.example.app.modeeat.sick.HuyetApCao;
import com.example.app.modeeat.sick.HuyetApThap;
import com.example.app.modeeat.sick.MauNhiemMo;
import com.example.app.modeeat.sick.TieuDuong;
import com.example.app.modeeat.sick.TimMach;
import com.example.app.modeeat.sick.ViemDaDay;

public class LuaChonBenh extends AppCompatActivity {

    RelativeLayout submit;
    RadioGroup radioGroup;
    RadioButton benh,benhTimMach,benhTieuDuong,benhHuyetApCao,benhHuyetApThap,benhMauNhiemMo,benhGout,benhViemDaDay,khongBenh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tien_su_benh);
        AnhXa();
        getSupportActionBar().setTitle("Người có tiền sử mắc bệnh ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.benhTimMach:
                        intent=new Intent(LuaChonBenh.this,TimMach.class);
                        startActivity(intent);
                        break;
                    case R.id.benhTieuDuong:
                        intent=new Intent(LuaChonBenh.this, TieuDuong.class);
                        startActivity(intent);
                        break;
                    case R.id.benhHuyetApCao:
                        intent=new Intent(LuaChonBenh.this, HuyetApCao.class);
                        startActivity(intent);
                        break;
                    case R.id.benhHuyetApThap:
                        intent=new Intent(LuaChonBenh.this, HuyetApThap.class);
                        startActivity(intent);
                        break;
                    case R.id.benhMauNhiemMo:
                        intent=new Intent(LuaChonBenh.this, MauNhiemMo.class);
                        startActivity(intent);
                        break;
                    case R.id.benhGout:
                        intent=new Intent(LuaChonBenh.this, Gout.class);
                        startActivity(intent);
                        break;
                    case R.id.benhViemDaDay:
                        intent=new Intent(LuaChonBenh.this, ViemDaDay.class);
                        startActivity(intent);
                        break;
                    case R.id.khongBenh:
                        intent=new Intent(LuaChonBenh.this, ModeEat.class);
                        startActivity(intent);
                        break;

                }
        };




    });
    }
    public void AnhXa()
    {
        benhTimMach=findViewById(R.id.benhTimMach);
        benhTieuDuong=findViewById(R.id.benhTieuDuong);
        benhHuyetApCao=findViewById(R.id.benhHuyetApCao);
        benhHuyetApThap=findViewById(R.id.benhHuyetApThap);
        benhMauNhiemMo=findViewById(R.id.benhMauNhiemMo);
        benhGout=findViewById(R.id.benhGout);
        benhViemDaDay=findViewById(R.id.benhViemDaDay);
        khongBenh=findViewById(R.id.khongBenh);
        submit=findViewById(R.id.btnSub);
        radioGroup=findViewById(R.id.ratioGoup);
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
