package com.fxx.pao.ui.article.articledetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.util.GlideUtil;
import com.fxx.pao.view.HtmlTextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 文章详情
 * @author fxx
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
    @BindView(R.id.tv_comment_num)
    TextView mTvCommentNum;
    @BindView(R.id.tv_collection_num)
    TextView mTvCollectionNum;
    @BindView(R.id.tv_praise_num)
    TextView mTvPraiseNum;

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
        if(mPresenter !=null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initData() {
        mArticleId = getIntent().getIntExtra("articleId",0);
        initAuthorInfo(getIntent().getStringExtra("nick"),getIntent().getStringExtra("face"));
        mPresenter.loadArticleDetail(mArticleId);
    }


    @Override
    public void onGetArticleDetailSuccess(ArticleDetailModel articleDetail) {
        mHtvContent.setRichText(articleDetail.getContent());
        mPbLoading.setVisibility(View.GONE);
        if(articleDetail.getComments()>0){
            mTvCommentNum.setText(String.valueOf(articleDetail.getComments()));
            mTvCommentNum.setVisibility(View.VISIBLE);
        }
        if(articleDetail.getStow()>0){
            mTvCollectionNum.setText(String.valueOf(articleDetail.getStow()));
            mTvCollectionNum.setVisibility(View.VISIBLE);
        }
        if(articleDetail.getUpvote()>0){
            mTvPraiseNum.setText(String.valueOf(articleDetail.getUpvote()));
            mTvPraiseNum.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onGetArticleDetailFail(String msg) {

    }

    @Override
    public void onFollowSuccess(BaseMsgModel baseMsgModel) {
        if(baseMsgModel.getSucess()==1){
            Toast.makeText(this,baseMsgModel.getMessage(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,baseMsgModel.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFollowFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collectSuccess(BaseMsgModel baseMsgModel) {
        Toast.makeText(this,baseMsgModel.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collectFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void praiseSuccess(BaseMsgModel baseMsgModel) {
        Toast.makeText(this,baseMsgModel.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void praiseFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @OnClick({R.id.tv_follow,R.id.iv_comment,R.id.iv_collection,R.id.iv_praise})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_follow:
                if(mPresenter.getArticleDetailModel()!=null){
                    mPresenter.followUser(mPresenter.getArticleDetailModel().getUser().getId());
                }else{
                    Toast.makeText(this, R.string.try_later,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_comment:
                //TODO
                Toast.makeText(this,R.string.tip_todo,Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_collection:
                mPresenter.collect(mArticleId);
                break;
            case R.id.iv_praise:
                mPresenter.praise(mArticleId);
                break;
            default:
                break;
        }
    }
}
