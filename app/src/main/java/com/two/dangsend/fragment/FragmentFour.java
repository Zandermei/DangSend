package com.two.dangsend.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.two.dangsend.R;
import com.two.dangsend.ui.ActivityAbout;
import com.two.dangsend.ui.ActivityBackMsg;
import com.two.dangsend.utils.ActivityManager;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentFour extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.persion_bg_top)
    RelativeLayout persionBgTop;
    @BindView(R.id.persion_bg_heard)
    RelativeLayout persionBgHeard;
    @BindView(R.id.heard_pic)
    ImageView heardPic;

    //优惠卷
    @BindView(R.id.persion_yhj_line)
    LinearLayout persionLine;
    @BindView(R.id.persion_yhj)
    ImageView persionYhuImg;
    @BindView(R.id.persion_yhj_tv)
    TextView persionTv;
    @BindView(R.id.persion_next)
    ImageView persionNext;
    //我的订单
    @BindView(R.id.persion_order_line)
    LinearLayout persionOrderLine;
    @BindView(R.id.persion_order)
    ImageView persionOrderImg;
    @BindView(R.id.persion_order_tv)
    TextView persionOrderTv;
    @BindView(R.id.persion_next_order)
    ImageView persionOrderNext;
    //意见反馈
    @BindView(R.id.persion_back_line)
    LinearLayout persionBackLine;
    @BindView(R.id.persion_back)
    ImageView persionBackImg;
    @BindView(R.id.persion_back_tv)
    TextView persionBackTv;
    @BindView(R.id.persion_back_next)
    ImageView persionBackNext;
    //关于我们
    @BindView(R.id.persion_about_line)
    LinearLayout persionAboutLine;
    @BindView(R.id.persion_about)
    ImageView persionAboutImg;
    @BindView(R.id.persion_about_tv)
    TextView persionAboutTv;
    @BindView(R.id.persion_about_next)
    ImageView persionAboutNext;
    //退出登录
    @BindView(R.id.persion_login_line)
    LinearLayout persionLoginLine;
    @BindView(R.id.persion_login)
    ImageView persionLoginImg;
    @BindView(R.id.persion_login_tv)
    TextView persionLoginTv;
    @BindView(R.id.persion_login_next)
    ImageView persionLoginNext;
    @BindView(R.id.persion_line)
    LinearLayout persionLines;


    @Override
    protected View setLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_four,container,false);
    }


    @Override
    protected void initView() {
        ScreenUtil.setLinearLayoutParams(persionBgTop,0,430,0,0,0,0);
        ScreenUtil.setRelativeLayoutParams(persionBgHeard,0,280,0,0,20,20);
        ScreenUtil.setRelativeLayoutParams(heardPic,230,230,0,0,0,0);
        //优惠卷
        ScreenUtil.setLinearLayoutParams(persionLine,0,90,10,0,15,15);
        ScreenUtil.setLinearLayoutParams(persionYhuImg,35,35,0,0,0,15);
        ScreenUtil.setLinearLayoutParams(persionTv,0,35,0,0,0,0);
        ScreenUtil.setLinearLayoutParams(persionNext,35,45,0,0,0,0);
        ScreenUtil.setTextSize(persionTv,30);
        //我的订单
        ScreenUtil.setLinearLayoutParams(persionOrderLine,0,90,10,0,15,15);
        ScreenUtil.setLinearLayoutParams(persionOrderImg,35,35,0,0,0,15);
        ScreenUtil.setLinearLayoutParams(persionOrderTv,0,35,0,0,0,0);
        ScreenUtil.setLinearLayoutParams(persionOrderNext,35,45,0,0,0,0);
        ScreenUtil.setTextSize(persionOrderTv,30);

        //意见反馈
        ScreenUtil.setLinearLayoutParams(persionBackLine,0,90,10,0,15,15);
        ScreenUtil.setLinearLayoutParams(persionBackImg,35,35,0,0,0,15);
        ScreenUtil.setLinearLayoutParams(persionBackTv,0,35,0,0,0,0);
        ScreenUtil.setLinearLayoutParams(persionBackNext,35,45,0,0,0,0);
        ScreenUtil.setTextSize(persionBackTv,30);
        //关于我们
        ScreenUtil.setLinearLayoutParams(persionAboutLine,0,90,10,0,15,15);
        ScreenUtil.setLinearLayoutParams(persionAboutImg,35,35,0,0,0,15);
        ScreenUtil.setLinearLayoutParams(persionAboutTv,0,35,0,0,0,0);
        ScreenUtil.setLinearLayoutParams(persionAboutNext,35,45,0,0,0,0);
        ScreenUtil.setTextSize(persionAboutTv,30);
        //退出登录
        ScreenUtil.setLinearLayoutParams(persionLoginLine,0,90,10,0,15,15);
        ScreenUtil.setLinearLayoutParams(persionLoginImg,35,35,0,0,0,15);
        ScreenUtil.setLinearLayoutParams(persionLoginTv,0,35,0,0,0,0);
        ScreenUtil.setLinearLayoutParams(persionLoginNext,35,45,0,0,0,0);
        ScreenUtil.setTextSize(persionLoginTv,30);
        ScreenUtil.setPadding(persionLines,30,15,15,15);
        //设置点击事件
        persionOrderLine.setOnClickListener(this::onClick);
        persionAboutLine.setOnClickListener(this::onClick);
        persionLoginLine.setOnClickListener(this::onClick);
        persionBackLine.setOnClickListener(this::onClick);
        persionLine.setOnClickListener(this::onClick);

    }

    @Override
    protected void setUpView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.persion_yhj_line://联系客服
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("客服电话 0371-289313");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:0371289313")));
                    }
                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
                builder.show();


                break;

            case R.id.persion_about_line://关于我们
                startActivity(new Intent(getActivity(), ActivityAbout.class));
                break;

            case R.id.persion_order_line://我的订单
              //  startActivity(new Intent(getActivity(), ActivityMyOrderList.class));
                break;

            case R.id.persion_back_line://意见反馈
                startActivity(new Intent(getActivity(), ActivityBackMsg.class));
                break;

            case R.id.persion_login_line://退出登录
                loginOut();
                break;

        }
    }

    private void loginOut() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getResources().getString(R.string.title_dialog));
        builder.setMessage(getActivity().getResources().getString(R.string.title_dialog_msg));
        builder.setCancelable(false);
        builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityManager.getAppManager().finishAllActivity();
                dialogInterface.dismiss();
                SharedPreferencesUtils.saveAccount(getActivity(), "");
            }
        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        builder.show();

    }
}
