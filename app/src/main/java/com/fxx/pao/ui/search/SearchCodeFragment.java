package com.fxx.pao.ui.search;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.adapter.CodeRvAdapter;
import com.fxx.pao.base.BaseFragment;
import com.fxx.pao.model.CodeModel;
import com.fxx.pao.ui.code.codedetail.CodeDetailActivity;
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
 *
 * Created by fxx on 2017/8/15 0015.
 */

public class SearchCodeFragment extends BaseFragment<SearchCodePresenter> implements SearchCodeContract.View, OnRefreshListener, OnLoadmoreListener, CodeRvAdapter.ItemClickListener {
    @BindView(R.id.srl_search)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_result)
    RecyclerView mRvCodes;
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    private String mKeyword;
    private List<CodeModel.ItemsBean> mItems;
    private CodeRvAdapter mAdapter;

    @Override
    public SearchCodePresenter getmPresenter() {
        return new SearchCodePresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null)
            mPresenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView() {
        mRvCodes.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCodes.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mSrl.setOnRefreshListener(this);
        mSrl.setOnLoadmoreListener(this);

    }

    @Override
    public void initData() {
        mItems =new ArrayList<>();
        mAdapter = new CodeRvAdapter(mItems);
        mAdapter.setmItemClickListener(this);
        mRvCodes.setAdapter(mAdapter);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onInitSearchCodesSuccess(List<CodeModel.ItemsBean> itemsBeen) {
        mItems.clear();
        mItems.addAll(itemsBeen);
        mAdapter.notifyDataSetChanged();
        if(mSrl.isRefreshing()){
            mSrl.finishRefresh();
        }
    }

    @Override
    public void onLoadMoreSearchCodes(List<CodeModel.ItemsBean> itemsBeen) {
        int oldSize = mItems.size();
        mItems.addAll(itemsBeen);
        mAdapter.notifyItemRangeInserted(oldSize,itemsBeen.size());
        if(mSrl.isLoading())
            mSrl.finishLoadmore();
    }

    @Override
    public void onSearchCodesFail(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        if(mSrl.isRefreshing()){
            mSrl.finishRefresh();
            return;
        }
        if(mSrl.isLoading())
            mSrl.finishLoadmore();

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.getInitSearchCodes(mKeyword);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.getMoreSearchCodes(mKeyword);
    }

    @OnClick({R.id.iv_search,R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search:
                mKeyword= mEtKeyword.getText().toString().trim();
                if(mKeyword == null || mKeyword.equals("")){
                    Toast.makeText(getContext(),"请输入关键字",Toast.LENGTH_SHORT).show();
                    return;
                }
                mSrl.autoRefresh();
                Util.hideInput(mEtKeyword);
                break;
            case R.id.iv_back:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onClick(int position) {
        CodeDetailActivity.start(getContext(),mItems.get(position).getId(),mItems.get(position).getTitle());
    }
}
