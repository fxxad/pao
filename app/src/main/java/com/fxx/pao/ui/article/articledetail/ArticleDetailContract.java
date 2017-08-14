package com.fxx.pao.ui.article.articledetail;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleDetailModel;

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
    }
    interface Presenter extends BasePresenter<ArticleDetailContract.View>{
        /**
         * 获取文章详情
         */
        void loadArticleDetail(int articleId);
    }
}
