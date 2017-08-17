package com.fxx.pao.ui.mine;

import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.MyProfileModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *个人主页presenter
 * Created by fxx on 2017/8/14.
 */

class MineHomePresenter implements MineHomeContract.Presenter{

    private MineHomeContract.View mView;
    private MyProfileModel mMyProfileModel;

    @Override
    public void setView(MineHomeContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
            mView = null;
    }

    @Override
    public void myProfile() {
        RetrofitHelper.createUserApi().myProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyProfileModel>() {
                    @Override
                    public void accept(MyProfileModel myProfileModel) throws Exception {
                        mMyProfileModel =myProfileModel;
                        mView.hasLogin(mMyProfileModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.getMyProfileFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }

    @Override
    public void logOut() {
        RetrofitHelper.createUserApi().logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMsgModel>() {
                    @Override
                    public void accept(BaseMsgModel baseMsgModel) throws Exception {
                        if(baseMsgModel.getSucess()==1){
                            mView.logoutSuccess();
                        }else{
                            mView.logoutFailed(baseMsgModel.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.logoutFailed(NetErrorUtil.handleThrowable(t));
                    }
                });
    }
}
