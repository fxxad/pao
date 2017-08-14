package com.fxx.pao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类
 * Created by fxx on 2017/8/10 0010.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity{


    protected P mPresenter;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder=ButterKnife.bind(this);
        mPresenter = getmPresenter();
        presenterSetView();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null)
            mUnbinder.unbind();
        if(mPresenter!=null)
            mPresenter.removeView();
    }

    public abstract P getmPresenter();

    public abstract void presenterSetView();

    public abstract void initView();

    public abstract void initData();

    public abstract int getLayoutId();
}
