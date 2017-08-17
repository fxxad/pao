package com.fxx.pao.base;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;

/**
 *  未使用   TODO
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

    public void addTask(Observable observable, Observer observer){
        observable.subscribeWith(observer);
    }


}
