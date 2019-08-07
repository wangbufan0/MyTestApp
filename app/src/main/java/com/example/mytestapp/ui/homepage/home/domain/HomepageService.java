package com.example.mytestapp.ui.homepage.home.domain;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HomepageService {

    @GET("news/latest")
    Observable<HomepageResp> GetHomepageReverse();
}
