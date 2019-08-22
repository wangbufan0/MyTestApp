package com.example.mytestapp.ui.news.home.domain;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomepageService {

    @GET("/toutiao/index")
    Observable<HomepageResp1> GetHomepageReverse(@Query("type")String type);
}
