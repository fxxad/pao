package com.fxx.pao.ui.mine.mycollection;


import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
/**
 *  收藏代码presenter
 * Created by fxx on 2017/8/15 0015.
 */

class CollectionCodePresenter implements CollectionCodeContract.Presenter{
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
        RetrofitHelper.createUserApi().collectionArticle(p, ApiContants.COLLECTION_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectionModel>() {
                    @Override
                    public void accept(CollectionModel collectionModel) throws Exception {
                        mView.loadInitCollectionCodesSuccess(collectionModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.loadCollectionCodesFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }

    @Override
    public void loadMoreCollectionCodes() {
        RetrofitHelper.createUserApi().collectionArticle(++p, ApiContants.COLLECTION_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectionModel>() {
                    @Override
                    public void accept(CollectionModel collectionModel) throws Exception {
                        mView.loadMoreCollectionCodesSuccess(collectionModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.loadCollectionCodesFail(NetErrorUtil.handleThrowable(throwable));
                    }
                });
    }
}
