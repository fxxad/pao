package com.fxx.pao.ui.mine.mycollection;


import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  收藏代码presenter
 * Created by fxx on 2017/8/15 0015.
 */

public class CollectionCodePresenter implements CollectionCodeContract.Presenter{
    private int p;

    private CollectionCodeContract.View mView;

    @Override
    public void setView(CollectionCodeContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView =null;
    }

    @Override
    public void loadInitCollectionCodes() {
        p=0;
        RetrofitHelper.createUserApi().collectionArticle(p, ApiContants.COLLECTION_CODE).enqueue(new Callback<CollectionModel>() {
            @Override
            public void onResponse(Call<CollectionModel> call, Response<CollectionModel> response) {
                if(response.isSuccessful()){
                    mView.loadInitCollectionCodesSuccess(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<CollectionModel> call, Throwable t) {
                //TODO
            }
        });
    }

    @Override
    public void loadMoreCollectionCodes() {
        RetrofitHelper.createUserApi().collectionArticle(++p, ApiContants.COLLECTION_CODE).enqueue(new Callback<CollectionModel>() {
            @Override
            public void onResponse(Call<CollectionModel> call, Response<CollectionModel> response) {
                if(response.isSuccessful()){
                    mView.loadMoreCollectionCodesSuccess(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<CollectionModel> call, Throwable t) {
                //TODO
            }
        });
    }
}
