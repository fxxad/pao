package com.fxx.pao.ui.search;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/15 0015.
 */

interface SearchArticleContract {
    interface View extends BaseView{
        void onInitSearchArticlesSuccess(List<ArticleModel.ItemsBean> itemsBeen);
        void onLoadMoreSearchArticles(List<ArticleModel.ItemsBean> itemsBeen);
        void onSearchArticlesFail(String msg);

    }
    interface Presenter extends BasePresenter<SearchArticleContract.View>{
        void getInitSearchArticles(String keyword);
        void getMoreSearchArticles(String keyword);

    }
}
