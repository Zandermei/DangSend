package com.two.dangsend.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.two.dangsend.R;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.utils.ToastUtils;
import com.two.dangsend.widget.ImageTitleBar;

import butterknife.BindView;

public class ActivityBackMsg extends StatusBarActivity{

    @BindView(R.id.back_title)
    ImageTitleBar titleBar;
    @BindView(R.id.back_ed)
    EditText editMsg;
    @BindView(R.id.back_btn)
    Button backBtn;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_back);
    }

    @Override
    protected void initView() {
        titleBar.setImageTitle(getResources().getString(R.string.back));
        titleBar.setLeftImgVisibility(View.VISIBLE);
        titleBar.setLeftOncliListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ScreenUtil.setLinearLayoutParams(editMsg,0,460,0,0,0,0);
        ScreenUtil.setLinearLayoutParams(backBtn,0,90,100,0,45,45);
        ScreenUtil.setTextSize(editMsg,28);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editMsg.getText().toString().equals("")){
                    ToastUtils.show("请填写需要反馈的内容");
                    return;
                }else {
                    ToastUtils.show("提交成功");
                    finish();
                }
            }
        });

    }
}
