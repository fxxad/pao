package com.fxx.pao.net;


import com.fxx.pao.model.CodeDetailModel;
import com.fxx.pao.model.CodeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  代码相关api
 * Created by fxx on 2017/8/11 0011.
 */

public interface CodeApi {

    /**
     * 代码列表
     * @param p 页数
     */
    @GET("/code_list.php")
    Observable<CodeModel> getCodeList(@Query("p") int p);

    /**
     * 代码详情
     * @param id 代码id
     */
    @GET("/code_detail.php")
    Observable<CodeDetailModel> getCodeDetail(@Query("id") int id);

    /**
     * 代码搜索
     * @param p 分页数
     * @param cate 代码分类
     * @param key 关键词
     */
    @GET("/code_list.php")
    Observable<CodeModel> getSearchCode(@Query("p") int p,@Query("cate") int cate,@Query("key") String key);
}
