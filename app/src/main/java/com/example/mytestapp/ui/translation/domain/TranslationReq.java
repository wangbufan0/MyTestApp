package com.example.mytestapp.ui.translation.domain;

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;

/**
 * @Name: TranslationReq
 * @Author: wangbufan
 * @Date: 2019/8/21 22:50
 * @Description:
 */
public class TranslationReq {

    @SerializedName("q")
    private String q;
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("appid")
    private String appid="2015063000000001";
    @SerializedName("salt")
    private String salt="1435660288";
    @SerializedName("sign")
    private String sign;

    public void setQ(String q)  {
        try {
            this.q = new String(q.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setSign() {
        //this.sign = MD5Util.getMD5(appid+q+salt+"13h2Q37ayuJy_kAxkkER");
        sign="f89f9594663708c1605f3d736d01d2d4";
    }

    public String getQ() {
        return q;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAppid() {
        return appid;
    }

    public String getSalt() {
        return salt;
    }

    public String getSign() {
        return sign;
    }
}
