package com.fxx.pao.ui.article.other;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
 * 文章列表通用fragment
 * Created by fxx on 2017/8/10 0010.
 */

public class ArticleListFragment extends BaseFragment<ArticleListContract.Presenter> implements ArticleListContract.View, OnRefreshListener, OnLoadmoreListener, ArticleRvAdapter.ItemClickListener {

    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_all_articles)
    RecyclerView mRvAllArticles;
    private ArticleRvAdapter mAdapter;
    private List<ArticleModel.ItemsBean> mItems;

    /**
     * fragment对应的文章类型tid
     */
    private int mTid;

    public static ArticleListFragment getInstance(int tid){
        ArticleListFragment instance =new ArticleListFragment();
        Bundle bundle =new Bundle();
        bundle.putInt("tid",tid);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public ArticleListContract.Presenter getmPresenter() {
        return new ArticleListPresenter();
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
        mTid = getArguments().getInt("tid",0);
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
        mPresenter.loadInitArticles(mTid);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMoreArticles(mTid);
    }

    @Override
    public void onClick(int position) {
        ArticleDetailActivity.start(getContext(),mItems.get(position).getId(),
                mItems.get(position).getUser().getNickname(),
                mItems.get(position).getUser().getFace());
    }
}
