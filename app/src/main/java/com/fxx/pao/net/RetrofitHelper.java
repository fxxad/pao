package com.fxx.pao.net;

import com.fxx.pao.PaoApp;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by fxx on 2017/8/10 0010.
 */

public class RetrofitHelper {

    private static OkHttpClient okhttpClient;

    static {
        initOkhttpClient();
    }

    private static void initOkhttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        Cache cache = new Cache(PaoApp.getInstance().getCacheDir(),1024*1024*10);

        okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();
    }

    /**
     * 根据传入的baseurl和 api类 创建 api实例
     * @param baseUrl baseurl
     * @param clazz api类
     * @param <T> api类型
     * @return api实例
     */
    private static <T> T createApi(String baseUrl,Class<T> clazz){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 创建ArticleApi实例并返回
     * @return ArticleApi实例
     */
    public static ArticleApi createArticleApi(){
        return createApi(ApiContants.BASE_URL,ArticleApi.class);
    }

    /**
     * 创建CodeApi实例并返回
     * @return CodeApi实例
     */
    public static CodeApi createCodeApi(){
        return createApi(ApiContants.BASE_URL,CodeApi.class);
    }

    /**
     * 创建UserApi实例并返回
     * @return UserAPi实例
     */
    public static UserApi createUserApi(){
        return createApi(ApiContants.BASE_URL,UserApi.class);
    }
}
