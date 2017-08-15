package com.fxx.pao.ui.mine.mycollection;


import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/15 0015.
 */

public class CollectionArticlePresenter implements CollectionArticleContract.Presenter{
    private int p;

    private CollectionArticleContract.View mView;


    @Override
    public void setView(CollectionArticleContract.View view) {
        mView =view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void getMyCollectionArticles() {
        p =0;
        RetrofitHelper.createUserApi().collectionArticle(p, ApiContants.COLLECTION_ARTICLE).enqueue(new Callback<CollectionModel>() {
            @Override
            public void onResponse(Call<CollectionModel> call, Response<CollectionModel> response) {
                if(response.isSuccessful()){
                    mView.getCollectionArticlesSuccess(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<CollectionModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void getMoreMyCollectionArticles() {
        RetrofitHelper.createUserApi().collectionArticle(++p, ApiContants.COLLECTION_ARTICLE).enqueue(new Callback<CollectionModel>() {
            @Override
            public void onResponse(Call<CollectionModel> call, Response<CollectionModel> response) {
                if(response.isSuccessful()){
                    mView.appendCollectionArticles(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<CollectionModel> call, Throwable t) {

            }
        });
    }
}
