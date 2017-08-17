package com.fxx.pao.ui.mine.myarticle;


import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *  我的文章presenter
 * Created by fxx on 2017/8/15 0015.
 */

class MyArticlePresenter implements MyArticleContract.Presenter{

    private MyArticleContract.View mView;

    private int p;

    @Override
    public void setView(MyArticleContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void myInitArticles() {
        p=0;
        RetrofitHelper.createUserApi().myArticle(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.getMyArticlesSuccess(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.getMyArticlesFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }

    @Override
    public void myMoreArticles() {
        RetrofitHelper.createUserApi().myArticle(++p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleModel>() {
                    @Override
                    public void accept(ArticleModel articleModel) throws Exception {
                        mView.appendMyArticles(articleModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.getMyArticlesFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }
}
