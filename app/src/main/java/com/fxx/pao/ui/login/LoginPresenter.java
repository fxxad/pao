package com.fxx.pao.ui.login;



import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录presenter
 * Created by fxx on 2017/8/14 0014.
 */

class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void login(String count, String pwd) {
        if(count == null || count.equals("")) {
            mView.loginFail("邮箱不能为空");
            return;
        }
        if(pwd == null || pwd.equals("")) {
            mView.loginFail("密码不能为空");
            return;
        }
        RetrofitHelper.createUserApi().login(count,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMsgModel>() {
                    @Override
                    public void accept(BaseMsgModel baseMsgModel) throws Exception {
                        mView.loginSuccess(baseMsgModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.loginFail(NetErrorUtil.handleThrowable(throwable));
                    }
                });
    }

}
