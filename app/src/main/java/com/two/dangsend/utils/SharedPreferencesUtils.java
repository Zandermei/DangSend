package com.two.dangsend.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    public static final String SP_NAME = "config";
    private static SharedPreferences sp;
    private static String ORDER_NAME="ordername";
    private static String ORDER_MONEY="ordermoney";
    private static String ORDER_POS="orderpos";
    private static String ORDER_ACC="orderacc";
    private static String ORDER_PHONE="order_phone";
    private static String ORDER_AddRESS="order_address";
    private static String ORDER_TIME = "order_time";
    private static String ORDER_TITLE = "order_title";
    private static String ORDER_LOGIN="order_login";

    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveName(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_NAME, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param defValue 默认值
     * @return
     */
    public static String getName(Context context, String defValue) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_NAME, defValue);
    }


    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveMoney(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_MONEY, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param defValue 默认值
     * @return
     */
    public static String getMoney(Context context, String defValue) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_MONEY, defValue);
    }


    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void savePos(Context context, int value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putInt(ORDER_POS, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param defValue 默认值
     * @return
     */
    public static int getPos(Context context, int defValue) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getInt(ORDER_POS, 0);
    }



    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveAccount(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_ACC, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static String getAccount(Context context) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_ACC, "");
    }



    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void savePhone(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_PHONE, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static String getPhone(Context context) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_PHONE, "");
    }


    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveAddress(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_AddRESS, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static String getAddress(Context context) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_AddRESS, "");
    }


    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveTime(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_TIME, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static String getTime(Context context) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_TIME, "");
    }



    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveTitle(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_TITLE, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static String getTitle(Context context) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_TITLE, "");
    }

    /**
     * 保存字符串
     *
     * @param context
     * @param
     * @param value
     */
    public static void saveLogin(Context context, String value) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(ORDER_LOGIN, value).commit();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static String getLogin(Context context) {
        if (sp == null){
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(ORDER_LOGIN, null);
    }

}
