package com.fxx.pao.ui.mine.mycollection;


import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *  收藏文章presenter
 * Created by fxx on 2017/8/15 0015.
 */

class CollectionArticlePresenter extends CollectionArticleContract.Presenter {
    private int p;

    @Override
    public void getMyCollectionArticles() {
        p =0;
        mCompositeDisposable.add(RetrofitHelper.createUserApi().collectionArticle(p, ApiContants.COLLECTION_ARTICLE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectionModel>() {
                    @Override
                    public void accept(CollectionModel collectionModel) throws Exception {
                        mView.getCollectionArticlesSuccess(collectionModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.getCollectionArticlesFail(NetErrorUtil.handleThrowable(t));
                    }
                }));
    }

    @Override
    public void getMoreMyCollectionArticles() {
        mCompositeDisposable.add(RetrofitHelper.createUserApi().collectionArticle(++p, ApiContants.COLLECTION_ARTICLE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectionModel>() {
                    @Override
                    public void accept(CollectionModel collectionModel) throws Exception {
                        mView.appendCollectionArticles(collectionModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.getCollectionArticlesFail(NetErrorUtil.handleThrowable(throwable));
                    }
                }));
    }
}
