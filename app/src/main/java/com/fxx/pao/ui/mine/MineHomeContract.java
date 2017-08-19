package com.fxx.pao.ui.mine;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.MyProfileModel;

/**
 *个人主页协议
 * Created by fxx on 2017/8/14.
 */

interface MineHomeContract {
    interface View extends BaseView{
        void hasLogin(MyProfileModel myProfileModel);
        void getMyProfileFail(String msg);
        void logoutSuccess();
        void logoutFailed(String msg);
    }
    abstract static class Presenter extends BaseRxPresenter<View> {
        public abstract void myProfile();
        public abstract void logOut();
    }

}
