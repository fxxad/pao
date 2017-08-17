package com.fxx.pao.net;

import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.model.BaseMsgModel;
import com.fxx.pao.model.CollectionModel;
import com.fxx.pao.model.MyProfileModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *  账户相关api
 * Created by fxx on 2017/8/14 0014.
 */

public interface UserApi {

    /**
     * 登录
     * @param userid 账号
     * @param pwd 密码
     */
    @FormUrlEncoded
    @POST("/login.php")
    Observable<BaseMsgModel> login(@Field("userid") String userid, @Field("pwd") String pwd);


    @GET("/checklogin.php")
    Observable<BaseMsgModel> checkLogin();

    /**
     * 退出登录
     */
    @GET("/logout.php")
    Observable<BaseMsgModel> logout();

    /**
     * 用户个人信息
     * @param id 用户id
     */
    @GET("/user_profile.php")
    Observable<ResponseBody> userProfile(@Query("id") int id);

    /**
     * 我的个人信息
     */
    @GET("/my_profile.php")
    Observable<MyProfileModel> myProfile();

    /**
     * 我的文章
     * @param p 分页数
     */
    @GET("/my_blog.php")
    Observable<ArticleModel> myArticle(@Query("p") int p);

    /**
     * 我的收藏
     * @param p 分页数
     * @param c 文章:1 ；代码：-19
     */
    @GET("/my_stow.php")
    Observable<CollectionModel> collectionArticle(@Query("p") int p, @Query("c") int c);

    /**
     * 关注
     * @param userId 用户id
     */
    @GET("/social.php?action=follow")
    Observable<BaseMsgModel> followUser(@Query("id") int userId);

    /**
     * 收藏
     * @param id 文章/代码id
     */
    @GET("/stow.php")
    Observable<BaseMsgModel> stow(@Query("id") int id);

    /**
     * 点赞
     * @param id id
     */
    @GET("/upvote.php")
    Observable<BaseMsgModel> praise(@Query("id")  int id);
}
