package com.fxx.pao.ui.article.articledetail;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.BaseMsgModel;

/**
 *文章详情协议
 * Created by fxx on 2017/8/13.
 */

interface ArticleDetailContract {
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

        void collectSuccess(BaseMsgModel baseMsgModel);

        void collectFail(String msg);

        void praiseSuccess(BaseMsgModel baseMsgModel);

        void praiseFail(String msg);
    }
    abstract class Presenter extends BaseRxPresenter<View> {
        /**
         * 获取文章详情
         */
        public abstract void loadArticleDetail(int articleId);

        /**
         * 关注
         * @param userId 用户id
         */
        public abstract void followUser(int userId);

        /**
         * 收藏
         * @param articleId 文章id
         */
        public abstract void collect(int articleId);

        /**
         * 点赞
         * @param articleId 文章id
         */
        public abstract void praise(int articleId);
    }
}
