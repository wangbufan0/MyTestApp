package com.example.mytestapp.ui.weibo.util;

import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: WeiboImageUrlUtil
 * @Author: wangbufan
 * @Date: 2019/8/31 17:23
 * @Description:
 */
public class WeiboImageUrlUtil {

    public static List<String> getMiddleImageURL(List<WeiboHomepageResp.StatusesBean.PicUrlsBean> urls) {
        //thumbnail 低 bmiddle 中 large 高
        String low = "thumbnail";
        String middle = "bmiddle";
        String large = "large";
        List<String> datas = new ArrayList<>();

        if (urls.isEmpty()) return datas;

        for (int i = 0; i < urls.size(); i++) {

            datas.add(StringUtil.replace(urls.get(i).getThumbnail_pic(), low, middle));
        }
        return datas;
    }

    public static List<String> getLargeImageURL(List<String> urls) {
        //thumbnail 低 bmiddle 中 large 高
        String low = "thumbnail";
        String middle = "bmiddle";
        String large = "large";
        List<String> datas = new ArrayList<>();
        if (urls.isEmpty()) return datas;
        for (int i = 0; i < urls.size(); i++) {
            datas.add(StringUtil.replace(urls.get(i), middle, large));
            datas.get(0);
        }
        return datas;
    }

}
