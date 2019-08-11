package com.example.mytestapp.ui.community.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.community.home.fragment.CommunityAttentionFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends BaseMvpFragment {

    private TabLayout mytabl;
    private ViewPager viewPager;

    List<Fragment> fragments=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {

        mytabl= (TabLayout) findViewById(R.id.mTl);
        viewPager= (ViewPager) findViewById(R.id.mVp);
        fragments.add(new CommunityAttentionFragment());
        fragments.add(new CommunityAttentionFragment());
        titles.add("关注");titles.add("热门");

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

        });
        mytabl.setupWithViewPager(viewPager);


    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_community_layout;
    }



    public void refresh(){
        int i=viewPager.getCurrentItem();
        if(i==0){
            CommunityAttentionFragment communityAttentionFragment = (CommunityAttentionFragment) fragments.get(0);
            communityAttentionFragment.refresh();
        }else{
            CommunityAttentionFragment communityAttentionFragment = (CommunityAttentionFragment) fragments.get(1);
            communityAttentionFragment.refresh();
        }
    }


}
