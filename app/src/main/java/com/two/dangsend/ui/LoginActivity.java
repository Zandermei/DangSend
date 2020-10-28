package com.two.dangsend.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.two.dangsend.MainActivity;
import com.two.dangsend.R;
import com.two.dangsend.utils.AppLog;
import com.two.dangsend.utils.Constant;
import com.two.dangsend.utils.CountDownTimerUtils;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.utils.SharedPreferencesUtils;
import com.two.dangsend.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

public class LoginActivity extends StatusBarActivity {

    @BindView(R.id.login_pic)
    ImageView imageView;

    @BindView(R.id.login_account_line)
    RelativeLayout accountLine;
    @BindView(R.id.login_account)
    EditText account;
    @BindView(R.id.login_code_line)
    RelativeLayout codeLine;
    @BindView(R.id.login_code)
    EditText code;
    @BindView(R.id.code_get)
    TextView codeGet;
    @BindView(R.id.login_btn)
    Button loginBtn;


    //用户协议
    @BindView(R.id.line_radio)
    RelativeLayout lineRadio;
    @BindView(R.id.radio)
    ImageButton radioButton;
    @BindView(R.id.tv_yhxy)
    TextView tvYhxy;//用户协议
    @BindView(R.id.tv_content)
    TextView tvContent;

    private boolean isCheck = false;

    private Handler mHandler = new Handler();
    private String codeNum;

    private CountDownTimerUtils utils;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        ScreenUtil.setLinearLayoutParams(imageView, 0, 460, 0, 0, 0, 0);
        ScreenUtil.setLinearLayoutParams(accountLine, 0, 90, 15, 0, 35, 35);
        ScreenUtil.setTextSize(account, 30);
        ScreenUtil.setLinearLayoutParams(codeLine, 0, 90, 25, 0, 35, 35);
        ScreenUtil.setRelativeLayoutParams(code, 300, 85, 0, 0, 0, 0);
        ScreenUtil.setPadding(codeGet, 10, 10, 5, 5);
        ScreenUtil.setTextSize(code, 30);
        ScreenUtil.setTextSize(codeGet, 26);

        ScreenUtil.setLinearLayoutParams(loginBtn, 0, 90, 90, 0, 45, 45);
        ScreenUtil.setTextSize(loginBtn, 32);

        //用户协议
        ScreenUtil.setLinearLayoutParams(lineRadio, 0, 55, 10, 0, 20, 20);
        ScreenUtil.setRelativeLayoutParams(radioButton, 55, 55, 0, 0, 0, 5);
        // ScreenUtil.setRelativeLayoutParams(tvYhxy,300,45,0,0,10,0);
        ScreenUtil.setTextSize(tvYhxy, 30);
        ScreenUtil.setTextSize(tvContent, 28);
        ScreenUtil.setRelativeLayoutParams(tvContent, 220, 45, 0, 0, 15, 0);


        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCheck) {
                    isCheck = false;
                    radioButton.setImageResource(R.drawable.select_no);
                } else {
                    isCheck = true;
                    radioButton.setImageResource(R.drawable.select_yes);
                }
            }
        });

        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ActivityMsg.class));

            }
        });

        tvContent.setText(Html.fromHtml("<u>" + "隐私协议内容" + "</u>"));



        codeGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (account.getText().toString().equals("")) {
                    ToastUtils.show(getResources().getString(R.string.phone_nul));
                    return;
                } else {
                    AppLog.e("网络状态---",Constant.getNetworkState()+"=");
                   if(Constant.getNetworkState()){
                       utils = new CountDownTimerUtils(codeGet, 59000, 1000, LoginActivity.this);
                       utils.start();
                       ToastUtils.show(getResources().getString(R.string.code_send));
                       getCodeMsg();
                   }else {
                       ToastUtils.show(getResources().getString(R.string.neteror));
                   }
                }
            }
        });

    }

    private void getCodeMsg() {
        //随机数生成
         codeNum = Constant.getRanDom();

        OkHttpUtils.post().url(Constant.urlCode).addParams("mobile", account.getText().toString())
                .addParams("tNum", Constant.codeModel)
                .addParams("showapi_appid", Constant.appid)
                .addParams("showapi_sign", Constant.appsecret)
                .addParams("content", "{\"name\":\"您好,您的验证码是\",\"code\":\"" + codeNum + "\",\"minute\":\"3\"}")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AppLog.e("---返回信息--", response.toString());
                    }
                });
            }
        });

    }


    @Override
    protected void setUpView() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.getText().toString().equals("")) {
                    ToastUtils.show(getResources().getString(R.string.input_code));
                    return;
                } else {
                    if (code.getText().toString().equals(codeNum)) {

                        if (isCheck) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            SharedPreferencesUtils.saveAccount(LoginActivity.this, account.getText().toString());
                            finish();
                        } else {
                            ToastUtils.show("请同意用户协议");
                        }

                    } else {
                        ToastUtils.show(getResources().getString(R.string.code_error));
                    }
                }


            }
        });
    }
}
