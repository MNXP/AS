package com.xp.mymvp.Tool.Net;

import android.content.Context;

import com.xp.mymvp.Tool.View.MyToast;

import rx.Subscriber;

/**
 *
 * Created by CheYR-T-XiangP on 2017/5/11.
 */

public class MySubscriber<T> extends Subscriber<T> {
    private XpSubscriber<T> xpSubscriber;
    private Context mContext;
    public MySubscriber(Context context, XpSubscriber<T> listener){
        this.xpSubscriber = listener;
        this.mContext = context;
    }


    @Override
    public void onCompleted() {
        if (xpSubscriber!=null){
            xpSubscriber.onFinish();
        }
    }

    @Override
    public void onError(Throwable e) {
        MyToast.showToast(mContext, "网络中断，请检查您的网络状态");
        if (xpSubscriber!=null){
            xpSubscriber.onFail(e.getMessage());
            xpSubscriber.onFinish();
        }

    }

    @Override
    public void onNext(T t) {
        if (xpSubscriber != null){
            xpSubscriber.onSuccess(t);
        }
    }
}
