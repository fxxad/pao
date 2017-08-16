package com.fxx.pao.ui.article.articledetail;



import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.BaseMsgModel;
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
 *文章详情presenter
 * Created by fxx on 2017/8/13.
 */

class ArticleDetailPresenter implements ArticleDetailContract.Presenter{

    private ArticleDetailContract.View mView;

    private ArticleDetailModel articleDetailModel;
    @Override
    public void setView(ArticleDetailContract.View view) {
        mView =view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void loadArticleDetail(int articleId) {
        RetrofitHelper.createArticleApi().getArticleDetail(articleId).enqueue(new Callback<ArticleDetailModel>() {
            @Override
            public void onResponse(Call<ArticleDetailModel> call, Response<ArticleDetailModel> response) {
                if(response.isSuccessful()){
                    articleDetailModel = response.body();
                    mView.onGetArticleDetailSuccess(articleDetailModel);
                }else{
                    try {
                        mView.onGetArticleDetailFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.onGetArticleDetailFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.onGetArticleDetailFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.onGetArticleDetailFail("网络错误,端口被占用");
                    } else{
                        mView.onGetArticleDetailFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.onGetArticleDetailFail("连接丢失");
                }else{
                    mView.onGetArticleDetailFail("未知错误");
                }
            }
        });
    }

    @Override
    public void followUser(int userId) {
        RetrofitHelper.createUserApi().followUser(userId).enqueue(new Callback<BaseMsgModel>() {
            @Override
            public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
                if(response.isSuccessful()){
                    mView.onFollowSuccess(response.body());
                }else{
                    try {
                        mView.onFollowFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.onFollowFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.onFollowFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.onFollowFail("网络错误,端口被占用");
                    } else{
                        mView.onFollowFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.onFollowFail("连接丢失");
                }else{
                    mView.onFollowFail("未知错误");
                }
            }
        });
    }

    @Override
    public void collect(int articleId) {
        RetrofitHelper.createUserApi().stow(articleId).enqueue(new Callback<BaseMsgModel>() {
            @Override
            public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
                if(response.isSuccessful()){
                    mView.collectSuccess(response.body());
                }else{
                    try {
                        mView.collectFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.collectFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.collectFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.collectFail("网络错误,端口被占用");
                    } else{
                        mView.collectFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.collectFail("连接丢失");
                }else{
                    mView.collectFail("未知错误");
                }
            }
        });
    }

    @Override
    public void praise(int articleId) {
        RetrofitHelper.createUserApi().praise(articleId).enqueue(new Callback<BaseMsgModel>() {
            @Override
            public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
                if(response.isSuccessful()){
                    mView.praiseSuccess(response.body());
                }else{
                    try {
                        mView.praiseFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.praiseFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.praiseFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.praiseFail("网络错误,端口被占用");
                    } else{
                        mView.praiseFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.praiseFail("连接丢失");
                }else{
                    mView.praiseFail("未知错误");
                }
            }
        });
    }

    public ArticleDetailModel getArticleDetailModel() {
        return articleDetailModel;
    }

    public void setArticleDetailModel(ArticleDetailModel articleDetailModel) {
        this.articleDetailModel = articleDetailModel;
    }
}
