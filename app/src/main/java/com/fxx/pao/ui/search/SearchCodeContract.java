package com.fxx.pao.ui.search;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CodeModel;

import java.util.List;

/**
 *代码搜索协议
 * Created by fxx on 2017/8/15 0015.
 */

interface SearchCodeContract {
    interface View extends BaseView{
        void onInitSearchCodesSuccess(List<CodeModel.ItemsBean> itemsBeen);
        void onLoadMoreSearchCodes(List<CodeModel.ItemsBean> itemsBeen);
        void onSearchCodesFail(String msg);
    }
    abstract class Presenter extends BaseRxPresenter<View>{
        abstract void getInitSearchCodes(String keyword);
        abstract void getMoreSearchCodes(String keyword);
    }
}
