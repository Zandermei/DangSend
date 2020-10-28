package com.two.dangsend.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.tech.NfcA;

import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;

import com.two.dangsend.model.OrderModel;
import com.two.dangsend.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelp extends SQLiteOpenHelper {

    private static String DB_NAME = "cack.db";
    private static int DB_VERSION = 1;

    private static DBOpenHelp instance;


    public DBOpenHelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    public DBOpenHelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * 创建单例模式
     * @param
     */

    public static DBOpenHelp getInstance(Context cx){
        if(instance == null){
            synchronized (DBOpenHelp.class){
                if(instance == null){
                    instance = new DBOpenHelp(cx,DB_NAME,null,DB_VERSION);
                }
            }
        }
        return instance;
    }


    private String CREATE_ORDER_MSG = "CREATE TABLE "+DBParams.TABLE_NAME+"(_id integer primary key autoincrement," +
            ""+DBParams.ORDER_MONEY+" text,"+DBParams.ORDER_NUMBER+" String,"+
            DBParams.ORDER_TIME+" String, "+DBParams.ORDER_TITLE+" String, " +
            DBParams.ORDER_PEOPLE_NAME+" String,"+
            DBParams.ORDER_PHONE+" String,"+
            DBParams.ORDER_ADDRESS+" String,"+
            DBParams.ORDER_ACCOUNT+" String,"+
            ""+DBParams.ORDER_SUCESS+" integer)";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ORDER_MSG);
    }




    /**
     * 保存数据
     * @param =
     * @param
     * @param
     */

    public void saveOrderData(OrderModel model){
        AppLog.e("保存数据--",model.getOrderName()+"--"+model.getOrderTime());
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBParams.ORDER_MONEY,model.getOrderMoney());
        values.put(DBParams.ORDER_NUMBER,model.getOrderNum());
        values.put(DBParams.ORDER_SUCESS,model.getOrderSucess());
        values.put(DBParams.ORDER_TITLE,model.getOrderName());
        values.put(DBParams.ORDER_PHONE,model.getOrderPhone());
        values.put(DBParams.ORDER_ADDRESS,model.getOrderAddress());
        values.put(DBParams.ORDER_PEOPLE_NAME, model.getOrderPeopleName());
        values.put(DBParams.ORDER_TIME,model.getOrderTime());
        values.put(DBParams.ORDER_ACCOUNT,model.getOrderAccount());


        database.insert(DBParams.TABLE_NAME,null,values);
        database.close();
    }

    //更新数据库
    public void updataOrderData(OrderModel model,String num){
        AppLog.e("更新--",model.getOrderSucess());
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBParams.ORDER_SUCESS,model.getOrderSucess());
        database.update(DBParams.TABLE_NAME,values,DBParams.ORDER_NUMBER+" = "+num,null);
        database.close();
    }

    /**
     * 获取数据
     * @param
     */

    public List<OrderModel> getOrderData(String account){
        AppLog.e("获取数据库--account---",account);
        SQLiteDatabase database = this.getReadableDatabase();
        List<OrderModel> modelList = new ArrayList<>();
        Cursor cursor = database.query(DBParams.TABLE_NAME,null,DBParams.ORDER_ACCOUNT+" =?",
                new String[]{account},null,null,null);

        while(cursor.moveToNext()){
            OrderModel model = new OrderModel();
            model.setOrderMoney(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_MONEY)));
            model.setOrderName(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_TITLE)));
            model.setOrderNum(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_NUMBER)));
            model.setOrderSucess(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_SUCESS)));
            model.setOrderAddress(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_ADDRESS)));
            model.setOrderPhone(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_PHONE)));
            model.setOrderPeopleName(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_PEOPLE_NAME)));
            model.setOrderTime(cursor.getString(cursor.getColumnIndex(DBParams.ORDER_TIME)));
            modelList.add(model);
        }


        cursor.close();
        database.close();
        return modelList;

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
