package com.fxx.pao.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.event.LoginSuccessEvent;
import com.fxx.pao.model.MyProfileModel;
import com.fxx.pao.ui.login.LoginActivity;
import com.fxx.pao.ui.mine.myarticle.MyArticleActivity;
import com.fxx.pao.ui.mine.mycollection.MyCollectionActivity;
import com.fxx.pao.util.GlideUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 *
 * @author fxx
 * @date 2017/8/10 0010
 */

public class MineHomeFragment extends BaseFragment<MineHomePresenter> implements MineHomeContract.View {

    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_nick)
    TextView mTvNick;
    @BindView(R.id.tv_signature)
    TextView mTvSignature;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.bt_logout)
    Button mBtLogout;

    private boolean isLogin = false;

    @Override
    public MineHomePresenter getmPresenter() {
        return new MineHomePresenter();
    }

    @Override
    public void presenterSetView() {
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minehome;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        mPresenter.myProfile();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_head, R.id.tv_nick, R.id.rl_article, R.id.rl_collection, R.id.rl_msg, R.id.rl_set,
            R.id.bt_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
            case R.id.tv_nick:
                LoginActivity.start(getContext());
                break;
            case R.id.rl_article:
                if (isLogin) {
                    MyArticleActivity.start(getContext());
                } else {
                    LoginActivity.start(getContext());
                }
                break;
            case R.id.rl_collection:
                if (isLogin) {
                    MyCollectionActivity.start(getContext());
                } else {
                    LoginActivity.start(getContext());
                }
                break;
            case R.id.rl_msg:
                if (!isLogin) {
                    LoginActivity.start(getContext());
                }
                break;
            case R.id.rl_set:
                Toast.makeText(getContext(), "TODO", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_logout:
                mPresenter.logOut();
                break;
            default:
                break;
        }
    }

    @Override
    public void hasLogin(MyProfileModel myProfileModel) {
        initMyProfile(myProfileModel);
    }

    @Override
    public void getMyProfileFail(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSuccess() {
        clearMyProfile();
    }

    @Override
    public void logoutFailed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化个人信息
     *
     * @param myProfileModel 个人信息
     */
    private void initMyProfile(MyProfileModel myProfileModel) {
        if (myProfileModel == null || myProfileModel.getModel() == null) {
            Toast.makeText(getContext(), "未登录或登录已过期", Toast.LENGTH_SHORT).show();
            return;
        }
        isLogin = true;
        GlideUtil.loadHeadImage(myProfileModel.getModel().getFace()).into(mIvHead);
        mTvNick.setText(myProfileModel.getModel().getNickname());
        mTvSignature.setText(myProfileModel.getModel().getQianming());
        mTvFans.setText(String.valueOf(myProfileModel.getModel().getFans()));
        mTvAttention.setText(String.valueOf(myProfileModel.getModel().getGuanzhu()));
        mBtLogout.setVisibility(View.VISIBLE);
    }

    /**
     * 清空个人信息
     */
    private void clearMyProfile() {
        isLogin = false;
        mIvHead.setImageResource(R.drawable.ic_default_head);
        mTvNick.setText(R.string.click_login);
        mTvSignature.setText(R.string.no_signature);
        mTvFans.setText(R.string.zero);
        mTvAttention.setText(R.string.zero);
        mBtLogout.setVisibility(View.GONE);
    }

    /**
     * 处理登录成功消息
     *
     * @param loginSuccessEvent 消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(LoginSuccessEvent loginSuccessEvent) {
        mPresenter.myProfile();
    }
}
