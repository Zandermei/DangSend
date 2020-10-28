package com.two.dangsend.utils;

import android.util.Log;

public class AppLog {
	public static boolean isDebug(){
		String debug = "0";
		return debug.equals("1");
	}
	
	public static void e(String tag, String msg){
		if(isDebug())
			Log.e(tag, msg);
	}
	
	public static void e(String tag, int msg){
		if(isDebug())
			e(tag, String.valueOf(msg));
	}
	
	public static void e(String tag, String msg, Throwable e){
		if(isDebug())
			Log.e(tag, msg, e);
	}
}
