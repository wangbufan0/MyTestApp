package com.example.mytestapp.ui.homepage.home.domain;


import retrofit2.http.GET;
import rx.Observable;

public interface HomepageService {

    @GET("news/latest")
    Observable<HomepageResp> GetHomepageReverse();
}
