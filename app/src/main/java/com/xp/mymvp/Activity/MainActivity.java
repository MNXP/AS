package com.xp.mymvp.Activity;

import android.view.View;
import android.widget.ListView;

import com.xp.mymvp.Activity.Base.BaseActivity;
import com.xp.mymvp.Activity.Base.BaseRequestContract;
import com.xp.mymvp.Adapter.ActivityAdapter;
import com.xp.mymvp.Bean.ActivityListBean;
import com.xp.mymvc.R;
import com.xp.mymvp.Presenter.MainPresenter;
import com.xp.mymvp.Tool.View.MyToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements BaseRequestContract<ActivityListBean> ,View.OnClickListener {


    private ListView listView;
    private List<ActivityListBean.ActivityBean> list;
    private ActivityAdapter adapter;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        findViewById(R.id.tv).setOnClickListener(this);
        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new ActivityAdapter(list,this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                if (mPresenter == null){
                    MyToast.showToast(MainActivity.this,"空值");
                }else {
                    mPresenter.getArticleData(1,20,MainActivity.this);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestSuccessData(ActivityListBean data) {
        list.addAll(data.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getArticleDataFailure(Throwable t) {

    }
}
