package com.fxx.pao.ui.article.articledetail;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.BaseMsgModel;

/**
 *
 * Created by fxx on 2017/8/13.
 */

public interface ArticleDetailContract {
    interface View extends BaseView{
        /**
         * 文章详情获取成功
         */
        void onGetArticleDetailSuccess(ArticleDetailModel articleDetail);

        /**
         * 文章详情获取失败
         */
        void onGetArticleDetailFail(String msg);

        void onFollowSuccess(BaseMsgModel baseMsgModel);

        void onFollowFail(String msg);
    }
    interface Presenter extends BasePresenter<ArticleDetailContract.View>{
        /**
         * 获取文章详情
         */
        void loadArticleDetail(int articleId);

        /**
         * 关注
         * @param userId
         */
        void followUser(int userId);
    }
}
