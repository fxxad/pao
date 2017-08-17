package com.fxx.pao.ui.article.synthetical;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *  综合资讯presenter
 * Created by fxx on 2017/8/11 0011.
 */

class SyntheticalArticlePresenter implements SyntheticallArticleContract.Presenter {

    private SyntheticallArticleContract.View mView;
    /**
     * 分页数
     */
    private int p;

    @Override
    public void setView(SyntheticallArticleContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void loadInitArticles() {
        p=0;
        RetrofitHelper.createArticleApi().getArticles(p, ApiContants.TID_NEWS)
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
                });
    }

    @Override
    public void loadMoreArticles() {
        RetrofitHelper.createArticleApi().getArticles(++p,ApiContants.TID_NEWS)
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
                });
    }

    @Override
    public void loadBannerData() {
        RetrofitHelper.createArticleApi().slideArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.loadBannerDataSuccess(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.loadBannerDataFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }
}
