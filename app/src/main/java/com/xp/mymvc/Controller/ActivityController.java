package com.xp.mymvc.Controller;

import android.content.Context;

import com.xp.mymvc.Bean.ActivityListBean;
import com.xp.mymvc.Model.ActivityModel;

import java.util.List;

/**
 * 描  述：活动中心控制器
 * 开发者：xiangpan
 * 时  间：2017/5/18.
 */

public class ActivityController {
    private ActivityModel activityModel;
    public ActivityController(){
        activityModel = new ActivityModel();
    }
    public void postActivityList(int i, int pageSize, Context context,NetMessageListener netMessageListener){
        activityModel.postActivityList(i, pageSize, context,netMessageListener);
    }

    /**
     * 添加成功的回调接口
     */
    public interface NetMessageListener {
        void onComplete(List<ActivityListBean.ActivityBean> list);
    }




}
