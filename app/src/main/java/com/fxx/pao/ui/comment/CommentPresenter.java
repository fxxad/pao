package com.fxx.pao.ui.comment;


import com.fxx.pao.model.CommentModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
/**
 *评论presenter
 * Created by fxx on 2017/8/14 0014.
 */

class CommentPresenter implements CommentContract.Presenter{
    private CommentContract.View mView;

    private int p;

    @Override
    public void setView(CommentContract.View view) {
        mView =view;
    }

    @Override
    public void removeView() {
        mView =null;
    }

    @Override
    public void loadInitComments(int id) {
        p = 0;
        RetrofitHelper.createArticleApi().getComments(id,p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommentModel>() {
                    @Override
                    public void accept(CommentModel commentModel) throws Exception {
                        mView.onGetCommentsSuccess(commentModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                       mView.onGetCommentsFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }

    @Override
    public void loadMoreComments(int id) {
        RetrofitHelper.createArticleApi().getComments(id,++p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommentModel>() {
                    @Override
                    public void accept(CommentModel commentModel) throws Exception {
                        mView.onAppendComments(commentModel.getItems());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.onGetCommentsFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }
}
