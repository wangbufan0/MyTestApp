package com.example.mytestapp.manager.translation;

import com.blankj.utilcode.util.SPUtils;

/**
 * @Name: StatusManager
 * @Author: wangbufan
 * @Date: 2019/8/25 23:28
 * @Description:
 */
public class StatusManager {
    private static final String SP_SPACE_TRANSLATION_STATUS="SP_SPACE_TRANSLATION_STATUS";
    private static final String SP_TRANSLATION_STATUS="SP_TRANSLATION_STATUS";

    private volatile static StatusManager statusManager;


    public static StatusManager getInstance(){
        if(null==statusManager){
            synchronized (StatusManager.class){
                if(null==statusManager)
                    statusManager=new StatusManager();
            }
        }
        return statusManager;
    }

    public int getStatus(){
        return SPUtils.getInstance(SP_SPACE_TRANSLATION_STATUS).getInt(SP_TRANSLATION_STATUS,0);
    }

    public void setStatus(int status){
        if(status!=0&&status!=1)return;
        SPUtils.getInstance(SP_SPACE_TRANSLATION_STATUS).put(SP_TRANSLATION_STATUS,status,true);
    }

}
