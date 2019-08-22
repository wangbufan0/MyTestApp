package com.example.mytestapp.ui.news.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.manager.user.UserManager;
import com.example.mytestapp.ui.news.NewsActivity;
import com.example.mytestapp.ui.news.login.presenter.LoginPresenter;
import com.example.mytestapp.ui.news.login.view.LoginViewI;
import com.example.mytestapp.ui.news.login.widget.InputPhoneNumView;
import com.example.mytestapp.ui.news.login.widget.SendVerifyCodeView;

public class LoginActivity extends BaseMvpActivity implements View.OnClickListener, LoginViewI {

    private InputPhoneNumView mInputPhoneNumView;
    private EditText mEditTextVerifyCode;
    private SendVerifyCodeView mSendVerifyCodeView;

    private LoginPresenter mLoginPresenter;


    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) { //判断该Activity是不是任务空间的源Activity，false也就是说是被系统重新实例化出来
            //如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        if (UserManager.getInstance().isLogin()) {
            //登陆成功直接到home页面
            NewsActivity.launch(this);
            finish();
        }
    }



    @Override
    protected void initPresenter() {
        mLoginPresenter = PresenterProviders.of(this, LoginPresenter.class);
    }

    @Override
    protected void initView() {
        this.mInputPhoneNumView = findViewById(R.id.et_phone);
        this.mEditTextVerifyCode = findViewById(R.id.et_verify_code);
        this.mSendVerifyCodeView = findViewById(R.id.send_verify_code_view);


        findViewById(R.id.btm_login).setOnClickListener(this);

        this.mSendVerifyCodeView.setOnClickListener(this);

        this.mInputPhoneNumView.setText(UserManager.getInstance().getPhoneNumber());
        this.mInputPhoneNumView.setSelection(UserManager.getInstance().getPhoneNumber().length());
    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login_layout;
    }

    @Override
    public void onClick(View view) {

        String phoneNum = mInputPhoneNumView.getText().toString();
        switch (view.getId()) {
            case R.id.send_verify_code_view:
                if (canSendAuthCode()) {
                    //验证码
                    mLoginPresenter.requestSendAuthCode(phoneNum);
                    mEditTextVerifyCode.setText("");
                }
                break;
            case R.id.btm_login:
                if (checkDataIsValid()) {
                    mLoginPresenter.requestLogin(phoneNum, mEditTextVerifyCode.getText().toString());
                }
                break;
        }


    }


    private boolean canSendAuthCode() {
        if (!mInputPhoneNumView.checkPhoneNumberIsValid()) return false;
        if (!mSendVerifyCodeView.canSendAuthCode()) return false;
        return true;
    }

    private boolean checkDataIsValid() {
        //手机号校验
        if (!mInputPhoneNumView.checkPhoneNumberIsValid()) return false;
        //验证码校验
        if (TextUtils.isEmpty(mEditTextVerifyCode.getText().toString())) {
            ToastUtils.showShort("请填写验证码");
            return false;
        }
        return true;
    }

    @Override
    public void loginSuccess() {
        //这里会保存用户相关的信息
        UserManager.getInstance().savePhoneNumber(mInputPhoneNumView.getText().toString());
        UserManager.getInstance().saveToken("999999");
        NewsActivity.launch(this);
        finish();
    }

    @Override
    public void requestSendAuthCodeSuccess() {
        mSendVerifyCodeView.startCountDown();
    }
}
