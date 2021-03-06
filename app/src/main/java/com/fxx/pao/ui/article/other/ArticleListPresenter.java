package com.fxx.pao.ui.article.other;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *文章列表presenter
 * Created by fxx on 2017/8/11 0011.
 */

class ArticleListPresenter extends ArticleListContract.Presenter {
    //分页数
    private int p;

    @Override
    public void loadInitArticles(int tid) {
        p=0;
        mCompositeDisposable.add(RetrofitHelper.createArticleApi().getArticles(p,tid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.refreshArticles(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.loadArticlesFail(NetErrorUtil.handleThrowable(t));
                    }
                }));
    }

    @Override
    public void loadMoreArticles(int tid) {
        mCompositeDisposable.add(RetrofitHelper.createArticleApi().getArticles(++p,tid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.appendArticles(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.loadArticlesFail(NetErrorUtil.handleThrowable(t));
                    }
                }));
    }
}
