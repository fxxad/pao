package com.fxx.pao.ui.article.synthetical;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;

import java.util.List;

/**
 * 综合资讯协议
 * Created by fxx on 2017/8/11 0011.
 */

interface SyntheticallArticleContract {
    interface View extends BaseView{
        /**
         *  刷新首页列表
         * @param itemsBeen 列表数据
         */
        void refreshArticles(List<ArticleModel.ItemsBean> itemsBeen);

        /**
         *  首页列表加载更多
         * @param itemsBeen 列表数据
         */
        void appendArticles(List<ArticleModel.ItemsBean> itemsBeen);

        /**
         *  加载列表失败
         * @param msg 错误信息
         */
        void loadArticlesFail(String msg);

        /**
         * 轮播图加载
         * @param itemsBeen 轮播图数据
         */
        void loadBannerDataSuccess(List<ArticleModel.ItemsBean> itemsBeen);

        /**
         * 加载轮播图数据失败
         * @param msg 错误信息
         */
        void loadBannerDataFail(String msg);
    }
    abstract class Presenter extends BaseRxPresenter<View> {
        /**
         * 加载首页列表
         */
        abstract void loadInitArticles();

        /**
         * 加载更多列表
         */
        abstract void loadMoreArticles();

        /**
         * 加载轮播图
         */
        abstract void loadBannerData();
    }
}
