package com.fxx.pao.ui.search;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;

import java.util.List;

/**
 *文章搜索协议
 * Created by fxx on 2017/8/15 0015.
 */

interface SearchArticleContract {
    interface View extends BaseView{
        void onInitSearchArticlesSuccess(List<ArticleModel.ItemsBean> itemsBeen);
        void onLoadMoreSearchArticles(List<ArticleModel.ItemsBean> itemsBeen);
        void onSearchArticlesFail(String msg);

    }
    abstract static class Presenter extends BaseRxPresenter<View> {
        public abstract void getInitSearchArticles(String keyword);
        public abstract void getMoreSearchArticles(String keyword);
    }
}
