package com.fxx.pao.ui.search;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/15 0015.
 */

class SearchArticlePresenter implements SearchArticleContract.Presenter{

    private SearchArticleContract.View mView;
    private int p;

    @Override
    public void setView(SearchArticleContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void getInitSearchArticles(String keyword) {
        p=0;
        RetrofitHelper.createArticleApi().getSearchArticles(keyword,p).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.onInitSearchArticlesSuccess(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void getMoreSearchArticles(String keyword) {
        RetrofitHelper.createArticleApi().getSearchArticles(keyword,++p).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                if(response.isSuccessful()){
                    mView.onLoadMoreSearchArticles(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {

            }
        });
    }

}
