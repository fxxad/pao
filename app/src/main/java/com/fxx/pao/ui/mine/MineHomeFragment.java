package com.fxx.pao.ui.mine;

import android.view.View;
import android.widget.ImageView;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.ui.login.LoginActivity;
import com.fxx.pao.ui.login.LoginContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 * Created by fxx on 2017/8/10 0010.
 */

public class MineHomeFragment extends BaseFragment{

    @BindView(R.id.iv_head)
    ImageView mIvHead;

    @Override
    public BasePresenter getmPresenter() {
        return null;
    }

    @Override
    public void presenterSetView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minehome;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

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
}
