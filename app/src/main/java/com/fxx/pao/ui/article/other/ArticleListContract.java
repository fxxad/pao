package com.fxx.pao.ui.article.other;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;

import java.util.List;

/**
 *文章列表协议
 * Created by fxx on 2017/8/11 0011.
 */

interface ArticleListContract {
    interface View extends BaseView{
        /**
         *首页列表
         * @param itemsBeen 列表数据
         */
        void refreshArticles(List<ArticleModel.ItemsBean> itemsBeen);

        /**
         *更多列表
         * @param itemsBeen 列表数据
         */
        void appendArticles(List<ArticleModel.ItemsBean> itemsBeen);

        /**
         *加载失败
         * @param msg 错误信息
         */
        void loadArticlesFail(String msg);
    }
    interface Presenter extends BasePresenter<View>{
        /**
         * 加载首页文章
         * @param tid 组id
         */
        void loadInitArticles(int tid);

        /**
         * 加载更多文章
         * @param tid 组id
         */
        void loadMoreArticles(int tid);
    }
}
