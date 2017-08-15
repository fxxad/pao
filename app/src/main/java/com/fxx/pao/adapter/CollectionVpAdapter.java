package com.fxx.pao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fxx.pao.R;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.ui.article.other.ArticleListFragment;
import com.fxx.pao.ui.article.synthetical.SyntheticalArticleFragment;
import com.fxx.pao.ui.mine.mycollection.CollectionArticleFragment;
import com.fxx.pao.ui.mine.mycollection.CollectionCodeFragment;

/**
 *
 * Created by fxx on 2017/8/10 0010.
 */

public class CollectionVpAdapter extends FragmentPagerAdapter{

    private Fragment[] mFgs;
    private String[] mTitles;

    public CollectionVpAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.collection);
        mFgs = new Fragment[mTitles.length];
    }

    @Override
    public Fragment getItem(int position) {
        if(mFgs[position] == null){
            switch (position){
                case 0://文章
                    mFgs[0] = new CollectionArticleFragment();
                    break;
                case 1://代码
                    mFgs[1] = new CollectionCodeFragment();
                    break;
                default:
                    break;
            }
        }
        return mFgs[position];
    }

    @Override
    public int getCount() {
        return mFgs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
