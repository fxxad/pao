package com.fxx.pao.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *  未使用    TODO
 * Created by fxx on 2017/8/17 0017.
 */

public class BaseObserver<T> implements Observer<T>{

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
