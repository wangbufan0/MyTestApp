package com.example.mytestapp.ui.news.home.domain;


import retrofit2.http.GET;
import rx.Observable;

public interface NewsService {

    @GET("news/latest")
    Observable<NewsResq> GetNewsReverse();
}
