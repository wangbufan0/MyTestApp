package com.example.mytestapp.ui.news.news.detail.domain;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface NewsDetailService {

    @GET("news/"+"{id}")
    Observable<NewDetailresq> GetNewsReverse(@Path("id") String id);
}
