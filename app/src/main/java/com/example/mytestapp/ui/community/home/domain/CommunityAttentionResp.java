package com.example.mytestapp.ui.community.home.domain;

import java.util.ArrayList;
import java.util.List;

public class CommunityAttentionResp {
    private String id;
    private String neirong;
    private String urlTouxiang;
    private List<String> urlNineIv;

    public static List<CommunityAttentionResp> CeshiBuilder(){
        List<CommunityAttentionResp> list =new ArrayList<>();
        for(int i=0;i<10;i++){
            CommunityAttentionResp shequ_tyle = new CommunityAttentionResp();
            //头像
            shequ_tyle.setUrlTouxiang("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561805219659&di=21aafa302d919a21be27a7acfde39a18&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F61%2F05%2F01300542392970153122058010203_s.jpg");
            //id
            shequ_tyle.setId("第"+i+"个wangbufan");
            List<String> list1=new ArrayList<>();
            StringBuilder s= new StringBuilder("这是一个测试！");
            for(int j=0;j<i;j++){
                //内容
                s.append('\n').append("这是一个测试！");
                //九宫格图片
                list1.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
            }
            //内容和图片
            shequ_tyle.setNeirong(s.toString());
            shequ_tyle.setUrlNineIv(list1);
            list.add(shequ_tyle);
        }
        return list;
    }

    public String getId() {
        return id;
    }

    public String getNeirong() {
        return neirong;
    }

    public String getUrlTouxiang() {
        return urlTouxiang;
    }

    public List<String> getUrlNineIv() {
        return urlNineIv;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public void setUrlTouxiang(String urlTouxiang) {
        this.urlTouxiang = urlTouxiang;
    }

    public void setUrlNineIv(List<String> urlNineIv) {
        this.urlNineIv = urlNineIv;
    }
}
