package com.fxx.pao.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 *  未使用   TODO
 * Created by Administrator on 2017/8/16 0016.
 */

public abstract class BaseRxPresenter<V extends BaseView> implements BasePresenter<V>{

    private CompositeDisposable mCompositeDisposable;

    private V mView;

    @Override
    public void setView(V view) {
        mView =view;
        mCompositeDisposable =new CompositeDisposable();
    }

    @Override
    public void removeView() {
        mView = null;
        mCompositeDisposable.dispose();
    }

}
