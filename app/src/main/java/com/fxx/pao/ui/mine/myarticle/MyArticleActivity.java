package com.fxx.pao.ui.mine.myarticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.ArticleRvAdapter;
import com.fxx.pao.base.BaseActivity;
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
 *我的文章
 * Created by fxx on 2017/8/11
 * @author fxx
 */
public class MyArticleActivity extends BaseActivity<MyArticlePresenter> implements MyArticleContract.View, OnRefreshListener, OnLoadmoreListener, ArticleRvAdapter.ItemClickListener {

    @BindView(R.id.toolbar_my_article)
    Toolbar mToolbar;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_my_article)
    RecyclerView mRvArticle;
    @BindView(R.id.tv_no_data)
    TextView mTvNoData;

    private ArticleRvAdapter mAdapter;
    private List<ArticleModel.ItemsBean> mItems;

    public static void start(Context context){
        Intent intent = new Intent(context,MyArticleActivity.class);
        context.startActivity(intent);
    }

    @Override
    public MyArticlePresenter getmPresenter() {
        return new MyArticlePresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_article;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);

        mRvArticle.setLayoutManager(new LinearLayoutManager(this));
        mRvArticle.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mAdapter = new ArticleRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvArticle.setAdapter(mAdapter);
        mSrl.autoRefresh(0);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.myInitArticles();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.myMoreArticles();
    }

    @Override
    public void getMyArticlesSuccess(List<ArticleModel.ItemsBean> itemsBeen) {
        if(itemsBeen !=null && itemsBeen.size() > 0){
            mItems.clear();
            mItems.addAll(itemsBeen);
            mAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this,R.string.no_data,Toast.LENGTH_SHORT).show();
        }
        if(mSrl.isRefreshing()) {
            mSrl.finishRefresh();
        }
    }

    @Override
    public void appendMyArticles(List<ArticleModel.ItemsBean> itemsBeen) {
        int oldSize = mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeInserted(oldSize,itemsBeen.size());
        if(mSrl.isLoading()){
            mSrl.finishLoadmore();
        }
    }

    @Override
    public void getMyArticlesFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
                default:
                    break;
        }
        return true;
    }

    @Override
    public void onClick(int position) {
        ArticleDetailActivity.start(MyArticleActivity.this,mItems.get(position).getId(),
                mItems.get(position).getUser().getNickname(),
                mItems.get(position).getUser().getFace());
    }
}
