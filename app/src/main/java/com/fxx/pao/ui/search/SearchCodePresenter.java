package com.fxx.pao.ui.search;


import com.fxx.pao.model.CodeModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *搜索代码presenter
 * Created by fxx on 2017/8/15 0015.
 */

class SearchCodePresenter extends SearchCodeContract.Presenter{

    private int p;

    @Override
    public void getInitSearchCodes(String keyword) {
        p=0;
        mCompositeDisposable.add(RetrofitHelper.createCodeApi().getSearchCode(p,0,keyword)
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
                }));
    }

    @Override
    public void getMoreSearchCodes(String keyword) {
        mCompositeDisposable.add(RetrofitHelper.createCodeApi().getSearchCode(++p,0,keyword)
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
                }));
    }
}
