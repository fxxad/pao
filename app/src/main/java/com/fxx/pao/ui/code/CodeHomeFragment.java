package com.fxx.pao.ui.code;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.CodeRvAdapter;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.event.ScrollToStartEvent;
import com.fxx.pao.model.CodeModel;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.ui.code.codedetail.CodeDetailActivity;
import com.fxx.pao.ui.search.SearchActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代码
 * Created by fxx on 2017/8/10 0010.
 */

public class CodeHomeFragment extends BaseFragment<CodeHomeContract.Presenter>
        implements CodeHomeContract.View,OnRefreshListener,OnLoadmoreListener, CodeRvAdapter.ItemClickListener {

    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_code)
    RecyclerView mRvCodes;
    @BindView(R.id.iv_search_code)
    ImageView mIvSearchCode;

    private CodeRvAdapter mAdapter;
    private List<CodeModel.ItemsBean> mItems;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_codehome;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public CodeHomeContract.Presenter getmPresenter() {
        return new CodeHomePresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null) mPresenter.setView(this);
    }

    @Override
    public void initView() {
        mRvCodes.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRvCodes.addItemDecoration(new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL));
        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);
    }

    @Override
    public void initData() {
        mItems = new ArrayList<>();
        mAdapter =new CodeRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvCodes.setAdapter(mAdapter);
    }

    @Override
    public void loadData() {
        mSrl.autoRefresh(0);
    }

    @Override
    public void refreshCodeItems(List<CodeModel.ItemsBean> itemsBeen) {
        mItems.clear();
        mItems.addAll(itemsBeen);
        mAdapter.notifyDataSetChanged();
        finishRefresh();
    }

    @Override
    public void appendCodeItems(List<CodeModel.ItemsBean> itemsBeen) {
        int oldSize=mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeInserted(oldSize,itemsBeen.size());
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
    public void loadCodesFail(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();

        if(!finishRefresh())
            finishLoadMore();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMoreCodeItems();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.loadInitCodeItems();
    }

    @Override
    public void onClick(int position) {
        CodeDetailActivity.start(this.getContext(),mItems.get(position).getId(),mItems.get(position).getTitle());
    }

    @OnClick({R.id.iv_search_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search_code:
                SearchActivity.start(getContext(),SearchActivity.SEARCHTYPE_CODE);
                break;
        }
    }

    /**
     * 处理列表滚动的顶部事件
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMsg(ScrollToStartEvent event){
        if(event.getCode() == ApiContants.TID_CODES){
//        mRvCodes.smoothScrollToPosition(0);//这里滑动过程会比较长
            if(mRvCodes.canScrollVertically(-1)){//不在顶部
                mRvCodes.scrollToPosition(0);
            }else{//已经滑动到顶部
                mSrl.autoRefresh(0);
            }
        }
    }
}
