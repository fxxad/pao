package com.fxx.pao.ui.code;

import com.fxx.pao.model.CodeModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *code presenter
 * Created by fxx on 2017/8/11 0011.
 */

class CodeHomePresenter extends CodeHomeContract.Presenter{

    /**
     * 分页数
     */
    private int mPageIndex=0;

    @Override
    public void loadInitCodeItems() {
        mPageIndex = 0;
        mCompositeDisposable.add(RetrofitHelper.createCodeApi().getCodeList(mPageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeModel>() {
                    @Override
                    public void accept(CodeModel codeModel) throws Exception {
                        mView.refreshCodeItems(codeModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.loadCodesFail(NetErrorUtil.handleThrowable(t));
                    }
                }));
    }

    @Override
    public void loadMoreCodeItems(){
        mCompositeDisposable.add(RetrofitHelper.createCodeApi().getCodeList(++mPageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeModel>() {
                    @Override
                    public void accept(CodeModel codeModel) throws Exception {
                        mView.appendCodeItems(codeModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.loadCodesFail(NetErrorUtil.handleThrowable(t));
                    }
                }));
    }

}
