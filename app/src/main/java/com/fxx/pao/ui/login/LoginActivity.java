package com.fxx.pao.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fxx.pao.R;
import com.fxx.pao.base.BaseActivity;
import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.net.RetrofitHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @BindView(R.id.bt_check_login)
    Button mBtCheckLogin;

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

    @OnClick({R.id.bt_login,R.id.bt_check_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_login:
                mPresenter.login(mEtCount.getText().toString().trim(),mEtPwd.getText().toString().trim());
                break;
            case R.id.bt_check_login:

//                RetrofitHelper.createUserApi().checkLogin().enqueue(new Callback<BaseMsgModel>() {
//                    @Override
//                    public void onResponse(Call<BaseMsgModel> call, Response<BaseMsgModel> response) {
//                        String msg = response.body().getMessage();
//                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
//                        Log.d("xxf",msg);
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseMsgModel> call, Throwable t) {
//
//                    }
//                });

                RetrofitHelper.createUserApi().myProfile().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String msg = null;
                        try {
                            msg = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                        Log.d("xxf",msg);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                break;
        }
    }

    @Override
    public void loginSuccess(BaseMsgModel msgModel) {
        if(msgModel.getSucess() == 1){//登录成功
            //TODO 保存用户信息
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
//            finish();
        }
    }

    @Override
    public void loginFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
