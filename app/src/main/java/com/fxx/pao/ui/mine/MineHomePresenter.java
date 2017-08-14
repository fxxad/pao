package com.fxx.pao.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.fxx.pao.model.MyProfileModel;
import com.fxx.pao.net.RetrofitHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/14.
 */

public class MineHomePresenter implements MineHomeContract.Presenter{

    private MineHomeContract.View mView;

    @Override
    public void setView(MineHomeContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
            mView = null;
    }



    @Override
    public void checkLogin() {
//        RetrofitHelper.createUserApi().checkLogin().enqueue(new Callback<ResponseBody>() {
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
        RetrofitHelper.createUserApi().checkLogin().enqueue(new Callback<MyProfileModel>() {
            @Override
            public void onResponse(Call<MyProfileModel> call, Response<MyProfileModel> response) {
                if(response.isSuccessful()){
                    mView.hasLogin(response.body());
                }
            }

            @Override
            public void onFailure(Call<MyProfileModel> call, Throwable t) {

            }
        });
    }
}
