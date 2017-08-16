package com.fxx.pao.ui.mine.mycollection;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CollectionModel;

import java.util.List;

/**
 *  收藏代码协议
 * Created by fxx on 2017/8/15 0015.
 */

interface CollectionCodeContract {
    interface View extends BaseView{
        void loadInitCollectionCodesSuccess(List<CollectionModel.ItemsBean> itemsBeen);
        void loadMoreCollectionCodesSuccess(List<CollectionModel.ItemsBean> itemsBeen);
        void loadCollectionCodesFail(String msg);
    }
    interface Presenter extends BasePresenter<CollectionCodeContract.View>{
        void loadInitCollectionCodes();
        void loadMoreCollectionCodes();
    }
}
