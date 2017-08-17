package com.fxx.pao.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.fxx.pao.R;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.event.ScrollToStartEvent;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.ui.article.ArticleHomeFragment;
import com.fxx.pao.ui.code.CodeHomeFragment;
import com.fxx.pao.ui.mine.MineHomeFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bnb)
    BottomNavigationBar mBnb;

    private List<Fragment> mFgs;
    private int mLastFgIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter getmPresenter() {
        return null;
    }

    @Override
    public void presenterSetView() {
//        if(mPresenter != null)
//            mPresenter.setView(this);
    }

    @Override
    public void initView() {
        mBnb.addItem(new BottomNavigationItem(R.drawable.article,R.string.article))
                .addItem(new BottomNavigationItem(R.drawable.code,R.string.code))
                .addItem(new BottomNavigationItem(R.drawable.mine,R.string.mine))
                .setFirstSelectedPosition(0)
                .initialise();
        mBnb.setTabSelectedListener(this);
        initFragments();
        switchFragment(0);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onTabSelected(int position) {
        switchFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        switch (position){
            case 1:
                EventBus.getDefault().post(new ScrollToStartEvent(ApiContants.TID_CODES));
                break;
            default:
                break;
        }
    }

    /**
     * 初始化fragment
     */
    private void initFragments(){
        mFgs = new ArrayList<>();
        mFgs.add(new ArticleHomeFragment());
        mFgs.add(new CodeHomeFragment());
        mFgs.add(new MineHomeFragment());
    }


    /**
     * 切换fragment
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position){
        if(position >= mFgs.size()){
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFgs.get(position);
        Fragment lastFg= mFgs.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if(!targetFg.isAdded())
            ft.add(R.id.container,targetFg);
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }
}
