package com.fxx.pao.ui.code.codedetail;

import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.base.BaseView;
import com.fxx.pao.model.CodeDetailModel;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public interface CodeDetailContract {
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
    }
    interface Presener extends BasePresenter<View>{
        /**
         * 获取代码详情
         * @param codeId 代码id
         */
        void getCodeDetail(int codeId);
    }
}
