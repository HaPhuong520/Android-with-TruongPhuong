package com.example.quanlyuserstrenfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.quanlyuserstrenfirebase.fragment.FavoriteFragment;
import com.example.quanlyuserstrenfirebase.fragment.HistoryFragment;
import com.example.quanlyuserstrenfirebase.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME=0;
    private static final int FRAGMENT_FAVORITE=1;
    private static final int FRAGMENT_HISTORY=2;
    private int mCurrentFragment=FRAGMENT_HOME;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageView imgAvt;
    private TextView tvName,tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        initUi();
        mNavigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);


        showUserInformation();

    }

    private void initUi(){
        mNavigationView=findViewById(R.id.navigation_view);
        imgAvt=mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvName=mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvEmail=mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_home){

            if(mCurrentFragment!=FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment=FRAGMENT_HOME;
            }
        }else if(id==R.id.nav_history){
            if(mCurrentFragment!=FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                mCurrentFragment=FRAGMENT_HISTORY;
            }

        }else if(id==R.id.nav_favorite){
            if(mCurrentFragment!=FRAGMENT_FAVORITE){
                replaceFragment(new FavoriteFragment());
                mCurrentFragment=FRAGMENT_FAVORITE;
            }
        }else if(id==R.id.nav_sign_out) {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
    private void showUserInformation(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return ;
        }
        else
        {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            if(name==null){
                tvName.setVisibility(View.GONE);
            }else{
                tvName.setVisibility(View.VISIBLE);
                tvName.setText(name);
            }

            tvEmail.setText(email);
            Glide.with(this).load(photoUrl).error(R.drawable.ic_my_profile).into(imgAvt);
        }
    }

}