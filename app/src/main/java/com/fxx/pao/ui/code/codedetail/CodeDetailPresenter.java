package com.fxx.pao.ui.code.codedetail;


import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.CodeDetailModel;
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
 *代码详情presenter
 * Created by fxx on 2017/8/11 0011.
 */

class CodeDetailPresenter implements CodeDetailContract.Presener {

    CodeDetailContract.View mView;

    @Override
    public void setView(CodeDetailContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void getCodeDetail(int codeId) {
        RetrofitHelper.createCodeApi().getCodeDetail(codeId).enqueue(new Callback<CodeDetailModel>() {
            @Override
            public void onResponse(Call<CodeDetailModel> call, Response<CodeDetailModel> response) {
                if(response.isSuccessful()){
                    mView.getCodeDetailSucces(response.body());
                }else{
                    try {
                        mView.getCodeDetailFailed(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<CodeDetailModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.getCodeDetailFailed("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.getCodeDetailFailed("网络未连接");
                    } else if(t instanceof BindException){
                        mView.getCodeDetailFailed("网络错误,端口被占用");
                    } else{
                        mView.getCodeDetailFailed("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.getCodeDetailFailed("连接丢失");
                }else{
                    mView.getCodeDetailFailed("未知错误");
                }
            }
        });
    }

    @Override
    public void stow(int codeId) {
        RetrofitHelper.createUserApi().stow(codeId).enqueue(new Callback<BaseMsgModel>() {
            @Override
            public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
                if(response.isSuccessful()){
                    mView.stowSuccess(response.body());
                }else{
                    try {
                        mView.stowFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.stowFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.stowFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.stowFail("网络错误,端口被占用");
                    } else{
                        mView.stowFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.stowFail("连接丢失");
                }else{
                    mView.stowFail("未知错误");
                }
            }
        });
    }
}
