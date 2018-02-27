package com.xp.mymvp.Tool.Net;

import com.xp.mymvp.Tool.View.LoadDialog;

/**
 * Created by CheYR-T-XiangP on 2017/5/19.
 */

public class XpSubscriber<T> {
    public void onSuccess(T t){

    }
    public void onFail(String t){

    }
    public void onFinish(){
        LoadDialog.cancelDialog();
    }

}
