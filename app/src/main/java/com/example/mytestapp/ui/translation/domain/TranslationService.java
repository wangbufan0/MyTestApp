package com.example.mytestapp.ui.translation.domain;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @Name: TranslationService
 * @Author: wangbufan
 * @Date: 2019/8/21 23:13
 * @Description:
 */
public interface TranslationService {
    @GET("api/trans/vip/translate")
    Observable<TranslationResp> getTranslationData(@QueryMap Map<String,String> params);
}
