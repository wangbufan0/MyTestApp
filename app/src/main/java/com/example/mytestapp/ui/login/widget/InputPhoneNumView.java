package com.example.mytestapp.ui.login.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.blankj.utilcode.util.ToastUtils;

import java.util.regex.Pattern;

/**
 * Date:  2019-06-28
 * Time:  11:02
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class InputPhoneNumView extends AppCompatEditText {
    public InputPhoneNumView(Context context) {
        super(context);
    }

    public InputPhoneNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean checkPhoneNumberIsValid() {
        String phoneNum = getText().toString();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入手机号码!");
            return false;
        }
        if (!Pattern.compile("^1[0-9]{10}$").matcher(phoneNum).matches()) {
            ToastUtils.showShort("请输入正确的手机号码!");
            return false;//手机号码不正确
        }
        return true;
    }
}
