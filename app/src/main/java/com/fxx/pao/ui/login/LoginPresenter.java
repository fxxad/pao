package com.fxx.pao.ui.login;


import android.util.Log;

import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.net.RetrofitHelper;

import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public class LoginPresenter implements LoginContract.Presenter{

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
        RetrofitHelper.createUserApi().login(count,pwd).enqueue(new Callback<BaseMsgModel>() {
            @Override
            public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
                if(response.isSuccessful()){
                    mView.loginSuccess(response.body());
                }else{
                    try {
                        mView.loginFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.loginFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.loginFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.loginFail("网络错误,端口被占用");
                    } else{
                        mView.loginFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.loginFail("连接丢失");
                }else{
                    mView.loginFail("未知错误");
                }
            }
        });

//        RetrofitHelper.createUserApi().loginOld(count,pwd).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String msg= response.body().string();
//                    Log.d("xxf",msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }
}
