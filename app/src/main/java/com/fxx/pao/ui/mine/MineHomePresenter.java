package com.fxx.pao.ui.mine;

import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.MyProfileModel;
import com.fxx.pao.net.RetrofitHelper;

import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *个人主页presenter
 * Created by fxx on 2017/8/14.
 */

class MineHomePresenter implements MineHomeContract.Presenter{

    private MineHomeContract.View mView;
    private MyProfileModel myProfileModel;

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
        RetrofitHelper.createUserApi().myProfile().enqueue(new Callback<MyProfileModel>() {
            @Override
            public void onResponse(Call<MyProfileModel> call, Response<MyProfileModel> response) {
                if(response.isSuccessful()){
                    myProfileModel = response.body();
                    mView.hasLogin(myProfileModel);
                }else{
                    try {
                        mView.getMyProfileFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyProfileModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.getMyProfileFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.getMyProfileFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.getMyProfileFail("网络错误,端口被占用");
                    } else{
                        mView.getMyProfileFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.getMyProfileFail("连接丢失");
                }else{
                    mView.getMyProfileFail("未知错误");
                }
            }
        });
    }

    @Override
    public void logOut() {
        RetrofitHelper.createUserApi().logout().enqueue(new Callback<BaseMsgModel>() {
            @Override
            public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getSucess()==1){
                        mView.logoutSuccess();
                    }else{
                        try {
                            mView.logoutFailed(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {

            }
        });
    }
}
