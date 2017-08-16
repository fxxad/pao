package com.fxx.pao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment基类
 * Created by fxx on 2017/8/10 0010.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment{

    protected boolean isViewCreated=false;
    protected boolean isUserVisible;

    protected P mPresenter;

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        mUnbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getmPresenter();
        presenterSetView();
        initView();
        initData();
        isViewCreated =true;
        lazyLoad();
    }

    /**
     * 获取presenter实例
     * @return presenter实例
     */
    public abstract P getmPresenter();

    /**
     * 设置presenter持有view引用
     */
    public abstract void presenterSetView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null)
            mUnbinder.unbind();

        if(mPresenter!=null)
            mPresenter.removeView();

        isViewCreated =false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isUserVisible = true;
            onVisible();
        }else{
            isUserVisible = false;
            onInVisible();
        }
    }
    private void onVisible(){
        lazyLoad();
    }
    private void onInVisible(){}


    public  void lazyLoad(){
        if(!isViewCreated || !isUserVisible){
            return;
        }
        loadData();
    }
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initData();
    public abstract void loadData();
}
