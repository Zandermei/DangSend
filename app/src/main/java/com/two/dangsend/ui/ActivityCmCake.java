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

public class ActivityCmCake extends StatusBarActivity implements FragmentOneRecycAdapter.OnclickMsgLook{

    @BindView(R.id.title_cm)
    ImageTitleBar titleBar;
    @BindView(R.id.recyc_cm)
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
        setContentView(R.layout.activity_cm);
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

        imgList.add(R.drawable.qk1);
        imgList.add(R.drawable.qk2);
        imgList.add(R.drawable.qk3);
        imgList.add(R.drawable.qk4);
        imgList.add(R.drawable.qk5);
        imgList.add(R.drawable.qk6);

        nameList.add("巧克力爱心小蛋糕");
        nameList.add("巧克力奶油蛋糕");
        nameList.add("巧克力葡萄加成蛋糕");
        nameList.add("巧克力饼干蛋糕");
        nameList.add("三次巧克力奶油蛋糕");
        nameList.add("樱桃巧克力奶油蛋糕");


        moneyList.add("￥135");
        moneyList.add("￥145");
        moneyList.add("￥125");
        moneyList.add("￥155");
        moneyList.add("￥135");
        moneyList.add("￥145");

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
        intent.putExtra("pageId",1);//一个标记
        startActivity(intent);
    }
}
