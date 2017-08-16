package com.fxx.pao.ui.article.synthetical;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.ApiContants;
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
 *  综合资讯presenter
 * Created by fxx on 2017/8/11 0011.
 */

class SyntheticalArticlePresenter implements SyntheticallArticleContract.Presenter {

    SyntheticallArticleContract.View mView;
    /**
     * 分页数
     */
    private int p;

    @Override
    public void setView(SyntheticallArticleContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void loadInitArticles() {
        p=0;
        RetrofitHelper.createArticleApi().getArticles(p, ApiContants.TID_NEWS).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.refreshArticles(response.body().getItems());
                }else{
                    try {
                        mView.loadArticlesFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.loadArticlesFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.loadArticlesFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.loadArticlesFail("网络错误,端口被占用");
                    } else{
                        mView.loadArticlesFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.loadArticlesFail("连接丢失");
                }else{
                    mView.loadArticlesFail("未知错误");
                }
            }
        });
    }

    @Override
    public void loadMoreArticles() {
        RetrofitHelper.createArticleApi().getArticles(++p,ApiContants.TID_NEWS).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.appendArticles(response.body().getItems());
                }else{
                    try {
                        mView.loadArticlesFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.loadArticlesFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.loadArticlesFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.loadArticlesFail("网络错误,端口被占用");
                    } else{
                        mView.loadArticlesFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.loadArticlesFail("连接丢失");
                }else{
                    mView.loadArticlesFail("未知错误");
                }
            }
        });
    }

    @Override
    public void loadBannerData() {
        RetrofitHelper.createArticleApi().slideArticles().enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.loadBannerDataSuccess(response.body().getItems());
                }else{
                    try {
                        mView.loadBannerDataFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.loadBannerDataFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.loadBannerDataFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.loadBannerDataFail("网络错误,端口被占用");
                    } else{
                        mView.loadBannerDataFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.loadBannerDataFail("连接丢失");
                }else{
                    mView.loadBannerDataFail("未知错误");
                }
            }
        });
    }
}
