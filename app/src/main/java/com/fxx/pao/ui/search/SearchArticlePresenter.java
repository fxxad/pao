package com.fxx.pao.ui.search;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
/**
 *搜索文章presenter
 * Created by fxx on 2017/8/15 0015.
 */

class SearchArticlePresenter implements SearchArticleContract.Presenter{

    private SearchArticleContract.View mView;
    private int p;

    @Override
    public void setView(SearchArticleContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void getInitSearchArticles(String keyword) {
        p=0;
        RetrofitHelper.createArticleApi().getSearchArticles(keyword,p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.onInitSearchArticlesSuccess(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onSearchArticlesFail(NetErrorUtil.handleThrowable(throwable));
                    }
                });
    }

    @Override
    public void getMoreSearchArticles(String keyword) {
        RetrofitHelper.createArticleApi().getSearchArticles(keyword,++p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.onLoadMoreSearchArticles(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onSearchArticlesFail(NetErrorUtil.handleThrowable(throwable));
                    }
                });
    }

}
