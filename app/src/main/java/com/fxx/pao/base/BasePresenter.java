package com.fxx.pao.base;

/**
 *  Presenter规范接口
 * Created by fxx on 2017/8/11 0011.
 */

public interface BasePresenter<T extends BaseView> {
    void setView(T view);
    void removeView();
}
