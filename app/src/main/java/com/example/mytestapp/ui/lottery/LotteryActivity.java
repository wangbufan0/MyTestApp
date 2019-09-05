package com.example.mytestapp.ui.lottery;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.R;

import java.util.Random;

/**
 * @Name: LotteryActivity
 * @Author: wangbufan
 * @Date: 2019/9/5 23:44
 * @Description:
 */
public class LotteryActivity extends BaseMvpActivity implements Runnable{

    private TextView mTvControl,mTvResult;
    private boolean isWorking=false;
    private int start=0,end=100,result;

    public static void launch(Context context){
        Intent intent=new Intent(context,LotteryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initPresenter() {

    }
    Random random=new Random();

    @Override
    public void run() {
        while(isWorking){
            try {
                Thread.sleep(10);
                result= random.nextInt(end-start+1)+start;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvResult.setText(""+result);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startThread(){
        new Thread(this).start();
    }

    @Override
    protected void initView() {
        mTvControl=findViewById(R.id.tv_control);
        mTvResult=findViewById(R.id.tv_result);

        mTvControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWorking){
                    isWorking=false;
                    mTvControl.setText("开始");
                }else{
                    isWorking=true;
                    startThread();
                    mTvControl.setText("停止");
                }
            }
        });

    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lottery_layout;
    }
}
