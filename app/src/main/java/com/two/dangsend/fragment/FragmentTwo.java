package com.two.dangsend.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.two.dangsend.R;
import com.two.dangsend.adapter.FragmentOneAdapter;
import com.two.dangsend.adapter.FragmentOneRecycAdapter;
import com.two.dangsend.ui.OrderMsgActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragmentTwo extends BaseFragment implements FragmentOneRecycAdapter.OnclickMsgLook {
    @BindView(R.id.recyc_two)
    RecyclerView recyclerView;



    private FragmentOneRecycAdapter adapter;
    private List<Integer> imgList;
    private List<String> nameList, moneyList;

    @Override
    protected View setLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    @Override
    protected void setUpView() {
        imgList = new ArrayList<>();
        nameList = new ArrayList<>();
        moneyList = new ArrayList<>();
        imgList.add(R.drawable.dg5);
        imgList.add(R.drawable.dg6);
        imgList.add(R.drawable.dg7);
        imgList.add(R.drawable.dg8);
        imgList.add(R.drawable.dg9);
        imgList.add(R.drawable.dg10);
        imgList.add(R.drawable.dg11);
        imgList.add(R.drawable.dg12);
        imgList.add(R.drawable.dg13);
        imgList.add(R.drawable.dg14);
        imgList.add(R.drawable.dg15);
        imgList.add(R.drawable.dg16);
        //添加商品名称
        nameList.add("松软烤面包甜品");
        nameList.add("葡记紫薯小面包");
        nameList.add("蛋糕A款蓝色奶油");
        nameList.add("乳酸小口袋面包");
        nameList.add("小面包松软零食");
        nameList.add("新鲜面包奶油味");
        nameList.add("乳酸小口袋面包");
        nameList.add("松软夹心奶油蛋糕");
        nameList.add("奶酪黄色小面包");
        nameList.add("小黄包蒸的奶油味");
        nameList.add("米脂松软小蛋糕微甜");
        nameList.add("巧克力奶油小兔子蛋糕");
        //添加价格
        moneyList.add("￥35");
        moneyList.add("￥45");
        moneyList.add("￥135");
        moneyList.add("￥55");
        moneyList.add("￥35");
        moneyList.add("￥45");
        moneyList.add("￥75");
        moneyList.add("￥55");
        moneyList.add("￥35");
        moneyList.add("￥45");
        moneyList.add("￥65");
        moneyList.add("￥55");
        adapter = new FragmentOneRecycAdapter(imgList,nameList,moneyList);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnclickMsgLook(this::onLCick);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onLCick(String name, String money, int id) {
        Intent intent = new Intent(getActivity(), OrderMsgActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("money",money);
        intent.putExtra("pos",id);
        intent.putExtra("pageId",3);//一个标记
        startActivity(intent);
    }
}
