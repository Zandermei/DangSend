package com.two.dangsend.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.two.dangsend.R;


public class CountDownTimerUtils extends CountDownTimer{

    private TextView mTextView;
    private Context mContex;



    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval,Context context) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.mContex = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + mContex.getResources().getString(R.string.send_s));  //设置倒计时时间
    }

    @Override
    public void onFinish() {
        mTextView.setText(mContex.getResources().getString(R.string.getnew));
        mTextView.setClickable(true);//重新获得点击

    }

}
