package com.example.mytestapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.R;
import com.example.mytestapp.manager.user.UserManager;
import com.example.mytestapp.ui.homepage.home.fragment.HomepageFragment;
import com.example.mytestapp.ui.login.LoginActivity;
import com.example.mytestapp.ui.news.home.fragment.NewsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends BaseMvpActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;

    private List<Fragment> fragments;

    private int lastfragment;

    public static void launch(Context context){
        Intent intent = new Intent(context, BaseActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!UserManager.getInstance().isLogin()){
            LoginActivity.launch(this);
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragments=new ArrayList<>();
        fragments.add(new HomepageFragment());
        fragments.add(new NewsFragment());
        fragments.add(new HomepageFragment());
        fragments.add(new HomepageFragment());

        ///添加一行注释

        bottomNavigationView=findViewById(R.id.bnv_navigation);
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_home,fragments.get(0)).show(fragments.get(0)).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setVisibility(View.GONE);
    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home_layout;
    }

    public void switchFragment(int lastfragment,int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastfragment));
        if(!fragments.get(index).isAdded()){
            transaction.add(R.id.ll_home,fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_zhuye: {
                if (lastfragment != 0) {
                    switchFragment(lastfragment, 0);
                    lastfragment = 0;
                }
                return true;
            }
            case R.id.navigation_zixun: {
                if (lastfragment != 1) {
                    switchFragment(lastfragment, 1);
                    lastfragment = 1;
                }
                return true;
            }
            case R.id.navigation_shequ: {
                if (lastfragment != 2) {
                    switchFragment(lastfragment, 2);
                    lastfragment = 2;
                }
                return true;
            }
            case R.id.navigation_geren: {
                if (lastfragment != 3) {
                    switchFragment(lastfragment, 3);
                    lastfragment = 3;
                }
                return true;
            }

        }
        return false;
    }
}
