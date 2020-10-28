package com.two.dangsend.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.two.dangsend.MainActivity;
import com.two.dangsend.R;
import com.two.dangsend.utils.SharedPreferencesUtils;

import butterknife.BindView;

public class WelcomeActivity extends StatusBarActivity{
    @BindView(R.id.welcome_img)
    ImageView welcomeImg;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_welcome);
    }


    @Override
    protected void initView() {

        // DBOpenHelper.getInstance(this).getWritableDatabase();
    }

    @Override
    protected void setUpView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                if(SharedPreferencesUtils.getAccount(WelcomeActivity.this).equals("")){
//                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
//                    finish();
//                }else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
               // }


            }
        },2000);

    }
}
