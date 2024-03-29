package com.example.mytestapp.Base.observer;

import com.example.mytestapp.Retrofit.news.NewsRetrofit;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {

    public static <T> ObservableTransformer<T,T> compose(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.timeout(NewsRetrofit.getDefaultTime(), TimeUnit.SECONDS)
                        .retry(NewsRetrofit.getRETRY_TIMES())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
