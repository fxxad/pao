package com.fxx.pao.ui.search;

import android.util.Log;

import com.fxx.pao.model.CodeModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *搜索代码presenter
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
        RetrofitHelper.createCodeApi().getSearchCode(p,0,keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeModel>() {
                    @Override
                    public void accept(CodeModel codeModel) throws Exception {
                        mView.onInitSearchCodesSuccess(codeModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onSearchCodesFail(NetErrorUtil.handleThrowable(throwable));
                    }
                });
    }

    @Override
    public void getMoreSearchCodes(String keyword) {
        RetrofitHelper.createCodeApi().getSearchCode(++p,0,keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeModel>() {
                    @Override
                    public void accept(CodeModel codeModel) throws Exception {
                        mView.onLoadMoreSearchCodes(codeModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.onSearchCodesFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }
}
