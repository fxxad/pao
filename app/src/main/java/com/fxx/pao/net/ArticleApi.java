package com.fxx.pao.net;


import com.fxx.pao.model.ArticleDetailModel;
import com.fxx.pao.model.ArticleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  文章相关api
 * Created by fxx on 2017/8/11 0011.
 */

public interface ArticleApi {

    /**
     * 文章列表
     * @param p 分页数
     * @return
     */
    @GET("/article_list.php")
    Call<ArticleModel> getArticles(@Query("p") int p,@Query("tid") int tid);

    /**
     * 文章详情
     * @param id 文章id
     * @return
     */
    @GET("/article_detail.php")
    Call<ArticleDetailModel> getArticleDetail(@Query("id") int id);

}
