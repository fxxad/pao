package com.fxx.pao.ui.code.codedetail;

import com.fxx.pao.base.BaseRxPresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.CodeDetailModel;

/**
 *代码详情协议
 * Created by fxx on 2017/8/11 0011.
 */

interface CodeDetailContract {
    interface View extends BaseView{
        /**
         * 获取代码详情成功
         * @param codeDetailModel 代码详情
         */
        void getCodeDetailSucces(CodeDetailModel codeDetailModel);

        /**
         * 获取代码详情失败
         * @param msg 错误信息
         */
        void getCodeDetailFailed(String msg);

        /**
         * 收藏成功
         * @param msg 反馈
         */
        void stowSuccess(BaseMsgModel msg);

        /**
         * 收藏失败
         * @param msg 错误信息
         */
        void stowFail(String msg);

    }
    public abstract class Presener extends BaseRxPresenter<View> {
        /**
         * 获取代码详情
         * @param codeId 代码id
         */
        abstract void getCodeDetail(int codeId);

        /**
         * 收藏
         * @param codeId 代码id
         */
        abstract void stow(int codeId);
    }

}
