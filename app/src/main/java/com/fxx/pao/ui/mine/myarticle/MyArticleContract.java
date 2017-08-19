package com.fxx.pao.ui.mine.myarticle;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;

import java.util.List;

/**
 *  我的文章协议
 * Created by fxx on 2017/8/15 0015.
 */

interface MyArticleContract {
    interface View extends BaseView{
        void getMyArticlesSuccess(List<ArticleModel.ItemsBean> itemsBeen);
        void appendMyArticles(List<ArticleModel.ItemsBean> itemsBeen);
        void getMyArticlesFail(String msg);
    }
    abstract static class Presenter extends BaseRxPresenter<View> {
        public abstract void myInitArticles();
        public abstract void myMoreArticles();
    }
}
