package com.fxx.pao.ui.mine.myarticle;


import com.fxx.pao.model.ArticleModel;
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
 *  我的文章presenter
 * Created by fxx on 2017/8/15 0015.
 */

class MyArticlePresenter implements MyArticleContract.Presenter{

    private MyArticleContract.View mView;

    private int p;

    @Override
    public void setView(MyArticleContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void myInitArticles() {
        p=0;
        RetrofitHelper.createUserApi().myArticle(p).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.getMyArticlesSuccess(response.body().getItems());
                }else{
                    try {
                        mView.getMyArticlesFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.getMyArticlesFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.getMyArticlesFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.getMyArticlesFail("网络错误,端口被占用");
                    } else{
                        mView.getMyArticlesFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.getMyArticlesFail("连接丢失");
                }else{
                    mView.getMyArticlesFail("未知错误");
                }
            }
        });
    }

    @Override
    public void myMoreArticles() {
        RetrofitHelper.createUserApi().myArticle(++p).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.appendMyArticles(response.body().getItems());
                }else{
                    try {
                        mView.getMyArticlesFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.getMyArticlesFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.getMyArticlesFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.getMyArticlesFail("网络错误,端口被占用");
                    } else{
                        mView.getMyArticlesFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.getMyArticlesFail("连接丢失");
                }else{
                    mView.getMyArticlesFail("未知错误");
                }
            }
        });
    }
}
