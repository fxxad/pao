package com.fxx.pao.net;


import com.fxx.pao.model.CodeDetailModel;
import com.fxx.pao.model.CodeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Created by fxx on 2017/8/11 0011.
 */

public interface CodeApi {

    /**
     * 代码列表
     * @param p 页数
     * @return
     */
    @GET("/code_list.php")
    Call<CodeModel> getCodeList(@Query("p") int p);

    /**
     * 代码详情
     * @param id 代码id
     * @return
     */
    @GET("/code_detail.php")
    Call<CodeDetailModel> getCodeDetail(@Query("id") int id);


    @GET("/code_list.php")
    Call<CodeModel> getSearchCode(@Query("p") int p,@Query("cate") int cate,@Query("key") String key);
}
