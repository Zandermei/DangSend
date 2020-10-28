package com.two.dangsend.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.two.dangsend.R;
import com.two.dangsend.db.DBOpenHelp;
import com.two.dangsend.model.OrderModel;
import com.two.dangsend.utils.CommonUtil;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.utils.SharedPreferencesUtils;
import com.two.dangsend.utils.ToastUtils;
import com.two.dangsend.widget.ImageTitleBar;

import butterknife.BindView;


public class OrderCommintActivity extends StatusBarActivity{
    @BindView(R.id.title_commint)
    ImageTitleBar titleBar;
    @BindView(R.id.good_order_line)
    RelativeLayout goodLine;
    @BindView(R.id.goods_order_pic)
    ImageView goodsPic;
    @BindView(R.id.goods_order_title)
    TextView goodsTitle;
    @BindView(R.id.goods_order_money)
    TextView goodsMoney;

    @BindView(R.id.order_address)
    TextView orderAddress;
    @BindView(R.id.order_people_line)
    RelativeLayout orderPeopleLine;
    @BindView(R.id.order_people_tv)
    TextView orderPeopleTv;
    @BindView(R.id.order_people_ed)
    EditText orderPeopleEd;
    //联系电话
    @BindView(R.id.order_phone_line)
    RelativeLayout orderPhoneLine;
    @BindView(R.id.order_phone_tv)
    TextView orderPhoneTv;
    @BindView(R.id.order_phone_ed)
    EditText orderPhoneEd;
    //地址
    @BindView(R.id.order_address_line)
    RelativeLayout orderAddressLine;
    @BindView(R.id.order_address_tv)
    TextView orderAddressTv;
    @BindView(R.id.order_address_ed)
    EditText orderAddressEd;
    @BindView(R.id.ordr_commint_btn)
    Button orderCommint;




    private int intentPostion,pageId;
    private String name,money;
    private int[] img0 = {R.drawable.dg1, R.drawable.dg2, R.drawable.dg3, R.drawable.dg4};
    private int[] img1 = {R.drawable.qk1, R.drawable.qk2, R.drawable.qk3, R.drawable.qk4, R.drawable.qk5, R.drawable.qk6};
    private int[] img2 = {R.drawable.dgc1, R.drawable.dgc2, R.drawable.dgc3, R.drawable.dgc4, R.drawable.dgc5,
            R.drawable.dgc6, R.drawable.dgc7, R.drawable.dgc8};
    private int[] img3 = {R.drawable.dg5, R.drawable.dg6, R.drawable.dg7, R.drawable.dg8,
            R.drawable.dg9, R.drawable.dg10, R.drawable.dg11, R.drawable.dg12, R.drawable.dg13, R
            .drawable.dg14, R.drawable.dg15, R.drawable.dg16};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_commint_order);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        money = intent.getStringExtra("money");
        intentPostion = intent.getIntExtra("pageId",0);
        pageId = intent.getIntExtra("pos",0);
    }

    @Override
    protected void initView() {
        titleBar.setLeftImgVisibility(View.VISIBLE);
        titleBar.setLeftOncliListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ScreenUtil.setLinearLayoutParams(goodLine,0,300,15,0,0,0);
        ScreenUtil.setRelativeLayoutParams(goodsPic,260,260,0,0,15,15);
        ScreenUtil.setRelativeLayoutParams(goodsTitle,0,100,60,0,0,0);
        ScreenUtil.setRelativeLayoutParams(goodsMoney,0,100,0,0,0,0);
        ScreenUtil.setTextSize(goodsTitle,32);
        ScreenUtil.setTextSize(goodsMoney,30);


        ScreenUtil.setLinearLayoutParams(orderAddress,0,90,0,0,0,0);
        ScreenUtil.setPadding(orderAddress,0,0,15,0);
        ScreenUtil.setTextSize(orderAddress,35);
        //收货人
        ScreenUtil.setLinearLayoutParams(orderPeopleLine,0,180,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(orderPeopleTv,200,90,0,0,15,45);
        ScreenUtil.setRelativeLayoutParams(orderPeopleEd,350,90,0,0,15,0);
        ScreenUtil.setTextSize(orderPeopleTv,32);
        ScreenUtil.setTextSize(orderPeopleEd,30);
        //联系电话
        ScreenUtil.setLinearLayoutParams(orderPhoneLine,0,180,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(orderPhoneTv,200,90,0,0,15,45);
        ScreenUtil.setRelativeLayoutParams(orderPhoneEd,350,90,0,0,15,0);
        ScreenUtil.setTextSize(orderPhoneTv,32);
        ScreenUtil.setTextSize(orderPhoneEd,30);
        //收货地址
        ScreenUtil.setLinearLayoutParams(orderAddressLine,0,180,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(orderAddressTv,200,90,0,0,15,45);
        ScreenUtil.setRelativeLayoutParams(orderAddressEd,350,90,0,0,15,0);
        ScreenUtil.setTextSize(orderAddressTv,32);
        ScreenUtil.setTextSize(orderAddressEd,30);
        ScreenUtil.setLinearLayoutParams(orderCommint,0,90,100,30,55,55);
        ScreenUtil.setTextSize(orderCommint,32);

        orderCommint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderPeopleEd.getText().toString().equals("")){
                    ToastUtils.show("联系人姓名不能为空");
                    return;
                }else if(orderPhoneEd.getText().toString().equals("")){
                    ToastUtils.show("手机号不能为空");
                    return;
                }else if(orderAddressEd.getText().toString().equals("")){
                    ToastUtils.show("收货地址不能为空");
                    return;
                }else {
                    ToastUtils.show("提交成功");
                    OrderModel model = new OrderModel();
                    model.setOrderPhone(orderPhoneEd.getText().toString());
                    model.setOrderPeopleName(orderPeopleEd.getText().toString());
                    model.setOrderTime(CommonUtil.getCurrentTime());
                    model.setOrderAddress(orderAddressEd.getText().toString());
                    model.setOrderName(name);
                    model.setOrderMoney(money);
                    model.setOrderSucess("1");// 1 提交订单 0 取消订单
                    model.setOrderNum(CommonUtil.timeStamp());
                    model.setOrderAccount(SharedPreferencesUtils.getAccount(OrderCommintActivity.this));
                    DBOpenHelp.getInstance(OrderCommintActivity.this).saveOrderData(model);
                    finish();
                }
            }
        });

    }

    @Override
    protected void setUpView() {
        goodsMoney.setText(money);
        goodsTitle.setText(name);
        switch (pageId) {
            case 0:
                Glide.with(this).load(img0[intentPostion]).into(goodsPic);
                break;

            case 1:
                Glide.with(this).load(img1[intentPostion]).into(goodsPic);
                break;

            case 2:
                Glide.with(this).load(img2[intentPostion]).into(goodsPic);
                break;
            case 3:
                Glide.with(this).load(img3[intentPostion]).into(goodsPic);
                break;
        }
    }
}
