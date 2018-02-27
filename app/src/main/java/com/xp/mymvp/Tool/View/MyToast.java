package com.xp.mymvp.Tool.View;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

public class MyToast {
	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};

	/**
	 * 每次创建Toast时先做一下判断，如果前面有Toast在显示，则替换成要显示的信息。
	 * 
	 * @param mContext
	 * @param text
	 * @param duration
	 */
	public static void showToast(Context mContext, String text, int duration) {

		if (TextUtils.isEmpty(text))
			return;

		mHandler.removeCallbacks(r);
		if (mToast != null)
			mToast.setText(text);
		else
			mToast = Toast.makeText(mContext, text, duration);
		mHandler.postDelayed(r, 1500);

		mToast.show();
	}

	public static void showToast(Context mContext, String text) {
		showToast(mContext, text, Toast.LENGTH_SHORT);
	}
	
	public static void showToast(Context mContext, int resId) {
		showToast(mContext, mContext.getResources().getString(resId), Toast.LENGTH_SHORT);
	}

	public static void showToast(Context mContext, int resId, int duration) {
		showToast(mContext, mContext.getResources().getString(resId), duration);
	}
}
