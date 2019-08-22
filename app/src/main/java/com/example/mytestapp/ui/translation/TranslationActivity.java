package com.example.mytestapp.ui.translation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.translation.domain.TranslationResp;
import com.example.mytestapp.ui.translation.presenter.TranslationPresenter;
import com.example.mytestapp.ui.translation.view.TranslationViewI;

/**
 * @Name: TranslationActivity
 * @Author: wangbufan
 * @Date: 2019/8/21 22:08
 * @Description:
 */
public class TranslationActivity extends BaseMvpActivity implements TranslationViewI {

    public static void launch(Context context){
        Intent intent = new Intent(context, TranslationActivity.class);
        context.startActivity(intent);
    }

    private EditText editText;
    private TextView textView,tv1,tv2;
    private ImageView imageView;
    private TranslationPresenter presenter;
    private String q;

    @Override
    protected void initPresenter() {
        presenter= PresenterProviders.of(this,TranslationPresenter.class);
       // loadData(1);
    }


    @Override
    protected void initView() {
        editText=findViewById(R.id.et_input);
        textView=findViewById(R.id.tv_output);
        tv1=findViewById(R.id.tv_chinese);
        tv2=findViewById(R.id.tv_english);
        imageView=findViewById(R.id.iv_change);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("chinese".equals(tv1.getText())){
                    tv1.setText("english");
                    tv2.setText("chinese");
                }else{
                    tv1.setText("chinese");
                    tv2.setText("english");
                }
            }
        });
        initEditText();

    }



    @SuppressLint("ClickableViewAccessibility")
    private void initEditText(){

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    editText.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //按下事件
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

// 如果软键盘已经显示，则隐藏，反之则显示
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    editText.setCursorVisible(false);
                    q= String.valueOf(editText.getText());
                    loadData(1);
                    return true;
                }
                return false;

            }
        });
    }

    @Override
    protected void loadData(int page) {
        String from,to;
        if("chinese".equals(tv1.getText())){
            from="zh";
            to="en";
        }else{
            from="en";
            to="zh";
        }
        presenter.getTranslationData(q,from,to);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_translation_layout;
    }

    @Override
    public void getDataSuccess(TranslationResp resp) {
        textView.setText(resp.getTrans_result().get(0).getDst());
    }
}
