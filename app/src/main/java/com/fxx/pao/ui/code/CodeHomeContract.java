package com.fxx.pao.ui.code;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CodeModel;

import java.util.List;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public interface CodeHomeContract {
    interface View extends BaseView{
        /**
         * 刷新列表
         * @param itemsBeen items
         */
        void refreshCodeItems(List<CodeModel.ItemsBean> itemsBeen);
        /**
         * 列表加载更多
         * @param itemsBeen 新加项
         */
        void appendCodeItems(List<CodeModel.ItemsBean> itemsBeen);
    }
    interface Presenter extends BasePresenter<View> {
        void loadInitCodeItems();
        void loadMoreCodeItems();
    }
}
