package com.fxx.pao.ui.comment;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CommentModel;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public interface CommentContract {
    interface View extends BaseView{
        void onGetCommentsSuccess(List<CommentModel.ItemsBean> items);
        void onAppendComments(List<CommentModel.ItemsBean> items);
        void onGetCommentsFail(String errorMSg);
    }
    interface Presenter extends BasePresenter<View>{
        void loadInitComments(int id);
        void loadMoreComments(int id);
    }
}
