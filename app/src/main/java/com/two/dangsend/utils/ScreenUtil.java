package com.two.dangsend.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScreenUtil {
	public static int screenWidth = 0;// 当前屏幕宽
	public static int screenHeight = 0;// 当前屏幕高
	public static int statusBarHeight = 0;// 状态栏高度
	public static int designScreenWidth = 720;// 设计参考的屏幕宽度
	public static int designScreenHeight = 1280;// 设计参考的屏幕高度

	public static void setScreenData(Activity activity) {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels; // 屏幕宽度（像素）
		screenHeight = metric.heightPixels; // 屏幕高度（像素）
		statusBarHeight = getStatusHeight(activity);
	}

	/**
	 * 设置全屏
	 * @param activity
	 * @param isShowStatusBar	是否显示状态栏
	 */
	public static void setFullScreen(Activity activity, boolean isShowStatusBar) {
		Window window = activity.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		WindowManager.LayoutParams attrs = window.getAttributes();
		if(isShowStatusBar)
			attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		else
			attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		window.setAttributes(attrs);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }
    
	public static int sp2px(Context context, float sp) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (sp * fontScale + 0.5f);
	}

	/**
	 * 
	 * @param activity
	 * @return > 0 success; <= 0 fail
	 */
	public static int getStatusHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass
						.getField("status_bar_height").get(localObject)
						.toString());
				statusHeight = activity.getResources()
						.getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

	/**
	 * 通过控件的原始宽度计算在当前屏幕下的实际宽度，以屏幕宽度为基准计算，单位：像素
	 * 
	 * @param designWidth
	 *            设计的宽度
	 * @return 原始宽度*当前屏幕的宽度/设计参考的屏幕宽度
	 */
	public static int getRealWidth(int designWidth) {
		return designWidth * screenWidth / designScreenWidth;
	}

	/**
	 * 通过控件的原始宽高和在当前屏幕下的实际宽度计算图片在当前屏幕下的实际高度，以屏幕宽度为基准计算，单位：像素
	 * 
	 * @param picWidth
	 *            图片的原始宽度
	 * @param picHeight
	 *            图片的原始高度
	 * @param currentWidth
	 *            图片在当前屏幕下的实际宽度
	 * @return 原始高度*当前实际宽度/原始宽度
	 */
	public static int getRealHeight(int picWidth, int picHeight,
			int currentWidth) {
		return picHeight * currentWidth / picWidth;
	}

	public static Bitmap getRoundCornerImage(Bitmap bitmap_bg, Bitmap bitmap_in) {
		Bitmap roundConcerImage = Bitmap.createBitmap(bitmap_in.getWidth(),
				bitmap_in.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(roundConcerImage);
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, bitmap_in.getWidth(), bitmap_in.getHeight());
		Rect rectF = new Rect(0, 0, bitmap_in.getWidth(), bitmap_in.getHeight());
		paint.setAntiAlias(true);
		NinePatch patch = new NinePatch(bitmap_bg,
				bitmap_bg.getNinePatchChunk(), null);
		patch.draw(canvas, rect);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap_in, rectF, rect, paint);
		return roundConcerImage;
	}

	public static Bitmap getShardImage(Bitmap bitmap_bg, Bitmap bitmap_in) {
		Bitmap roundConcerImage = Bitmap.createBitmap(bitmap_in.getWidth(),
				bitmap_in.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(roundConcerImage);
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, bitmap_in.getWidth(), bitmap_in.getHeight());
		paint.setAntiAlias(true);
		NinePatch patch = new NinePatch(bitmap_bg,
				bitmap_bg.getNinePatchChunk(), null);
		patch.draw(canvas, rect);
		Rect rect2 = new Rect(2, 2, bitmap_in.getWidth(), bitmap_in.getHeight());
		canvas.drawBitmap(bitmap_in, rect, rect2, paint);
		return roundConcerImage;
	}

	/**
	 * 缩小图片
	 * 
	 * @param bm
	 * @param minWidth
	 * @param minHeight
	 * @return
	 */
	public static Bitmap shrinkBitmap(Bitmap bm, int minWidth, int minHeight) {
		int w = bm.getWidth();
		int h = bm.getHeight();
		float sx = (float) minWidth / w;// 要强制转换，不转换我的在这总是死掉。
		float sy = (float) minHeight / h;
		float scale = Math.max(sx, sy);
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
		return resizeBmp;
	}

	/**
	 * 放大图片
	 * 
	 * @param bm
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	public static Bitmap enlargeBitmap(Bitmap bm, int maxWidth, int maxHeight) {
		int w = bm.getWidth();
		int h = bm.getHeight();
		float sx = (float) maxWidth / w;// 要强制转换，不转换我的在这总是死掉。
		float sy = (float) maxHeight / h;
		float scale = Math.min(sx, sy);
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
		return resizeBmp;
	}

	/**
	 * 设置LinearLayout的控件参数，参数全部为设计图尺寸
	 * 
	 * @param view
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param topMargin
	 *            上间距
	 * @param bottomMargin
	 *            下间距
	 * @param leftMargin
	 *            左间距
	 * @param rightMargin
	 *            右间距
	 */
	public static void setLinearLayoutParams(View view, int width, int height,
                                             int topMargin, int bottomMargin, int leftMargin, int rightMargin) {
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
				.getLayoutParams();
		if (width > 0) {
			params.width = getRealWidth(width);
		}
		if (height > 0) {
			params.height = getRealWidth(height);
		}
		if (topMargin > 0) {
			params.topMargin = getRealWidth(topMargin);
		}
		if (bottomMargin > 0) {
			params.bottomMargin = getRealWidth(bottomMargin);
		}
		if (leftMargin > 0) {
			params.leftMargin = getRealWidth(leftMargin);
		}
		if (rightMargin > 0) {
			params.rightMargin = getRealWidth(rightMargin);
		}
		view.setLayoutParams(params);
	}

	/**
	 * 设置RelativeLayout的控件参数，参数全部为设计图尺寸
	 * 
	 * @param view
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param topMargin
	 *            上间距
	 * @param bottomMargin
	 *            下间距
	 * @param leftMargin
	 *            左间距
	 * @param rightMargin
	 *            右间距
	 */
	public static void setRelativeLayoutParams(View view, int width,
                                               int height, int topMargin, int bottomMargin, int leftMargin,
                                               int rightMargin) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
				.getLayoutParams();
		if (width > 0) {
			params.width = getRealWidth(width);
		}
		if (height > 0) {
			params.height = getRealWidth(height);
		}
		if (topMargin > 0) {
			params.topMargin = getRealWidth(topMargin);
		}
		if (bottomMargin > 0) {
			params.bottomMargin = getRealWidth(bottomMargin);
		}
		if (leftMargin > 0) {
			params.leftMargin = getRealWidth(leftMargin);
		}
		if (rightMargin > 0) {
			params.rightMargin = getRealWidth(rightMargin);
		}
		view.setLayoutParams(params);
	}
	
	/**
	 * 设置FrameLayout的控件参数，参数全部为设计图尺寸
	 * 
	 * @param view
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param topMargin
	 *            上间距
	 * @param bottomMargin
	 *            下间距
	 * @param leftMargin
	 *            左间距
	 * @param rightMargin
	 *            右间距
	 */
	public static void setFrameLayoutParams(View view, int width,
                                            int height, int topMargin, int bottomMargin, int leftMargin,
                                            int rightMargin) {
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
				.getLayoutParams();
		if (width > 0) {
			params.width = getRealWidth(width);
		}
		if (height > 0) {
			params.height = getRealWidth(height);
		}
		if (topMargin > 0) {
			params.topMargin = getRealWidth(topMargin);
		}
		if (bottomMargin > 0) {
			params.bottomMargin = getRealWidth(bottomMargin);
		}
		if (leftMargin > 0) {
			params.leftMargin = getRealWidth(leftMargin);
		}
		if (rightMargin > 0) {
			params.rightMargin = getRealWidth(rightMargin);
		}
		view.setLayoutParams(params);
	}

	/**
	 * 设置textview文字大小
	 * @param tv
	 * @param size	设计图尺寸
	 */
	public static void setTextSize(TextView tv, int size) {
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getRealWidth(size));
	}
	
	/**
	 * 设置行间距
	 * @param tv
	 * @param space
	 */
	public static void setLineSpacing(TextView tv, int space){
		tv.setLineSpacing(space, 1);
	}

	/**
	 * 设置view的内间距，参数为设计图上的尺寸
	 * @param view
	 * @param topPadding
	 * @param bottomPadding
	 * @param leftPadding
	 * @param rightPadding
	 */
	public static void setPadding(View view, int topPadding, int bottomPadding,
                                  int leftPadding, int rightPadding) {
		view.setPadding(getRealWidth(leftPadding), getRealWidth(topPadding),
				getRealWidth(rightPadding), getRealWidth(bottomPadding));
	}

	public static void setLeftDrawable(TextView tv, Drawable drawable,
                                       int drawableSize) {
		drawable.setBounds(0, 0, getRealWidth(drawableSize),
				getRealWidth(drawableSize));
		tv.setCompoundDrawables(drawable, null, null, null);
	}
	
	public static void setLeftDrawable(TextView tv, Drawable drawable,
                                       int drawableWidth, int drawableHeight) {
		drawable.setBounds(0, 0, getRealWidth(drawableWidth),
				getRealWidth(drawableHeight));
		tv.setCompoundDrawables(drawable, null, null, null);
	}
}
