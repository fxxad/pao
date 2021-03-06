package com.fxx.pao.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.event.LoginSuccessEvent;
import com.fxx.pao.model.BaseMsgModel;

import org.greenrobot.eventbus.EventBus;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by fxx on 2017/8/11
 * @author fxx
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tiet_count)
    TextInputEditText mEtCount;
    @BindView(R.id.tiet_pwd)
    TextInputEditText mEtPwd;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static void start(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public LoginPresenter getmPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void presenterSetView() {
        if(mPresenter != null) {
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

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.bt_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_login:
                mPresenter.login(mEtCount.getText().toString().trim(),mEtPwd.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(BaseMsgModel msgModel) {
        if(msgModel.getSucess() == 1){
            ////登录成功 通知minefragment刷新个人信息
            EventBus.getDefault().post(new LoginSuccessEvent());
            Toast.makeText(this,R.string.tip_login_success,Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,msgModel.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
