package com.fxx.pao.ui.article.articledetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.util.GlideUtil;
import com.fxx.pao.view.HtmlTextView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 文章详情
 */
public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {


    private int mArticleId;

    @BindView(R.id.toobar)
    Toolbar mToolbar;
    @BindView(R.id.htv_content)
    HtmlTextView mHtvContent;
    @BindView(R.id.civ_author_head)
    CircleImageView mCivAuthorHead;
    @BindView(R.id.tv_author_nick)
    TextView mTvAuthorNick;
    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;

    public static void start(Context context,int articleId,String nickName,String faceUrl){
        Intent intent =new Intent(context,ArticleDetailActivity.class);
        intent.putExtra("articleId",articleId);
        intent.putExtra("nick",nickName);
        intent.putExtra("face",faceUrl);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public ArticleDetailPresenter getmPresenter() {
        return new ArticleDetailPresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter !=null)
            mPresenter.setView(this);
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initData() {
        mArticleId = getIntent().getIntExtra("articleId",0);
        initAuthorInfo(getIntent().getStringExtra("nick"),getIntent().getStringExtra("face"));
        mPresenter.loadArticleDetail(mArticleId);
    }


    @Override
    public void onGetArticleDetailSuccess(ArticleDetailModel articleDetail) {
        //TODO
        mHtvContent.setRichText(articleDetail.getContent());
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onGetArticleDetailFail(String msg) {

    }

    /**
     * 初始化作者信息
     * @param nickName 昵称
     * @param faceUrl 头像
     */
    private void initAuthorInfo(String nickName,String faceUrl){
        mTvAuthorNick.setText(nickName);
        GlideUtil.loadHeadImage(faceUrl).into(mCivAuthorHead);
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
