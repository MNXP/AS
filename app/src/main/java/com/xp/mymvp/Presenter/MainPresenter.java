package com.xp.mymvp.Presenter;


import android.content.Context;
import android.text.TextUtils;

import com.xp.mymvp.Tool.MyConstants;
import com.xp.mymvp.Tool.Net.HttpClient;
import com.xp.mymvp.Tool.Net.MySubscriber;
import com.xp.mymvp.Tool.Net.XpSubscriber;
import com.xp.mymvp.Tool.View.MyToast;
import com.xp.mymvp.Activity.Base.BasePresenter;
import com.xp.mymvp.Activity.Base.BaseRequestContract;
import com.xp.mymvp.Bean.ActivityListBean;

/**
 * xiangpan
 * 2018/1/25
 * 17600660418
 * xiangpan@cheyr.cn
 */

public class MainPresenter extends BasePresenter<BaseRequestContract<ActivityListBean>> {

    public MainPresenter(BaseRequestContract<ActivityListBean> mainView) {
        attachView(mainView);
    }

    public void getArticleData(int i, int pageSize ,final Context context) {
        HttpClient.getInstance().getList(i, pageSize, new MySubscriber<>(context, new XpSubscriber<ActivityListBean>() {
            @Override
            public void onSuccess(ActivityListBean activityListBean) {
                if (TextUtils.equals(activityListBean.getRescode(), MyConstants.NET_OK)){
                    getView().onRequestSuccessData(activityListBean);
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
