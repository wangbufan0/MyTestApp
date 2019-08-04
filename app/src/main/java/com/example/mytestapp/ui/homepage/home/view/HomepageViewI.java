package com.example.mytestapp.ui.homepage.home.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;

public interface HomepageViewI extends BaseView {

    void getHomepageDatassuccessed(HomepageResp homepageResp);
}
