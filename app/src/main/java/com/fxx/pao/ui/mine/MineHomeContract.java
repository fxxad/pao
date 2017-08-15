package com.fxx.pao.ui.mine;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.MyProfileModel;

/**
 *
 * Created by fxx on 2017/8/14.
 */

public interface MineHomeContract {
    interface View extends BaseView{
        void hasLogin(MyProfileModel myProfileModel);
        void getMyProfileFail(String msg);
        void logoutSuccess();
        void logoutFailed(String msg);
    }
    interface Presenter extends BasePresenter<View>{
        void myProfile();
        void logOut();
    }

}
