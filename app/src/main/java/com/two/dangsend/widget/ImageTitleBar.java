package com.two.dangsend.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.two.dangsend.R;
import com.two.dangsend.utils.ScreenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageTitleBar extends RelativeLayout {

    @BindView(R.id.title_reline)
    RelativeLayout titleLine;
    @BindView(R.id.title_relinet)
    RelativeLayout titleLine2;//返回箭头
    @BindView(R.id.title_img)
    ImageView titleImg;
    @BindView(R.id.title_tv)
    TextView titleTv;//标题
    @BindView(R.id.title_ok)
    ImageView titleOk;

    private Activity activity;
    public ImageTitleBar(Context context) {
        super(context);
        init(context,null);
    }

    public ImageTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ImageTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //加载自定义布局
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.widget_title,this);
        ButterKnife.bind(this);
        ScreenUtil.setScreenData(activity);
        ScreenUtil.setRelativeLayoutParams(titleLine,0,90,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(titleLine2,90,90,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(titleImg,50,45,0,0,20,0);
        ScreenUtil.setRelativeLayoutParams(titleTv,480,60,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(titleOk,50,45,0,0,0,15);
        ScreenUtil.setTextSize(titleTv,32);
        parseStyle(attrs,context);

    }

    private void parseStyle(AttributeSet attrs, Context context) {
        if(attrs != null){
            TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ImageTitleBar);
            String ttile = array.getString(R.styleable.ImageTitleBar_title);
            titleTv.setText(ttile);
            Drawable leftDrawable = array.getDrawable(R.styleable.ImageTitleBar_img);
            if(leftDrawable != null){
                titleImg.setImageDrawable(leftDrawable);
            }
            array.recycle();
        }
    }

    public void setImageTitle(String str){
        titleTv.setText(str);
    }

    public void setImageTitle(int str){
        titleTv.setText(getResources().getString(str));
    }

    public void setLeftOncliListener(OnClickListener listener){
        titleLine2.setOnClickListener(listener);
    }

    public void setLeftImgVisibility(int visi){
        titleLine2.setVisibility(visi);
    }

    public void setRightImgVisibility(int vis){
        titleOk.setVisibility(vis);
    }

    public void setRightOncliListener(OnClickListener listener){
        titleOk.setOnClickListener(listener);
    }

}
