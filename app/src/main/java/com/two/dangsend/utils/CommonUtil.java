package com.two.dangsend.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	// 显示键盘
	public static void showSoftInput(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		// imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}

	// 隐藏键盘
	public static void hideSoftInput(Context context, View view) {
		if (view == null)
			return;

		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0); // 强制隐藏键盘
	}

	// 键盘是否是显示状态
	public static boolean isShowSoftInput(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 获取状态信息
		return imm.isActive();// true 打开
	}

	public static boolean isMp4(String fileName) {
		int index = fileName.lastIndexOf(".");
		if (fileName.substring(index + 1).equals("mp4"))
			return true;
		else
			return false;
	}

	public static void showToast(final Activity activity, final String message) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 显示toast
	 * @param activity
	 * @param message	内容
	 */
	public static void showToastInCenter(final Activity activity,
			final String message) {
		if(activity == null)
			return;
		
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(activity, message,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});

	}

	/**
	 * 显示toast
	 * @param activity
	 * @param
	 */
	public static void showToastInCenter(Activity activity, int resId) {
		if(activity == null)
			return;
		
		showToastInCenter(activity, activity.getResources().getString(resId));
	}

	/**
	 * 对象转化为byte
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] Object2Byte(Object obj) {
		byte[] tbyte = null;
		try {
			ByteArrayOutputStream fis = new ByteArrayOutputStream();
			ObjectOutputStream ois = new ObjectOutputStream(fis);
			ois.writeObject(obj);
			tbyte = fis.toByteArray();
			ois.close();
			fis.close();
		} catch (Exception e) {

		}
		return tbyte;
	}

	/**
	 * byte转化为对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byte2Object(byte[] bytes) {
		if (bytes == null)
			return null;

		ByteArrayInputStream fis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
		} catch (Exception e) {

		}
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {

		}
		return obj;
	}

	/**
	 * desc:将数组转为16进制
	 * 
	 * @param bArray
	 * @return modified:
	 */
	public static String bytesToHexString(byte[] bArray) {
		if (bArray == null) {
			return null;
		}
		if (bArray.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * desc:将16进制的数据转为数组
	 * <p>
	 * 创建人：聂旭阳 , 2014-5-25 上午11:08:33
	 * </p>
	 * 
	 * @param data
	 * @return modified:
	 */
	public static byte[] StringToBytes(String data) {
		String hexString = data.toUpperCase().trim();
		if (hexString.length() % 2 != 0) {
			return null;
		}
		byte[] retData = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length(); i++) {
			int int_ch; // 两位16进制数转化后的10进制数
			char hex_char1 = hexString.charAt(i); // //两位16进制数中的第一位(高位*16)
			int int_ch1;
			if (hex_char1 >= '0' && hex_char1 <= '9')
				int_ch1 = (hex_char1 - 48) * 16; // // 0 的Ascll - 48
			else if (hex_char1 >= 'A' && hex_char1 <= 'F')
				int_ch1 = (hex_char1 - 55) * 16; // // A 的Ascll - 65
			else
				return null;
			i++;
			char hex_char2 = hexString.charAt(i); // /两位16进制数中的第二位(低位)
			int int_ch2;
			if (hex_char2 >= '0' && hex_char2 <= '9')
				int_ch2 = (hex_char2 - 48); // // 0 的Ascll - 48
			else if (hex_char2 >= 'A' && hex_char2 <= 'F')
				int_ch2 = hex_char2 - 55; // // A 的Ascll - 65
			else
				return null;
			int_ch = int_ch1 + int_ch2;
			retData[i / 2] = (byte) int_ch;// 将转化后的数放入Byte里
		}
		return retData;
	}

	public static Drawable getDrawable(Context context, int resId) {
		return context.getResources().getDrawable(resId);
	}

	public static ProgressDialog showSubmitDialog(Context context, int resId) {
		return showSubmitDialog(context, context.getString(resId));
	}

	public static ProgressDialog showSubmitDialog(Context context,
                                                  String message) {
		ProgressDialog pd = new ProgressDialog(context);
		pd.setCanceledOnTouchOutside(false);
		pd.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {

			}
		});
		pd.setMessage(message);
		pd.show();
		return pd;
	}

	public static String getDistanceByMeter(int meters) {
		if (meters < 10)
			return "0.01km";

		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(meters / 1000f) + "km";
	}

	/**
	 * 控制频繁点击
	 */
	private static long lastClickTime;

	public static boolean isFastDoubleClick() {
		int minClickTime = 1000;// 两次点击间隔时间最小1秒
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		lastClickTime = time;
		return timeD <= minClickTime;
	}

	public static String getResourceString(Context context, int resId) {
		return context.getResources().getString(resId);
	}


	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        return str;
	    }
	}
	
	public static int getResId(Context ctx, String resName, String defType){
        int resId = 0 ;
        try {
        	resId = ctx.getResources().getIdentifier(resName, defType , ctx.getPackageName());
        } catch (Exception e) {
        	
        }
        return resId;
	}
	
	/**
	 * 根据名称获取图片的drawable资源id的方法
	 * @param imageName
	 * @return
	 */
	public static int getDrawableResId(Context ctx, String imageName){
        return getResId(ctx, imageName, "drawable");
	}
	
	public static int getStringResId(Context ctx, String stringName){
		return getResId(ctx, stringName, "string");
	}
	
	public static void setVipImageView(Context context, ImageView vipImageView, int vipLevel){
		if(vipImageView == null)
			return;
		
		if(vipLevel > 0){
			int imageWidth = 42;
			switch (vipLevel) {
			case 1:
				imageWidth = 42;
				break;
			case 2:
				imageWidth = 32;
				break;
			case 3:
				imageWidth = 64;
				break;
			case 4:
				imageWidth = 40;
				break;
			default:
				break;
			}
			LayoutParams llp = vipImageView.getLayoutParams();
			llp.width = ScreenUtil.getRealWidth(imageWidth);
			llp.height = ScreenUtil.getRealWidth(32);
			vipImageView.setImageResource(getDrawableResId(context, "icon_v"+vipLevel));
			vipImageView.setVisibility(View.VISIBLE);
		}else{
			vipImageView.setVisibility(View.GONE);
		}
	}
	

	/**
     * 隐藏虚拟按键，并且全屏
     */
    public static void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
        	//让虚拟键盘一直不显示
            Window window = activity.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION| View.SYSTEM_UI_FLAG_IMMERSIVE;
            window.setAttributes(params);
            //for new api versions.
//        	activity.getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
////                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
//        	activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        	
//            View decorView = activity.getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY/* | View.SYSTEM_UI_FLAG_FULLSCREEN*/;
//            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    
    /**
     * 将ImageView变成灰色
     * @param imageView
     */
    public static void makeImageViewGray(ImageView imageView){
//    	imageView.setAlpha(0.6f);
    	ColorMatrix matrix = new ColorMatrix();
    	matrix.setSaturation(0);
    	ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
    	imageView.setColorFilter(filter);
    }
    
    /**
     * 将ImageView从灰色还原成彩色
     * @param imageView
     */
    public static void makeImageViewRecoverFromGray(ImageView imageView){
    	ColorMatrix matrix = new ColorMatrix();
    	matrix.setSaturation(1);
    	ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
    	imageView.setColorFilter(filter);
    }

	/**
	 * 获取当前时间
	 */

	public static String getCurrentTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AppLog.e("当前时间----",format.format(new Date()));
		return format.format(new Date());
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * @return
	 */
	public static String timeStamp(){
		long time = System.currentTimeMillis();
		String t = String.valueOf(time/1000);
		return t;
	}


}
