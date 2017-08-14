package com.fxx.pao.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxx.pao.R;
import com.fxx.pao.adapter.ArticleRvAdapter$MyViewHolder_ViewBinding;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.model.MyProfileModel;
import com.fxx.pao.ui.login.LoginActivity;
import com.fxx.pao.ui.login.LoginContract;
import com.fxx.pao.util.GlideUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 * Created by fxx on 2017/8/10 0010.
 */

public class MineHomeFragment extends BaseFragment<MineHomePresenter> implements MineHomeContract.View {

    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_nick)
    TextView mTvNick;

    @Override
    public MineHomePresenter getmPresenter() {
        return new MineHomePresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null)
            mPresenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minehome;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        mPresenter.checkLogin();
    }

    @OnClick({R.id.iv_head,R.id.tv_nick})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_head:
            case R.id.tv_nick:
                LoginActivity.start(getContext());
                break;
        }
    }

    @Override
    public void hasLogin(MyProfileModel myProfileModel) {
        initMyProfile(myProfileModel);
    }

    /**
     *
     * @param myProfileModel
     */
    private void initMyProfile(MyProfileModel myProfileModel){
        if(myProfileModel == null)
            return;
        GlideUtil.loadHeadImage(myProfileModel.getModel().getFace()).into(mIvHead);
        mTvNick.setText(myProfileModel.getModel().getNickname());
    }
}
