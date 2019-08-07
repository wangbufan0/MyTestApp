package com.example.mytestapp.ui.news.home.domain;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface NewsService {

    @GET("news/latest")
    Observable<NewsResq> GetNewsReverse();
}
