package com.two.dangsend.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.two.dangsend.R;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.widget.ImageTitleBar;

import butterknife.BindView;

public class ActivityAbout extends StatusBarActivity{


    @BindView(R.id.about_title)
    ImageTitleBar titleBar;
    @BindView(R.id.about_pic)
    ImageView pic;
    @BindView(R.id.about_version)
    TextView versionTv;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initView() {
        titleBar.setImageTitle(getResources().getString(R.string.about));
        titleBar.setLeftImgVisibility(View.VISIBLE);
        titleBar.setLeftOncliListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ScreenUtil.setLinearLayoutParams(pic,230,230,90,0,0,0);
        ScreenUtil.setLinearLayoutParams(versionTv,0,45,10,0,0,0);
        ScreenUtil.setTextSize(versionTv,30);

    }
}
