
package com.xp.mymvp.Tool.View;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xp.mymvc.R;

/**
 * 加载中工具类
 */
public class LoadDialog {
    public static ProgressDialog progressDialog;

    public static void showDialog(Activity activity) {
        showDialog(activity, true);
    }

    public static void showDialog(Activity activity, boolean b) {
        if (activity == null) {
            return;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        LayoutInflater inflater = LayoutInflater.from(activity);
        RelativeLayout view = (RelativeLayout) inflater.inflate(
                R.layout.dialog_view_dia, null);
        TextView messageTextView = (TextView) view
                .findViewById(R.id.dialog_message);

        progressDialog = new ProgressDialog(activity,R.style.dialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(b);


        progressDialog.show();
        progressDialog.getWindow().setContentView(view);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = progressDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.65); // 设置宽度
        // lp.height = (int) (display.getHeight() * 0.3);
        progressDialog.getWindow().setAttributes(lp);


    }

    public static void cancelDialog() {
        if (progressDialog == null) {
            return;
        }
        progressDialog.dismiss();
        progressDialog = null;
    }


}
