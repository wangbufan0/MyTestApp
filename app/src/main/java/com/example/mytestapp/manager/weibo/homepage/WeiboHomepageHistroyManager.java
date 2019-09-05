package com.example.mytestapp.manager.weibo.homepage;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Name: WeiboHomepageHistroyManager
 * @Author: wangbufan
 * @Date: 2019/9/4 22:14
 * @Description:
 */
public class WeiboHomepageHistroyManager {

    private static String PATH="Weibo_History.out";


    public static void setdatas( List<WeiboHomepageResp.StatusesBean> datas){
        FileOutputStream fos= null;
        ObjectOutputStream foss=null;
        try {
            File file=new File(ActivityUtils.getTopActivity().getExternalCacheDir(),PATH);
            fos=new FileOutputStream(file);
            //   fos = getContext().openFileOutput("Weibo_History.out", Context.MODE_PRIVATE);
            foss=new ObjectOutputStream(fos);
            foss.writeObject(datas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(foss!=null)
                foss.close();
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<WeiboHomepageResp.StatusesBean> getDatas(){
        List<WeiboHomepageResp.StatusesBean> list=new ArrayList<>();
        FileInputStream fos=null;
        ObjectInputStream foss=null;
        try {
            // FileInputStream fos=getContext().openFileInput("Weibo_History.out");
            File file=new File(ActivityUtils.getTopActivity().getExternalCacheDir(),PATH);
             fos=new FileInputStream(file);
             foss=new ObjectInputStream(fos);
            list= (List<WeiboHomepageResp.StatusesBean>) foss.readObject();
            foss.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(foss!=null)
                    foss.close();
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
