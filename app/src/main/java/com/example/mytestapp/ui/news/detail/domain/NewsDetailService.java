package com.example.mytestapp.ui.news.detail.domain;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NewsDetailService {

    @GET("news/"+"{id}")
    Observable<NewDetailresq> GetNewsReverse(@Path("id") String id);
}
