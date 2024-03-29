package com.example.mytestapp.ui.news;

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
import com.example.mytestapp.manager.news.UserManager;
import com.example.mytestapp.ui.news.home.fragment.HomepageFragment;
import com.example.mytestapp.ui.news.login.LoginActivity;
import com.example.mytestapp.ui.news.news.home.fragment.NewsFragment;
import com.example.mytestapp.ui.news.personal.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseMvpActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;

    private List<Fragment> fragments;

    private int lastfragment;




    public static void launch(Context context){
        Intent intent = new Intent(context, NewsActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!this.isTaskRoot()) { //判断该Activity是不是任务空间的源Activity，false也就是说是被系统重新实例化出来
            //如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }

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
        //  fragments.add(new CommunityFragment());
      fragments.add(new PersonalFragment());

        ///添加一行注释

        bottomNavigationView=findViewById(R.id.bnv_navigation);
        lastfragment=0;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_home,fragments.get(0))
                .show(fragments.get(0))
                .commit();

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
                }else{
                    NewsFragment fragment= (NewsFragment) fragments.get(1);
                    fragment.refresh();
                }
                return true;
            }
//            case R.id.navigation_shequ: {
//                if (lastfragment != 2) {
//                    switchFragment(lastfragment, 2);
//                    lastfragment = 2;
//                }else{
//                    CommunityFragment fragment= (CommunityFragment) fragments.get(2);
//                    fragment.refresh();
//
//                }
//                return true;
   //         }
            case R.id.navigation_shequ: {
                if (lastfragment != 2) {
                    switchFragment(lastfragment, 2);
                    lastfragment = 2;
                }
                return true;
            }

        }
        return false;
    }





}
