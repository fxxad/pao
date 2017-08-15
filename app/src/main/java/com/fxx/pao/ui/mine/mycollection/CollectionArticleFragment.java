package com.fxx.pao.ui.mine.mycollection;

import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.ArticleRvAdapter;
import com.fxx.pao.adapter.CollectionRvAdapter;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.ui.article.articledetail.ArticleDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *收藏文章
 * Created by fx on 2017/8/15 0015.
 */

public class CollectionArticleFragment extends BaseFragment<CollectionArticlePresenter> implements CollectionArticleContract.View, OnRefreshListener, OnLoadmoreListener, CollectionRvAdapter.ItemClickListener {

    @BindView(R.id.srl_collection_article)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_collection_article)
    RecyclerView mRvArticle;

    private List<CollectionModel.ItemsBean> mItems;
    private CollectionRvAdapter mAdapter;

    @Override
    public CollectionArticlePresenter getmPresenter() {
        return new CollectionArticlePresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter!=null)
            mPresenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collection_article;
    }

    @Override
    public void initView() {
        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);

        mRvArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvArticle.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mAdapter = new CollectionRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvArticle.setAdapter(mAdapter);
    }

    @Override
    public void loadData() {
        mSrl.autoRefresh();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.getMyCollectionArticles();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mSrl.autoLoadmore();
    }

    @Override
    public void getCollectionArticlesSuccess(List<CollectionModel.ItemsBean> itemsBeen) {
        if(itemsBeen!=null && itemsBeen.size()>0){
            mItems.clear();
            mItems.addAll(itemsBeen);
            mAdapter.notifyDataSetChanged();
        }
        if(mSrl.isRefreshing())
            mSrl.finishRefresh();
    }

    @Override
    public void appendCollectionArticles(List<CollectionModel.ItemsBean> itemsBeen) {
        int oldSize = mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeInserted(oldSize,itemsBeen.size());
        if(mSrl.isLoading())
            mSrl.finishLoadmore();
    }

    @Override
    public void getCollectionArticlesFail(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        if(mSrl.isRefreshing()) {
            mSrl.finishRefresh();
            return;
        }
        if(mSrl.isLoading())
            mSrl.finishLoadmore();
    }

    @Override
    public void onClick(int position) {
        ArticleDetailActivity.start(getContext(),mItems.get(position).getId(),
                mItems.get(position).getUser().getNickname(),
                mItems.get(position).getUser().getFace());
    }
}
