package com.fxx.pao.net;


import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.ArticleModel;
import com.fxx.pao.model.CommentModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  文章相关api
 * Created by fxx on 2017/8/11 0011.
 */

public interface ArticleApi {

    /**
     * 轮播图数据
     */
    @GET("/slider.php")
    Observable<ArticleModel> slideArticles();

    /**
     * 文章列表
     * @param p 分页数
     */
    @GET("/article_list.php")
    Observable<ArticleModel> getArticles(@Query("p") int p,@Query("tid") int tid);

    /**
     * 文章详情
     * @param id 文章id
     */
    @GET("/article_detail.php")
    Observable<ArticleDetailModel> getArticleDetail(@Query("id") int id);

    /**
     * 评论列表
     * @param id id
     * @param p 分页数
     */
    @GET("/list_comment.php")
    Observable<CommentModel> getComments(@Query("id") int id, @Query("p") int p);


    /**
     * 文章搜索
     * @param key 关键词
     * @param p 分页数
     */
    @GET("/article_list.php")
    Observable<ArticleModel> getSearchArticles(@Query("key") String key,@Query("p") int p);

}
