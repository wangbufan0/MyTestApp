package com.example.mytestapp.ui.weibo.details.domin;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @Name: WeiboDetailsCommentService
 * @Author: wangbufan
 * @Date: 2019/9/5 22:23
 * @Description:
 */
public interface WeiboDetailsCommentService {
    @GET("comments/show.json")
    Observable<WeiboDetailsCommentResp> getConmmentResp(@QueryMap Map<String,String> req);
}
