package com.fxx.pao.ui.search;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.ArticleRvAdapter;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.ui.article.articledetail.ArticleDetailActivity;
import com.fxx.pao.util.Util;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文章搜索fragment
 *
 * @author fxx
 * @date 2017/8/15 0015
 */

public class SearchArticleFragment extends BaseFragment<SearchArticlePresenter> implements SearchArticleContract.View, OnRefreshListener, OnLoadmoreListener, ArticleRvAdapter.ItemClickListener {

    @BindView(R.id.srl_search)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_result)
    RecyclerView mRvArticles;
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    private String mKeyword;
    private List<ArticleModel.ItemsBean> mItems;
    private ArticleRvAdapter mAdapter;

    @Override
    public SearchArticlePresenter getmPresenter() {
        return new SearchArticlePresenter();
    }

    @Override
    public void presenterSetView() {
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView() {
        mRvArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvArticles.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);

        mEtKeyword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    search();
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mAdapter = new ArticleRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvArticles.setAdapter(mAdapter);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onInitSearchArticlesSuccess(List<ArticleModel.ItemsBean> itemsBeen) {
        mItems.clear();
        mItems.addAll(itemsBeen);
        mAdapter.notifyDataSetChanged();
        if (mSrl.isRefreshing()) {
            mSrl.finishRefresh();
        }
    }

    @Override
    public void onLoadMoreSearchArticles(List<ArticleModel.ItemsBean> itemsBeen) {
        int oldSize = mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeInserted(oldSize, itemsBeen.size());
        if (mSrl.isLoading()) {
            mSrl.finishLoadmore();
        }
    }

    @Override
    public void onSearchArticlesFail(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        if (mSrl.isRefreshing()) {
            mSrl.finishRefresh();
            return;
        }
        if (mSrl.isLoading()) {
            mSrl.finishLoadmore();
        }
    }


    @OnClick({R.id.iv_search, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                search();
                break;
            case R.id.iv_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.getInitSearchArticles(mKeyword);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.getMoreSearchArticles(mKeyword);
    }

    @Override
    public void onClick(int position) {
        ArticleDetailActivity.start(getContext(), mItems.get(position).getId(),
                mItems.get(position).getUser().getNickname(),
                mItems.get(position).getUser().getFace());
    }

    /**
     * 搜索
     */
    private void search() {
        mKeyword = mEtKeyword.getText().toString().trim();
        if ("".equals(mKeyword)) {
            Toast.makeText(getContext(), R.string.tip_input_keyword, Toast.LENGTH_SHORT).show();
            return;
        }
        mSrl.autoRefresh(0);
        Util.hideInput(mEtKeyword);
    }
}
