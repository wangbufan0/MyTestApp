package com.example.mytestapp.ui.homepage.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.homepage.view.HomepageViewI;

import java.util.ArrayList;
import java.util.List;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mMvpView.getScrollingPictureSuccessed((List<String>) msg.obj);
        }
    };

    public void getScrollingPicture(){
        List<String> datas = new ArrayList<>();
        datas.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
        datas.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
        datas.add("http://a.hiphotos.ba0idu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
        datas.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=a62e824376d98d1069d40a31113eb807/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
        datas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561805219659&di=21aafa302d919a21be27a7acfde39a18&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F61%2F05%2F01300542392970153122058010203_s.jpg");
        Message message = new Message();
        message.obj=datas;
        handler.sendMessageDelayed(message,1000);
    }





    @SuppressLint("HandlerLeak")
    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mMvpView.getNewsDatasuccessed((List<String>) msg.obj);
        }
    };

    public void getNewsData(){
        Message message =new Message();
        List<String> data=new ArrayList<>();
        data.add("5555");
        data.add("6666");
        data.add("77777");
        message.obj=data;
        handler1.sendMessageDelayed(message,2000);
    }

}
