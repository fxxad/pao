package com.fxx.pao.ui.mine.mycollection;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.fxx.pao.R;
import com.fxx.pao.adapter.CollectionVpAdapter;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.base.BasePresenter;

import butterknife.BindView;

/**
 * 收藏
 */
public class MyCollectionActivity extends BaseActivity {

    @BindView(R.id.stl_collection)
    SlidingTabLayout mStl;
    @BindView(R.id.vp_collection)
    ViewPager mVpCollection;
    @BindView(R.id.toolar_collection)
    Toolbar mToolbar;

    private CollectionVpAdapter mAdapter;

    public static void start(Context context){
        Intent intent =new Intent(context,MyCollectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    public BasePresenter getmPresenter() {
        return null;
    }

    @Override
    public void presenterSetView() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter =new CollectionVpAdapter(getSupportFragmentManager(),this);
        mVpCollection.setAdapter(mAdapter);
        mStl.setViewPager(mVpCollection);
    }

    @Override
    public void initData() {

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
}
