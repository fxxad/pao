package com.fxx.pao.ui.mine.mycollection;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CollectionModel;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/15 0015.
 */

public interface CollectionArticleContract {
    interface View extends BaseView{
        void getCollectionArticlesSuccess(List<CollectionModel.ItemsBean> itemsBeen);
        void appendCollectionArticles(List<CollectionModel.ItemsBean> itemsBeen);
        void getCollectionArticlesFail(String msg);
    }
    interface Presenter extends BasePresenter<CollectionArticleContract.View>{
        void getMyCollectionArticles();
        void getMoreMyCollectionArticles();
    }
}
