package com.two.dangsend.fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.two.dangsend.R;
import com.two.dangsend.adapter.FragmentAdapter;
import com.two.dangsend.adapter.FragmentOneAdapter;
import com.two.dangsend.adapter.FragmentOneRecycAdapter;
import com.two.dangsend.ui.ActivityCkCake;
import com.two.dangsend.ui.ActivityCmCake;
import com.two.dangsend.ui.OrderMsgActivity;
import com.two.dangsend.utils.AppLog;
import com.two.dangsend.utils.ScreenUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragmentOne extends BaseFragment implements FragmentOneRecycAdapter.OnclickMsgLook{

    @BindView(R.id.bannder)
    Banner banner;
    @BindView(R.id.one_gridview)
    GridView gridView;
    @BindView(R.id.one_day)
    TextView oneDay;//每日优惠
    @BindView(R.id.one_recycle)
    RecyclerView recyclerView;

    private List<Integer> imgList;
    private List<String> nameList, moneyList;
    private List<Integer> imageViews;
    private FragmentOneAdapter adapter;
    private FragmentOneRecycAdapter recycAdapter;

    @Override
    protected View setLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    protected void setUpView() {
        adapter = new FragmentOneAdapter(getActivity());
        gridView.setAdapter(adapter);
        imgList = new ArrayList<>();
        nameList = new ArrayList<>();
        moneyList = new ArrayList<>();
        imgList.add(R.drawable.dg1);
        imgList.add(R.drawable.dg2);
        imgList.add(R.drawable.dg3);
        imgList.add(R.drawable.dg4);
        //添加商品名称
        nameList.add("好吃烘烤面包夹心面包");
        nameList.add("软面包奶油味面包");
        nameList.add("水果奶油巧克力蛋糕");
        nameList.add("葡记牛奶烤面包");
        //添加价格
        moneyList.add("￥35");
        moneyList.add("￥45");
        moneyList.add("￥135");
        moneyList.add("￥55");
        recycAdapter = new FragmentOneRecycAdapter(imgList, nameList, moneyList);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recycAdapter);
        recycAdapter.setOnclickMsgLook(this::onLCick);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(getActivity(), ActivityCmCake.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), ActivityCkCake.class));
                        break;
                }
            }
        });


    }

    @Override
    protected void initView() {
        ScreenUtil.setLinearLayoutParams(banner, 0, 360, 0, 0, 0, 0);
        ScreenUtil.setRelativeLayoutParams(oneDay, 0, 55, 10, 10, 30, 0);
        ScreenUtil.setTextSize(oneDay, 32);

        imageViews = new ArrayList<>();
        imageViews.add(R.drawable.bannder1);
        imageViews.add(R.drawable.bannder2);
        imageViews.add(R.drawable.bannder3);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageViews);
        banner.setDelayTime(2500);
        banner.start();


    }


    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onLCick(String name, String money, int pos) {
        Intent intent = new Intent(getActivity(), OrderMsgActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("money",money);
        intent.putExtra("pos",pos);
        intent.putExtra("pageId",0);//一个标记
        startActivity(intent);
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }
}
