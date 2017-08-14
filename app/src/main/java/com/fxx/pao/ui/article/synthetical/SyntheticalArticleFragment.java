package com.fxx.pao.ui.article.synthetical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fxx.pao.R;
import com.fxx.pao.adapter.ArticleRvAdapter;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.ui.article.articledetail.ArticleDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 综合资讯
 * Created by fxx on 2017/8/10 0010.
 */

public class SyntheticalArticleFragment extends BaseFragment<SyntheticallArticleContract.Presenter>
        implements SyntheticallArticleContract.View, OnRefreshListener,
        OnLoadmoreListener, ArticleRvAdapter.ItemClickListener {

    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_all_articles)
    RecyclerView mRvAllArticles;
    private ArticleRvAdapter mAdapter;
    private List<ArticleModel.ItemsBean> mItems;

    @Override
    public SyntheticallArticleContract.Presenter getmPresenter() {
        return new SyntheticalArticlePresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null)
            mPresenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_synthetical_article;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initView() {
        mRvAllArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvAllArticles.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);
    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mAdapter = new ArticleRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvAllArticles.setAdapter(mAdapter);
    }


    @Override
    public void loadData() {
        mSrl.autoRefresh();
    }

    @Override
    public void refreshArticles(List<ArticleModel.ItemsBean> itemsBeen) {
        mItems.clear();
        mItems.addAll(itemsBeen);
        mAdapter.notifyDataSetChanged();
        if(mSrl.isRefreshing())
            mSrl.finishRefresh();
    }

    @Override
    public void appendArticles(List<ArticleModel.ItemsBean> itemsBeen) {
        int oldNum = mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeChanged(oldNum,itemsBeen.size());
        if(mSrl.isLoading())
            mSrl.finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.loadInitArticles();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMoreArticles();
    }


    @Override
    public void onClick(int position) {
        ArticleDetailActivity.start(getContext(),mItems.get(position).getId()
                ,mItems.get(position).getUser().getNickname()
                ,mItems.get(position).getUser().getFace());
    }
}
