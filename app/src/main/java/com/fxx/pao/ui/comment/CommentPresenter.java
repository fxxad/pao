package com.fxx.pao.ui.comment;


import com.fxx.pao.model.CommentModel;
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
 *评论presenter
 * Created by fxx on 2017/8/14 0014.
 */

class CommentPresenter implements CommentContract.Presenter{
    private CommentContract.View mView;

    private int p;

    @Override
    public void setView(CommentContract.View view) {
        mView =view;
    }

    @Override
    public void removeView() {
        mView =null;
    }

    @Override
    public void loadInitComments(int id) {
        p = 0;
        RetrofitHelper.createArticleApi().getComments(id,p).enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                if(response.isSuccessful()){
                    mView.onGetCommentsSuccess(response.body().getItems());
                }else{
                    try {
                        mView.onGetCommentsFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.onGetCommentsFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.onGetCommentsFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.onGetCommentsFail("网络错误,端口被占用");
                    } else{
                        mView.onGetCommentsFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.onGetCommentsFail("连接丢失");
                }else{
                    mView.onGetCommentsFail("未知错误");
                }
            }
        });
    }

    @Override
    public void loadMoreComments(int id) {
        RetrofitHelper.createArticleApi().getComments(id,++p).enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                if(response.isSuccessful()){
                    mView.onAppendComments(response.body().getItems());
                }else{
                    try {
                        mView.onGetCommentsFail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    mView.onGetCommentsFail("连接超时");
                } else if (t instanceof SocketException) {
                    if (t instanceof ConnectException) {
                        mView.onGetCommentsFail("网络未连接");
                    } else if(t instanceof BindException){
                        mView.onGetCommentsFail("网络错误,端口被占用");
                    } else{
                        mView.onGetCommentsFail("网络错误");
                    }
                }else if(t instanceof EOFException){
                    mView.onGetCommentsFail("连接丢失");
                }else{
                    mView.onGetCommentsFail("未知错误");
                }
            }
        });
    }
}
