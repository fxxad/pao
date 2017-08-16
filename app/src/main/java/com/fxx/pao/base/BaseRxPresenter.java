package com.fxx.pao.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 *  TODO
 * Created by Administrator on 2017/8/16 0016.
 */

public class BaseRxPresenter<V extends BaseView> implements BasePresenter<V>{

    private CompositeDisposable mCompositeDisposable;

    @Override
    public void setView(V view) {

    }

    @Override
    public void removeView() {
        if(mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }


}
