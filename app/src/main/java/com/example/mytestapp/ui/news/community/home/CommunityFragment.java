package com.example.mytestapp.ui.news.community.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.community.home.fragment.CommunityAttentionFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends BaseMvpFragment {

    private TabLayout mytabl;
    private ViewPager viewPager;
    private CommunityAttentionFragment[] fragments=new CommunityAttentionFragment[8];
    List<String> titles=new ArrayList<>();
    String[] taps={"guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
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
        titles.add("国内");titles.add("国际");
        titles.add("娱乐");titles.add("体育");
        titles.add("军事");titles.add("科技");
        titles.add("财经");titles.add("时尚");

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public Fragment getItem(int i) {
                if(fragments[i]==null)
                    fragments[i]=new CommunityAttentionFragment(taps[i]);
                return fragments[i];
            }

            @Override
            public int getCount() {
                return titles.size();
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
        fragments[viewPager.getCurrentItem()].refresh();
    }


}
