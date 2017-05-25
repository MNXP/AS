package com.xp.mymvc.Model;

import android.content.Context;
import android.text.TextUtils;

import com.xp.mymvc.Bean.ActivityListBean;
import com.xp.mymvc.Controller.ActivityController;
import com.xp.mymvc.Tool.MyConstants;
import com.xp.mymvc.Tool.Net.HttpClient;
import com.xp.mymvc.Tool.Net.MySubscriber;
import com.xp.mymvc.Tool.Net.XpSubscriber;
import com.xp.mymvc.Tool.View.MyToast;

/**
 * 描  述：ActivityModel
 * 开发者：x i a n g  p a n
 * 时  间：2017/5/18.
 */

public class ActivityModel {

    public void postActivityList(final int i, int pageSize, final Context context, final ActivityController.NetMessageListener netMessageListener){
        HttpClient.getInstance().getList(i, pageSize, new MySubscriber<ActivityListBean>(context, new XpSubscriber<ActivityListBean>() {
            @Override
            public void onSuccess(ActivityListBean activityListBean) {
                if (TextUtils.equals(activityListBean.getRescode(), MyConstants.NET_OK)){
                    netMessageListener.onComplete(activityListBean.getList());
                }else {
                    onFail(activityListBean.getResmsg_cn());
                }
            }

            @Override
            public void onFail(String s) {
                MyToast.showToast(context,s);
            }
        }));
    }


}
