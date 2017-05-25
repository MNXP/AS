package com.xp.mymvc.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.xp.mymvc.Activity.Base.BaseActivity;
import com.xp.mymvc.Adapter.ActivityAdapter;
import com.xp.mymvc.Bean.ActivityListBean;
import com.xp.mymvc.Controller.ActivityController;
import com.xp.mymvc.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityController activityController;
    private ListView listView;
    private List<ActivityListBean.ActivityBean> list;
    private ActivityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityController = new ActivityController();
        findViewById(R.id.tv).setOnClickListener(this);
        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new ActivityAdapter(list,this);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                activityController.postActivityList(1, 20, MainActivity.this, new ActivityController.NetMessageListener() {
                    @Override
                    public void onComplete(List<ActivityListBean.ActivityBean> list2) {

                        list.addAll(list2);
                        adapter.notifyDataSetChanged();
                    }
                });
                break;
            default:
                break;
        }
    }
}
