package com.two.dangsend;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.shell.control.bridge.ShellApp;
import com.shell.control.state.NativeState;
import com.two.dangsend.db.DBOpenHelp;
import com.two.dangsend.ui.WelcomeActivity;

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //创建数据库
        DBOpenHelp.getInstance(context).getWritableDatabase();

//        new ShellApp().shellBridge(this, new NativeState() {
//            @Override
//            public void show(Context context) {
//                Intent intent = new Intent(context, WelcomeActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    public static Context getApplication(){
        return context;
    }

}
