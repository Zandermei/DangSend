package com.two.dangsend.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.two.dangsend.MyApplication;


public class Constant {

    public static String appid = "154410";
    public static String appsecret = "43d9b5185eb9481081bedd1eeb8ae934";
    public static String codeModel = "T170317005896";
    public static String urlCode = "https://route.showapi.com/28-1";

    private static ConnectivityManager mManager = (ConnectivityManager) MyApplication.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

    public static String getRanDom() {
        String strRand = "";
        for (int i = 0; i < 4; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        AppLog.e("验证码----",strRand);
        return strRand;
    }

    /**
     * 获得网络连接状态
     * @return
     */
    public static boolean getNetworkState() {
        NetworkInfo mobile = mManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = mManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mobile.isConnected() || wifi.isConnected();
    }

}
