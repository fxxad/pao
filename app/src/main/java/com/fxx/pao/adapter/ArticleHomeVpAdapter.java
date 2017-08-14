package com.fxx.pao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fxx.pao.R;
import com.fxx.pao.net.ApiContants;
import com.fxx.pao.ui.article.synthetical.SyntheticalArticleFragment;
import com.fxx.pao.ui.article.other.ArticleListFragment;

/**
 *
 * Created by fxx on 2017/8/10 0010.
 */

public class ArticleHomeVpAdapter extends FragmentPagerAdapter{

    private Fragment[] mFgs;
    private String[] mTitles;

    public ArticleHomeVpAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.article_titles);
        mFgs = new Fragment[mTitles.length];
    }

    @Override
    public Fragment getItem(int position) {
        if(mFgs[position] == null){
            switch (position){
                case 0://所有
                    mFgs[0] = new SyntheticalArticleFragment();
                    break;
                case 1://安卓开发
                    mFgs[1] = ArticleListFragment.getInstance(ApiContants.TID_ANDROID);
                    break;
                case 2://程序设计
                    mFgs[2] = ArticleListFragment.getInstance(ApiContants.TID_PROGRAM_DESIGN);
                    break;
                case 3://前端
                    mFgs[3] = ArticleListFragment.getInstance(ApiContants.TID_FRONT);
                    break;
                case 4://ios
                    mFgs[4] = ArticleListFragment.getInstance(ApiContants.TID_IOS);
                    break;
                case 5://数据库
                    mFgs[5] = ArticleListFragment.getInstance(ApiContants.TID_DB);
                    break;
                case 6://开发日志
                    mFgs[6] = ArticleListFragment.getInstance(ApiContants.TID_DEVLOG);
                    break;
                case 7://应用推荐
                    mFgs[7] = ArticleListFragment.getInstance(ApiContants.TID_REC_APPLICATION);
                    break;
                case 8://每日一站
                    mFgs[8] = ArticleListFragment.getInstance(ApiContants.TID_DAILY);
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
