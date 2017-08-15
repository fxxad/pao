package com.fxx.pao.net;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.model.MyProfileModel;

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
    Call<MyProfileModel> myProfile();

    @GET("/my_blog.php")
    Call<ArticleModel> myArticle(@Query("p") int p);

    /**
     * 我的收藏
     * @param p 分页数
     * @param c 文章:1 ；代码：-19
     * @return
     */
    @GET("/my_stow.php")
    Call<CollectionModel> collectionArticle(@Query("p") int p, @Query("c") int c);

    /**
     * 关注
     * @param userId 用户id
     * @return
     */
    @GET("/social.php?action=follow")
    Call<BaseMsgModel> followUser(@Query("id") int userId);
}
