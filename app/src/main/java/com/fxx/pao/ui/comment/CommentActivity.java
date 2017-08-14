package com.fxx.pao.ui.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.CommentRvAdapter;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.model.CommentModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 评论页
 */
public class CommentActivity extends BaseActivity<CommentPresenter> implements CommentContract.View, CommentRvAdapter.ItemClickListener, OnRefreshListener, OnLoadmoreListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_comments)
    RecyclerView mRvComments;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;

    private CommentRvAdapter mAdapter;
    private List<CommentModel.ItemsBean> mItems;
    private int mId;

    public static void start(Context context,int id){
        Intent intent =new Intent(context,CommentActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    public CommentPresenter getmPresenter() {
        return new CommentPresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter !=null)
            mPresenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);

        mRvComments.setLayoutManager(new LinearLayoutManager(this));
        mRvComments.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mAdapter = new CommentRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvComments.setAdapter(mAdapter);

        mId = getIntent().getIntExtra("id",0);
//        mPresenter.loadInitComments(mId);
        mSrl.autoRefresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onGetCommentsSuccess(List<CommentModel.ItemsBean> items) {
        mItems.clear();
        mItems.addAll(items);
        mAdapter.notifyDataSetChanged();
        if(mSrl.isRefreshing())
            mSrl.finishRefresh();
    }

    @Override
    public void onAppendComments(List<CommentModel.ItemsBean> items) {
        int oldSize = mItems.size();
        mItems.addAll(items);
        mAdapter.notifyItemRangeChanged(oldSize,items.size());
        if(mSrl.isLoading())
            mSrl.finishLoadmore();
    }

    @Override
    public void onGetCommentsFail(String errorMSg) {
        Toast.makeText(this,errorMSg,Toast.LENGTH_SHORT).show();
        if(mSrl.isRefreshing()) {
            mSrl.finishRefresh();
            return;
        }
        if(mSrl.isLoading()) {
            mSrl.finishLoadmore();
        }
    }

    @Override
    public void onClick(int position) {


    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.loadInitComments(mId);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMoreComments(mId);
    }
}
