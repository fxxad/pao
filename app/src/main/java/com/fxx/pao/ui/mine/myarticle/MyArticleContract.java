package com.fxx.pao.ui.mine.myarticle;

import com.fxx.pao.base.BasePresenter;
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
    interface Presenter extends BasePresenter<MyArticleContract.View>{
        void myInitArticles();
        void myMoreArticles();
    }
}
