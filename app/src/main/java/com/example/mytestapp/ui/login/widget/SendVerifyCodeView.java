package com.example.mytestapp.ui.login.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.concurrent.TimeUnit;

/**
 * Date:  2019-06-02
 * Time:  09:51
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class SendVerifyCodeView extends AppCompatTextView {
    private static final long COUNT_DOWN_COUNT_MILLI = TimeUnit.SECONDS.toMillis(30);
    private CountDownTimer countDownTimer;

    public SendVerifyCodeView(Context context) {
        super(context);
    }

    public SendVerifyCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean canSendAuthCode(){
        if (countDownTimer != null) {
            return false;
        }
        return true;
    }


    public void startCountDown() {
        setEnabled(false);
        countDownTimer = new CountDownTimer(COUNT_DOWN_COUNT_MILLI, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateUiFormOnTick(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                updateUiFormOnFinish();
            }
        };
        countDownTimer.start();
    }

    private void updateUiFormOnFinish() {
        countDownTimer = null;
        setText("发送验证码");
        setEnabled(true);
    }

    private void updateUiFormOnTick(long secondUntilFinished) {
        setText(secondUntilFinished + " 秒");
    }

    @Override
    protected void onDetachedFromWindow() {
        if (null != countDownTimer) {
            countDownTimer.cancel();
        }
        super.onDetachedFromWindow();
    }
}
