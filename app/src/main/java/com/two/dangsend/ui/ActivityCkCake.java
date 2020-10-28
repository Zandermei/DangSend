package com.two.dangsend.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.two.dangsend.R;
import com.two.dangsend.adapter.FragmentOneRecycAdapter;
import com.two.dangsend.widget.ImageTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityCkCake extends StatusBarActivity implements FragmentOneRecycAdapter.OnclickMsgLook {

    @BindView(R.id.title_ck)
    ImageTitleBar titleBar;
    @BindView(R.id.recyc_ck)
    RecyclerView recyclerView;


    private FragmentOneRecycAdapter adapter;
    private List<Integer> imgList;
    private List<String> nameList, moneyList;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_ck);
    }

    @Override
    protected void initView() {
        titleBar.setImageTitle("巧克力蛋糕");
        titleBar.setLeftImgVisibility(View.VISIBLE);
        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void setUpView() {
        imgList = new ArrayList<>();
        nameList = new ArrayList<>();
        moneyList = new ArrayList<>();

        imgList.add(R.drawable.dgc1);
        imgList.add(R.drawable.dgc2);
        imgList.add(R.drawable.dgc3);
        imgList.add(R.drawable.dgc4);
        imgList.add(R.drawable.dgc5);
        imgList.add(R.drawable.dgc6);
        imgList.add(R.drawable.dgc7);
        imgList.add(R.drawable.dgc8);

        nameList.add("草莓爱心小蛋糕");
        nameList.add("草莓奶油棒蛋糕");
        nameList.add("草莓猕猴桃水果蛋糕");
        nameList.add("生日草莓蛋糕");
        nameList.add("爱心草莓蛋糕味");
        nameList.add("甜品奶油爱心蛋糕");
        nameList.add("草莓蛋糕Q版实惠装");
        nameList.add("草莓生日蛋糕奶油味");

        moneyList.add("￥135");
        moneyList.add("￥145");
        moneyList.add("￥85");
        moneyList.add("￥155");
        moneyList.add("￥135");
        moneyList.add("￥145");
        moneyList.add("￥175");
        moneyList.add("￥165");

        adapter = new FragmentOneRecycAdapter(imgList,nameList,moneyList);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnclickMsgLook(this::onLCick);
    }

    @Override
    public void onLCick(String name, String money, int id) {
        Intent intent = new Intent(this, OrderMsgActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("money",money);
        intent.putExtra("pos",id);
        intent.putExtra("pageId",2);//一个标记
        startActivity(intent);
    }
}
