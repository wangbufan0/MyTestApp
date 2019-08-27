package com.example.mytestapp.utils;

/**
 * @Name: StringUtil
 * @Author: wangbufan
 * @Date: 2019/8/27 22:17
 * @Description:
 */
public class StringUtil {

    /**
      *
      * @Name:           replace
      * @Author:         wangbufan
      * @CreateDate:     2019/8/27 22:18
      * @Description:  替换原文中的字符串
     */
    public static String replace(String s,String last,String now){
        String[] s1=s.split(last);
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<s1.length-1;i++){
            stringBuilder.append(s1[i]);
            stringBuilder.append(now);
        }
        stringBuilder.append(s1[s1.length-1]);
        return stringBuilder.toString();
    }
}
