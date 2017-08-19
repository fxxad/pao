package com.fxx.pao.ui.comment;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CommentModel;

import java.util.List;

/**
 *评论协议
 * Created by fxx on 2017/8/14 0014.
 */

interface CommentContract {
    interface View extends BaseView{
        void onGetCommentsSuccess(List<CommentModel.ItemsBean> items);
        void onAppendComments(List<CommentModel.ItemsBean> items);
        void onGetCommentsFail(String errorMSg);
    }
    public abstract class Presenter extends BaseRxPresenter<View> {
        abstract void loadInitComments(int id);
        abstract void loadMoreComments(int id);
    }
}
