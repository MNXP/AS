package com.xp.mymvp.Activity.Base;

import java.lang.ref.WeakReference;



public class BasePresenter<VIEW> {

    private WeakReference<VIEW> mViews;

    protected void attachView(VIEW view) {
        mViews = new WeakReference<VIEW>(view);
    }

    protected VIEW getView() {
        return isViewAttached() ? mViews.get() : null;
    }

    private boolean isViewAttached() {
        return null != mViews && null != mViews.get();
    }

    protected void detachView() {
        if (null != mViews) {
            mViews.clear();
            mViews = null;
        }
    }
}