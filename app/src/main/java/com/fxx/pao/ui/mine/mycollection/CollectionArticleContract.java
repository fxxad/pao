package com.fxx.pao.ui.mine.mycollection;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CollectionModel;

import java.util.List;

/**
 *  收藏文章协议
 * Created by fxx on 2017/8/15 0015.
 */

interface CollectionArticleContract {
    interface View extends BaseView{
        void getCollectionArticlesSuccess(List<CollectionModel.ItemsBean> itemsBeen);
        void appendCollectionArticles(List<CollectionModel.ItemsBean> itemsBeen);
        void getCollectionArticlesFail(String msg);
    }
    abstract static class Presenter extends BaseRxPresenter<View> {
        public abstract void getMyCollectionArticles();
        public abstract void getMoreMyCollectionArticles();
    }
}
