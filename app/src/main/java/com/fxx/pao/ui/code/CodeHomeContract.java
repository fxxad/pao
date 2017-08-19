package com.fxx.pao.ui.code;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CodeModel;

import java.util.List;

/**
 *code协议
 * Created by fxx on 2017/8/11 0011.
 */

interface CodeHomeContract {
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

        /**
         * 加载代码失败
         * @param msg 错误信息
         */
        void loadCodesFail(String msg);
    }
    public abstract class Presenter extends BaseRxPresenter<View> {
        /**
         * 加载首屏代码数据
         */
        abstract void loadInitCodeItems();

        /**
         * 加载更多
         */
        abstract void loadMoreCodeItems();
    }
}
