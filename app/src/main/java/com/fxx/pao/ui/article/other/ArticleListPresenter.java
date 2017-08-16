package com.fxx.pao.ui.article.other;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.RetrofitHelper;

import java.io.EOFException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *文章列表presenter
 * Created by fxx on 2017/8/11 0011.
 */

class ArticleListPresenter implements ArticleListContract.Presenter {

    ArticleListContract.View mView;
    //分页数
    private int p;
    @Override
    public void setView(ArticleListContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void loadInitArticles(int tid) {
        p=0;
        RetrofitHelper.createArticleApi().getArticles(p,tid).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {

                mView.refreshArticles(response.body().getItems());
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
    public void loadMoreArticles(int tid) {
        RetrofitHelper.createArticleApi().getArticles(++p,tid).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                mView.appendArticles(response.body().getItems());
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
}
