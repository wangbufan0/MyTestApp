package com.example.mytestapp.ui.weibo.homepage.domin;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @Name: WeiboHomepageService
 * @Author: wangbufan
 * @Date: 2019/8/27 10:46
 * @Description:
 */
public interface WeiboHomepageService {
    @GET("statuses/home_timeline.json")
    Observable<WeiboHomepageResp> getWeiboHomeTimeline(@QueryMap Map<String ,String> parms);
}
