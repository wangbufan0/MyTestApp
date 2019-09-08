package com.example.mytestapp.ui.lottery;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
    private EditText etStart,etEnd;

    private int start=0,end=100,result;

    public static void launch(Context context){
        Intent intent=new Intent(context,LotteryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initPresenter() {

    }
    //产生随机数
    Random random=new Random();

    @Override
    public void run() {
        while(isWorking){
            try {
                Thread.sleep(10);
                result= random.nextInt(end-start+1)+start;
                mTvResult.post(new Runnable() {
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

    class MyTask extends AsyncTask<Void, Integer,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (isWorking){
                try {
                    Thread.sleep(10);
                    result= random.nextInt(end-start+1)+start;
                    publishProgress(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mTvResult.setText(""+values[0]);
        }
    }

    private void startThread(){
       // new Thread(this).start();
        new MyTask().execute();
    }

    @Override
    protected void initView() {
        mTvControl=findViewById(R.id.tv_control);
        mTvResult=findViewById(R.id.tv_result);
        etStart=findViewById(R.id.et_start);
        etEnd=findViewById(R.id.et_end);



        mTvControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWorking){
                    isWorking=false;
                    etStart.setInputType(InputType.TYPE_CLASS_NUMBER);
                    etEnd.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mTvControl.setText("开始");
                }else{
                    if(InputDetection()) {
                        isWorking = true;
                        startThread();
                        mTvControl.setText("停止");
                    }
                }
            }
        });

    }

    private boolean InputDetection(){
        int start= Integer.parseInt(etStart.getText().toString());
        int end=Integer.parseInt(etEnd.getText().toString());
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(start>end){
            showToast("输入错误");
            if(inputMethodManager.isActive())
                inputMethodManager.hideSoftInputFromWindow(etStart.getApplicationWindowToken(),0);
            return false;
        }else{
            this.start=start;
            this.end=end;

            if(inputMethodManager.isActive())
                inputMethodManager.hideSoftInputFromWindow(etStart.getApplicationWindowToken(),0);
            etStart.setInputType(InputType.TYPE_NULL);
            etEnd.setInputType(InputType.TYPE_NULL);
            return true;
        }

    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lottery_layout;
    }
}
