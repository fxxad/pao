package com.fxx.pao.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
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
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tiet_count)
    TextInputEditText mEtCount;
    @BindView(R.id.tiet_pwd)
    TextInputEditText mEtPwd;

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
        if(mPresenter != null)
        mPresenter.setView(this);
    }

    @Override
    public void initView() {

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
        if(msgModel.getSucess() == 1){//登录成功
            //通知minefragment刷新个人信息
            EventBus.getDefault().post(new LoginSuccessEvent());
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,msgModel.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
