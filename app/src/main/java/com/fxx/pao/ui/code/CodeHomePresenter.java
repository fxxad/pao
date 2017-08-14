package com.fxx.pao.ui.code;

import com.fxx.pao.model.CodeModel;
import com.fxx.pao.net.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public class CodeHomePresenter implements CodeHomeContract.Presenter{

    private CodeHomeContract.View mView;

    /**
     * 页数
     */
    private int mPageIndex=0;

    @Override
    public void setView(CodeHomeContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView=null;
    }

    @Override
    public void loadInitCodeItems() {
        mPageIndex = 0;
        Call<CodeModel> call = RetrofitHelper.createCodeApi().getCodeList(mPageIndex);
        call.enqueue(new Callback<CodeModel>() {
            @Override
            public void onResponse(Call<CodeModel> call, Response<CodeModel> response) {
                CodeModel codeModel=response.body();
                mView.refreshCodeItems(codeModel.getItems());
            }

            @Override
            public void onFailure(Call<CodeModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadMoreCodeItems(){
        Call<CodeModel> call = RetrofitHelper.createCodeApi().getCodeList(++mPageIndex);
        call.enqueue(new Callback<CodeModel>() {
            @Override
            public void onResponse(Call<CodeModel> call, Response<CodeModel> response) {
                CodeModel codeModel=response.body();
                mView.appendCodeItems(codeModel.getItems());
            }

            @Override
            public void onFailure(Call<CodeModel> call, Throwable t) {

            }
        });
    }

}
