package com.fxx.pao.ui.code.codedetail;


import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.CodeDetailModel;
import com.fxx.pao.net.RetrofitHelper;
import com.fxx.pao.util.NetErrorUtil;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *代码详情presenter
 * Created by fxx on 2017/8/11 0011.
 */

class CodeDetailPresenter implements CodeDetailContract.Presener {

    private CodeDetailContract.View mView;

    @Override
    public void setView(CodeDetailContract.View view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    @Override
    public void getCodeDetail(int codeId) {
        RetrofitHelper.createCodeApi().getCodeDetail(codeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeDetailModel>() {
                    @Override
                    public void accept(CodeDetailModel codeDetailModel) throws Exception {
                        mView.getCodeDetailSucces(codeDetailModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.getCodeDetailFailed(NetErrorUtil.handleThrowable(t));
                    }
                });
    }

    @Override
    public void stow(int codeId) {
        RetrofitHelper.createUserApi().stow(codeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMsgModel>() {
                    @Override
                    public void accept(BaseMsgModel baseMsgModel) throws Exception {
                        mView.stowSuccess(baseMsgModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        mView.stowFail(NetErrorUtil.handleThrowable(t));
                    }
                });
    }
}
