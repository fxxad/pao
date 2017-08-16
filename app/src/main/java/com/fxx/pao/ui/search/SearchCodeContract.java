package com.fxx.pao.ui.search;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.model.CodeModel;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/15 0015.
 */

interface SearchCodeContract {
    interface View extends BaseView{
        void onInitSearchCodesSuccess(List<CodeModel.ItemsBean> itemsBeen);
        void onLoadMoreSearchCodes(List<CodeModel.ItemsBean> itemsBeen);
        void onSearchCodesFail(String msg);
    }
    interface Presenter extends BasePresenter<SearchCodeContract.View>{
        void getInitSearchCodes(String keyword);
        void getMoreSearchCodes(String keyword);
    }
}
