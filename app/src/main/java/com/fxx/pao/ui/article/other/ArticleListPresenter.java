package com.fxx.pao.ui.article.other;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public class ArticleListPresenter implements ArticleListContract.Presenter {

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

            }
        });
    }
}
