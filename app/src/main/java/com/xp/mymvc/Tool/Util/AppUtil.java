/**
 *
 */
package com.xp.mymvc.Tool.Util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class AppUtil {

    /**
     * 获取屏幕分辨率
     *
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = {width, height};
        return result;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    public static Drawable bitmapToDrawble(Bitmap bitmap, Context mContext){
        Drawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);
        return drawable;
    }
    public static void hideSoftInput(Activity activity) {
        // **隐藏软键盘**/
        View v = activity.getWindow().peekDecorView();
        if (v != null) {
            InputMethodManager inputmanger = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    public static void showSoftInput(Activity activity) {
        // **显示软键盘**/
        View v = activity.getWindow().peekDecorView();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 是否在后台
     *
     * @return
     */

    public static boolean isAppOnFreground(Context context) {//true 在后台运行  false 没有在后台运行
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        String curPackageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> app = am.getRunningAppProcesses();
        if (app == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo a : app) {
            if (a.processName.equals(curPackageName)
                    && a.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

}
