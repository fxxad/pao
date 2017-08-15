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
 *
 * Created by fxx on 2017/8/13.
 */

public class ArticleDetailPresenter implements ArticleDetailContract.Presenter{

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
                }
            }

            @Override
            public void onFailure(Call<BaseMsgModel> call, Throwable t) {

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
