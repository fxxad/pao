package com.fxx.pao.ui.login;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.BaseMsgModel;

/**
 *  登录协议
 * Created by fxx on 2017/8/14 0014.
 */

interface LoginContract {
    interface View extends BaseView{
        void loginSuccess(BaseMsgModel msgModel);
        void loginFail(String errorMsg);
    }
    public abstract class Presenter extends BaseRxPresenter<View> {
        abstract void login(String count, String pwd);
    }
}
