package com.two.dangsend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.two.dangsend.adapter.FragmentAdapter;
import com.two.dangsend.fragment.FragmentFour;
import com.two.dangsend.fragment.FragmentOne;
import com.two.dangsend.fragment.FragmentThree;
import com.two.dangsend.fragment.FragmentTwo;
import com.two.dangsend.ui.StatusBarActivity;
import com.two.dangsend.utils.ScreenUtil;
import com.two.dangsend.utils.ToastUtils;
import com.two.dangsend.widget.ImageTitleBar;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

import butterknife.BindView;

public class MainActivity extends StatusBarActivity implements RadioGroup.OnCheckedChangeListener
, ViewPager.OnPageChangeListener {


    @BindView(R.id.title_main)
    ImageTitleBar titleBar;
    @BindView(R.id.fragment_main)
    FrameLayout fragmentLayout;
    @BindView(R.id.main_radio)
    RadioGroup radioGroup;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int reqcode = 10;//权限标识码

    private Fragment fragmentOne,fragmentTwo,fragmentThree,fragmentFour;
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    private String[] title = {"首页","推荐","订单","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);
        fragmentList.add(fragmentFour);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOnPageChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);
        viewPager.setCurrentItem(0);//设置默认首页
        titleBar.setImageTitle(title[0]);
        //申请权限
        requestPhonePermission();
    }

    @Override
    protected void setUpView() {


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        for(int i=0;i<radioGroup.getChildCount();i++){
            if(checkId == radioGroup.getChildAt(i).getId()){
                titleBar.setImageTitle(title[i]);
                viewPager.setCurrentItem(i);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton button = (RadioButton) radioGroup.getChildAt(position);
        button.setChecked(true);
        titleBar.setImageTitle(title[position]);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    private void requestPhonePermission() {
        int per = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(per == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},reqcode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED)
            ToastUtils.show(getResources().getString(R.string.call_deind));

    }
}
