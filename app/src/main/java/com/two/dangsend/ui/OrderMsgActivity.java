package com.two.dangsend.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.two.dangsend.R;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.widget.ImageTitleBar;

import butterknife.BindView;

public class OrderMsgActivity extends StatusBarActivity {

    @BindView(R.id.title_msg)
    ImageTitleBar titleBar;
    @BindView(R.id.order_msg_img)
    ImageView orderMsgImg;

    @BindView(R.id.order_msg_title)
    TextView orderTitle;
    @BindView(R.id.order_msg_money)
    TextView orderMoney;
    @BindView(R.id.order_msg_fw)
    TextView orderTv;

    @BindView(R.id.order_tv1)
    TextView orderTv1;
    @BindView(R.id.order_tv2)
    TextView orderTv2;
    @BindView(R.id.order_tv3)
    TextView orderTv3;
    @BindView(R.id.order_tv4)
    TextView orderTv4;
    @BindView(R.id.order_tv5)
    TextView orderTv5;
    @BindView(R.id.order_btn_line)
    RelativeLayout relativeLayout;
    @BindView(R.id.order_btn)
    Button orderBtn;


    private String name, money;
    private int pos;//点击商品id
    private int pageId;//页面id

    private int[] img0 = {R.drawable.dg1, R.drawable.dg2, R.drawable.dg3, R.drawable.dg4};
    private int[] img1 = {R.drawable.qk1, R.drawable.qk2, R.drawable.qk3, R.drawable.qk4, R.drawable.qk5, R.drawable.qk6};
    private int[] img2 = {R.drawable.dgc1, R.drawable.dgc2, R.drawable.dgc3, R.drawable.dgc4, R.drawable.dgc5,
            R.drawable.dgc6, R.drawable.dgc7, R.drawable.dgc8};
    private int[] img3 = {R.drawable.dg5, R.drawable.dg6, R.drawable.dg7, R.drawable.dg8,
            R.drawable.dg9, R.drawable.dg10, R.drawable.dg11, R.drawable.dg12, R.drawable.dg13, R
            .drawable.dg14, R.drawable.dg15, R.drawable.dg16};

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_order_msg);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        money = intent.getStringExtra("money");
        pos = intent.getIntExtra("pos", 0);
        pageId = intent.getIntExtra("pageId", 0);


    }

    @Override
    protected void initView() {
        titleBar.setImageTitle("商品详情");
        titleBar.setLeftImgVisibility(View.VISIBLE);
        titleBar.setLeftOncliListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setUpView() {
        ScreenUtil.setLinearLayoutParams(orderMsgImg, 0, 450, 0, 0, 0, 0);
        ScreenUtil.setLinearLayoutParams(orderTitle, 0, 70, 5, 0, 25, 25);
        ScreenUtil.setLinearLayoutParams(orderMoney, 0, 45, 5, 0, 25, 25);
        ScreenUtil.setLinearLayoutParams(orderTv, 0, 70, 0, 0, 25, 0);
        ScreenUtil.setTextSize(orderTitle, 26);
        ScreenUtil.setTextSize(orderMoney, 32);
        ScreenUtil.setTextSize(orderTv, 30);
        //商品描述
        ScreenUtil.setLinearLayoutParams(orderTv1,0,70,0,0,25,0);
        ScreenUtil.setLinearLayoutParams(orderTv2,0,70,0,0,25,0);
        ScreenUtil.setLinearLayoutParams(orderTv3,0,70,0,0,25,0);
        ScreenUtil.setLinearLayoutParams(orderTv4,0,70,0,0,25,0);
        ScreenUtil.setLinearLayoutParams(orderTv5,0,70,0,0,25,0);

        ScreenUtil.setTextSize(orderTv1,28);
        ScreenUtil.setTextSize(orderTv2,28);
        ScreenUtil.setTextSize(orderTv3,28);
        ScreenUtil.setTextSize(orderTv4,28);
        ScreenUtil.setTextSize(orderTv5,28);
        //提交订单
        ScreenUtil.setLinearLayoutParams(relativeLayout,0,90,15,0,0,0);
        ScreenUtil.setRelativeLayoutParams(orderBtn,150,65,0,0,0,25);


        orderMoney.setText(money);
        orderTitle.setText(name);
        orderTv1.setText("商品名称:"+name);
        switch (pageId) {
            case 0:
                Glide.with(this).load(img0[pos]).into(orderMsgImg);
                break;

            case 1:
                Glide.with(this).load(img1[pos]).into(orderMsgImg);
                break;

            case 2:
                Glide.with(this).load(img2[pos]).into(orderMsgImg);
                break;
            case 3:
                Glide.with(this).load(img3[pos]).into(orderMsgImg);
                break;
        }


        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderMsgActivity.this,OrderCommintActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("money",money);
                intent.putExtra("pageId",pageId);
                intent.putExtra("pos",pos);
                startActivity(intent);
            }
        });
    }
}
