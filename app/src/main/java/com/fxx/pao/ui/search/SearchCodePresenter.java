package com.fxx.pao.ui.search;

import com.fxx.pao.model.CodeModel;
import com.fxx.pao.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/15 0015.
 */

class SearchCodePresenter implements SearchCodeContract.Presenter{

    private SearchCodeContract.View mView;
    private int p;

    @Override
    public void setView(SearchCodeContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void getInitSearchCodes(String keyword) {
        p=0;
        RetrofitHelper.createCodeApi().getSearchCode(p,0,keyword).enqueue(new Callback<CodeModel>() {
            @Override
            public void onResponse(Call<CodeModel> call, Response<CodeModel> response) {
                if(response.isSuccessful()){
                    mView.onInitSearchCodesSuccess(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<CodeModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void getMoreSearchCodes(String keyword) {
        RetrofitHelper.createCodeApi().getSearchCode(++p,0,keyword).enqueue(new Callback<CodeModel>() {
            @Override
            public void onResponse(Call<CodeModel> call, Response<CodeModel> response) {
                if(response.isSuccessful()){
                    mView.onLoadMoreSearchCodes(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<CodeModel> call, Throwable t) {

            }
        });
    }
}
