package com.two.dangsend.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.two.dangsend.R;
import com.two.dangsend.widget.ImageTitleBar;

import butterknife.BindView;

public class ActivityMsg extends StatusBarActivity {


    @BindView(R.id.title_xy)
    ImageTitleBar titleBar;
    @BindView(R.id.web)
    WebView webView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_xiey);
    }

    @Override
    protected void initView() {
        titleBar.setImageTitle("用户协议");
        titleBar.setLeftImgVisibility(View.VISIBLE);
        titleBar.setLeftOncliListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setUseWideViewPort(true);
        //   webSettings.setBuiltInZoomControls(true);
        webView.requestFocus();
        webView.loadUrl("file:///android_asset/web/webpage.html");


    }
    }


