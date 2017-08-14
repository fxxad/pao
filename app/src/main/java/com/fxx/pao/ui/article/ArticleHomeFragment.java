package com.fxx.pao.ui.article;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.fxx.pao.R;
import com.fxx.pao.adapter.ArticleHomeVpAdapter;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.base.BasePresenter;
import com.fxx.pao.ui.search.SearchActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文章
 * Created by fxx on 2017/8/10 0010.
 */

public class ArticleHomeFragment extends BaseFragment{

    @BindView(R.id.vp_articlehome)
    ViewPager mVp;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout mSliddeTab;
    @BindView(R.id.toolbar_article)
    Toolbar mToolbar;
    @BindView(R.id.iv_search_article)
    ImageView mIvSearch;

    private ArticleHomeVpAdapter mAdaper;


    @Override
    public BasePresenter getmPresenter() {
        return null;
    }

    @Override
    public void presenterSetView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_articlehome;
    }

    @Override
    public void initView() {
        mAdaper = new ArticleHomeVpAdapter(getChildFragmentManager(),this.getContext());
        mVp.setAdapter(mAdaper);
        mVp.setOffscreenPageLimit(5);
        mSliddeTab.setViewPager(mVp);
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.iv_search_article})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search_article:
                SearchActivity.start(getContext(),SearchActivity.SEARCHTYPE_ARTICLE);
                break;
        }
    }

}
