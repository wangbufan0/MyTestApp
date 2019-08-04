package com.example.mytestapp.ui.community.home.presenter;

import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.community.home.domain.CommunityAttentionResp;
import com.example.mytestapp.ui.community.home.view.CommunityAttentionViewI;

public class CommunityAttentionPresenter extends BasePresenter<CommunityAttentionViewI> {


     public    void loadData(){

        mMvpView.getDataSuccessed(CommunityAttentionResp.CeshiBuilder());

    }


}
