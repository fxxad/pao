package com.fxx.pao.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 *  基础RxPresenter类，添加CompositeDisposable用于解除订阅
 * Created by fxx on 2017/8/16 0016.
 */

public abstract class BaseRxPresenter<V extends BaseView> implements BasePresenter<V>{

    public CompositeDisposable mCompositeDisposable;

    public V mView;

    @Override
    public void setView(V view) {
        mView =view;
        mCompositeDisposable =new CompositeDisposable();
    }

    @Override
    public void removeView() {
        mView = null;
        if(mCompositeDisposable !=null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
    }

}
