package com.fxx.pao.ui.article.articledetail;



import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *文章详情presenter
 * Created by fxx on 2017/8/13.
 */

class ArticleDetailPresenter extends ArticleDetailContract.Presenter{

    private ArticleDetailModel mArticleDetailModel;

    @Override
    public void loadArticleDetail(int articleId) {
        mCompositeDisposable.add(RetrofitHelper.createArticleApi().getArticleDetail(articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleDetailModel>() {
                    @Override
                    public void accept(ArticleDetailModel articleDetailModel) throws Exception {
                        mArticleDetailModel = articleDetailModel;
                        mView.onGetArticleDetailSuccess(mArticleDetailModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onGetArticleDetailFail(NetErrorUtil.handleThrowable(throwable));
                    }
                }));
    }

    @Override
    public void followUser(int userId) {
        mCompositeDisposable.add(RetrofitHelper.createUserApi().followUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMsgModel>() {
                    @Override
                    public void accept(BaseMsgModel baseMsgModel) throws Exception {
                        mView.onFollowSuccess(baseMsgModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onFollowFail(NetErrorUtil.handleThrowable(throwable));
                    }
                }));
    }

    @Override
    public void collect(int articleId) {
        mCompositeDisposable.add(RetrofitHelper.createUserApi().stow(articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMsgModel>() {
                    @Override
                    public void accept(BaseMsgModel baseMsgModel) throws Exception {
                        mView.collectSuccess(baseMsgModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.collectFail(NetErrorUtil.handleThrowable(throwable));
                    }
                }));

    }

    @Override
    public void praise(int articleId) {
        mCompositeDisposable.add(RetrofitHelper.createUserApi().praise(articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMsgModel>() {
                    @Override
                    public void accept(BaseMsgModel baseMsgModel) throws Exception {
                        mView.praiseSuccess(baseMsgModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.praiseFail(NetErrorUtil.handleThrowable(t));
                    }
                }));
    }

    public ArticleDetailModel getArticleDetailModel() {
        return mArticleDetailModel;
    }

    public void setArticleDetailModel(ArticleDetailModel articleDetailModel) {
        this.mArticleDetailModel = articleDetailModel;
    }
}
