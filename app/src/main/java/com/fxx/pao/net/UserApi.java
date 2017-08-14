package com.fxx.pao.net;

import com.fxx.pao.model.BaseMsgModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public interface UserApi {

    /**
     * 登录
     * @param userid
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("/login.php")
    Call<BaseMsgModel> login(@Field("userid") String userid, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("/login.php")
    Call<ResponseBody> loginOld(@Field("userid") String userid, @Field("pwd") String pwd);

    @GET("/checklogin.php")
    Call<BaseMsgModel> checkLogin();

    /**
     * 退出登录
     * @return
     */
    @GET("/logout.php")
    Call<BaseMsgModel> logout();

    @GET("/user_profile.php")
    Call<ResponseBody> userProfile(@Query("id") int id);

    @GET("/my_profile.php")
    Call<ResponseBody> myProfile();
}
