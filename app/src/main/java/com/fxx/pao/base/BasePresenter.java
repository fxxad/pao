package com.fxx.pao.base;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public interface BasePresenter<T extends BaseView> {
    abstract void setView(T view);
    abstract void removeView();
}
