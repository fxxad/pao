package com.fxx.pao.ui.login;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.BaseMsgModel;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public interface LoginContract {
    interface View extends BaseView{
        void loginSuccess(BaseMsgModel msgModel);
        void loginFail(String errorMsg);
    }
    interface Presenter extends BasePresenter<View>{
        void login(String count,String pwd);
    }
}
