package com.fxx.pao.ui.article.synthetical;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.SyntheticalRvAdapter;
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
        OnLoadmoreListener, SyntheticalRvAdapter.ItemClickListener {

    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_all_articles)
    RecyclerView mRvAllArticles;
    private SyntheticalRvAdapter mAdapter;
    //列表数据
    private List<ArticleModel.ItemsBean> mItems;
    //轮播数据
    private List<ArticleModel.ItemsBean> mBannerItems;

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
    public void initView() {
        mRvAllArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvAllArticles.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);

    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mBannerItems = new ArrayList<>();
        mAdapter = new SyntheticalRvAdapter(mItems);
        mAdapter.setmBannerItems(mBannerItems);
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
        finishRefresh();
    }

    @Override
    public void appendArticles(List<ArticleModel.ItemsBean> itemsBeen) {
        int oldNum = mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeChanged(oldNum,itemsBeen.size());
        finishLoadMore();
    }

    @Override
    public void loadArticlesFail(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        if(!finishRefresh())
            finishLoadMore();
    }

    /**
     * 结束刷新动作
     * @return 是否结束刷新动作
     */
    private boolean finishRefresh(){
        if(mSrl.isRefreshing()) {
            mSrl.finishRefresh();
            return true;
        }else{
            return false;
        }
    }

    /**
     * 结束加载更多动作
     */
    private void finishLoadMore(){
        if(mSrl.isLoading())
            mSrl.finishLoadmore();
    }

    @Override
    public void loadBannerDataSuccess(List<ArticleModel.ItemsBean> itemsBeen) {
        mBannerItems.clear();
        mBannerItems.addAll(itemsBeen);
        mPresenter.loadInitArticles();
    }

    @Override
    public void loadBannerDataFail(String msg) {
        mPresenter.loadInitArticles();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.loadBannerData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMoreArticles();
    }

    /**
     * 列表项点击处理
     * @param position index
     */
    @Override
    public void onClick(int position) {
        ArticleDetailActivity.start(getContext(),mItems.get(position).getId()
                ,mItems.get(position).getUser().getNickname()
                ,mItems.get(position).getUser().getFace());
    }
}
