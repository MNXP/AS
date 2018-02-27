package com.xp.mymvp.Activity.Base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.xp.mymvc.Activity.PermissionActivity;
import com.xp.mymvc.Tool.Util.CheckPermission;

/**
 * 描  述：Activity公共类
 * 开发者：x i a n g  p a n
 * 时  间：2017/5/19.
 */

public abstract class BaseActivity<PRESENTER extends BasePresenter> extends FragmentActivity {

    public static final int REQUEST_CODE = 10;//请求文件权限的请求码
    private CheckPermission checkPermission;//检测权限器
    protected PRESENTER mPresenter;
    //配置需要取的权限
    static final String[] PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE,  //读取权限
    };




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == checkPermission){
            checkPermission = new CheckPermission(this);
        }
        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        setContentView(layoutRes());
        initView();
        initData();
    }

    protected abstract int layoutRes();

    protected PRESENTER createPresenter() {
        return null;
    }

    protected abstract void initView();
    protected abstract void initData();
    public boolean setPermissionStorage() {
        if (checkPermission.permissionSet(PERMISSION)) {
            PermissionActivity.startActivityForResult(this, REQUEST_CODE, PERMISSION);
            return false;
        }
        return true;
    }
    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }


    public Context getContext() {
        return this;
    }
}
