package com.fxx.pao.ui.code.codedetail;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.model.CodeDetailModel;
import com.fxx.pao.ui.comment.CommentActivity;
import com.fxx.pao.util.GlideUtil;
import com.fxx.pao.util.Util;
import com.fxx.pao.view.HtmlTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代码详情页
 */
public class CodeDetailActivity extends BaseActivity<CodeDetailPresenter> implements CodeDetailContract.View{

    @BindView(R.id.toolbar_code_detail)
    Toolbar mToolbar;
    @BindView(R.id.htv_code)
    HtmlTextView mHtv;
    @BindView(R.id.iv_thumb)
    ImageView mIvThumb;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_size)
    TextView mTvSize;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.iv_collection)
    ImageView mIvCollect;
    @BindView(R.id.iv_comment)
    ImageView mIvComment;
    @BindView(R.id.iv_go_github)
    ImageView mIvGoGitHub;
    @BindView(R.id.tv_like_num)
    TextView mTvLikeNum;
    @BindView(R.id.tv_comment_num)
    TextView mTvCommentNum;
    @BindView(R.id.tv_click_num)
    TextView mTvClickNum;

    private int mCodeId;
    private CodeDetailModel codeDetailModel;

    public static void start(Context context,int codeId,String title){
        Intent intent = new Intent(context,CodeDetailActivity.class);
        intent.putExtra("id",codeId);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_code_detail;
    }


    @Override
    public CodeDetailPresenter getmPresenter() {
        return new CodeDetailPresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null)
            mPresenter.setView(this);
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initData() {
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        mCodeId = getIntent().getIntExtra("id",0);
        mPresenter.getCodeDetail(mCodeId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getCodeDetailSucces(CodeDetailModel codeDetailModel) {
        this.codeDetailModel = codeDetailModel;
        mHtv.setRichText(codeDetailModel.getReadme());
        initCodeDetail(codeDetailModel);
    }

    @Override
    public void getCodeDetailFailed(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化代码详情
     * @param codeDetailModel 详情
     */
    private void initCodeDetail(CodeDetailModel codeDetailModel){
        GlideUtil.loadDefaultImage(codeDetailModel.getThumbnail()).into(mIvThumb);
        mTvName.setText(codeDetailModel.getTitle());
        mTvSize.setText(codeDetailModel.getSize());
        mTvCategory.setText(codeDetailModel.getCodecategory().getCatename());
        mTvLikeNum.setText(""+codeDetailModel.getStow());
        mTvCommentNum.setText(""+codeDetailModel.getComments());
        mTvClickNum.setText(""+codeDetailModel.getClick());
    }

    @OnClick({R.id.iv_collection,R.id.iv_comment,R.id.iv_go_github})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_collection:

                break;
            case R.id.iv_comment:
                CommentActivity.start(this,mCodeId);
                break;
            case R.id.iv_go_github:
                Util.startBrower(this,codeDetailModel.getUrl());
                break;
            default:
                break;
        }
    }
}
