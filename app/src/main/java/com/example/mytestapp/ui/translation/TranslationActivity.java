package com.example.mytestapp.ui.translation;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.decoration.BaseItemDecoration;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.manager.translation.HistoryManager;
import com.example.mytestapp.manager.translation.StatusManager;
import com.example.mytestapp.ui.translation.binder.TranslationBinder;
import com.example.mytestapp.ui.translation.domain.HistoryData;
import com.example.mytestapp.ui.translation.domain.TranslationResp;
import com.example.mytestapp.ui.translation.presenter.TranslationPresenter;
import com.example.mytestapp.ui.translation.view.TranslationViewI;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @Name: TranslationActivity
 * @Author: wangbufan
 * @Date: 2019/8/21 22:08
 * @Description: 翻译主界面
 */
public class TranslationActivity extends BaseMvpActivity implements TranslationViewI {

    public static void launch(Context context) {
        Intent intent = new Intent(context, TranslationActivity.class);
        context.startActivity(intent);
    }

    private static final int CHINESE_TO_ENGLISH =0;
    private static final int ENGLISH_TO_CHINESE =1;

    private EditText editText;
    private TextView textView, tv1, tv2;
    private ImageView imageView;
    private RecyclerView recyclerView;


    private TranslationPresenter presenter;
    private String q;//输入
    private int statas=0;
    private HistoryManager mgr;
    private MultiTypeAdapter adapter;

    private List<HistoryData> datas;


    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.getmImageViewBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!StringUtils.isEmpty(q)){
                    chang2NULLUI();
                    q=null;
                }else finish();
            }
        });
    }

    @Override
    protected void initPresenter() {
        presenter = PresenterProviders.of(this, TranslationPresenter.class);
        // loadData(1);
    }


    @Override
    protected void initView() {
        editText = findViewById(R.id.et_input);
        textView = findViewById(R.id.tv_output);
        textView.setVisibility(View.GONE);
        tv1 = findViewById(R.id.tv_chinese);
        tv2 = findViewById(R.id.tv_english);
        if(statas== ENGLISH_TO_CHINESE){
            tv1.setText("English");
            tv2.setText("Chinese");
        }else {
            tv1.setText("Chinese");
            tv2.setText("English");
        }
        imageView = findViewById(R.id.iv_change);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch();
            }
        });
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new BaseItemDecoration());
        adapter=new MultiTypeAdapter();
        recyclerView.setAdapter(adapter);
        adapter.register(HistoryData.class,new TranslationBinder());
        initEditText();
        initSqlite();
        adapter.setItems(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if(!StringUtils.isEmpty(q)){
            chang2NULLUI();
            q=null;
        }else super.onBackPressed();
    }

    public void initSqlite(){
        mgr=new HistoryManager(this);
        startManagingCursor(mgr.queryTheCursor());
        datas=mgr.query();
        if(datas==null)datas=new ArrayList<HistoryData>();
    }

    public TranslationActivity() {
    }

    @Override
    protected void parseArguments() {
        statas= StatusManager.getInstance().getStatus();
    }

    private void Switch(){
        ObjectAnimator.ofFloat(imageView, "rotationY", 0, 360)
                .setDuration(500)
                .start();
        ((RelativeLayout.LayoutParams) tv1.getLayoutParams()).leftMargin=0;tv1.requestLayout();

        ((RelativeLayout.LayoutParams) tv2.getLayoutParams()).rightMargin=0;tv2.requestLayout();

        ObjectAnimator.ofFloat(tv1, "translationX", 0, 250)
                .setDuration(250)
                .start();
        ObjectAnimator.ofFloat(tv2, "translationX", 0, -250)
                .setDuration(250)
                .start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(250);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((RelativeLayout.LayoutParams) tv1.getLayoutParams()).leftMargin=250;
                            tv1.requestLayout();
                            ((RelativeLayout.LayoutParams) tv2.getLayoutParams()).rightMargin=250;
                            tv2.requestLayout();
                            ObjectAnimator.ofFloat(tv1, "translationX", 0, -250)
                                    .setDuration(250)
                                    .start();
                            ObjectAnimator.ofFloat(tv2, "translationX", 0, 250)
                                    .setDuration(250)
                                    .start();
                            if(statas== CHINESE_TO_ENGLISH){
                                statas= ENGLISH_TO_CHINESE;
                                tv1.setText("English");
                                tv2.setText("Chinese");
                            }else{
                                statas= CHINESE_TO_ENGLISH;
                                tv1.setText("Chinese");
                                tv2.setText("English");
                            }
                            loadData(1);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initEditText() {

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
                    q = String.valueOf(editText.getText());
                    if(StringUtils.isEmpty(q)){
                        chang2NULLUI();
                    }else{
                        loadData(1);
                    }
                    return true;
                }
                return false;

            }
        });
    }

    public void chang2NULLUI(){
        textView.setText("");
        editText.setText("");
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void loadData(int page) {
        String from, to;
        if (statas==CHINESE_TO_ENGLISH) {
            from = "zh";
            to = "en";
        } else {
            from = "en";
            to = "zh";
        }
        presenter.getTranslationData(q, from, to);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_translation_layout;
    }

    @Override
    public void getDataSuccess(TranslationResp resp) {
        HistoryData data = new HistoryData(resp.getTrans_result().get(0).getSrc(),resp.getTrans_result().get(0).getDst());
        change2NotNULLUI(data);
    }

    public void change2NotNULLUI(HistoryData data){
        int index=datas.indexOf(data);
        if(index!=-1) datas.remove(index);
        datas.add(0,data);
        mgr.add(data);
        textView.setVisibility(View.VISIBLE);
        q=data.getFrom();
        editText.setText(data.getFrom());
        textView.setText(data.getTo());
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StatusManager.getInstance().setStatus(statas);
        mgr.closeDB();
    }

}
