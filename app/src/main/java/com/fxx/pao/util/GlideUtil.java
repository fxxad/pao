package com.fxx.pao.util;

import android.content.Context;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.fxx.pao.PaoApp;
import com.fxx.pao.R;

/**
 * Glide帮助类
 */
public class GlideUtil {

    @Deprecated
    public static <T> DrawableTypeRequest<T> load(Context context, T url) {
        return load(url);
    }

    /**
     * 默认调用方法
     *
     * @param url String, byte[], File, Integer, Uri
     * @param <T>
     * @return
     */
    public static <T> DrawableTypeRequest<T> load(T url) {
        return (DrawableTypeRequest<T>) Glide.with(PaoApp.getInstance()).load(url)
                .error(R.drawable.nopic)
                .dontAnimate();
    }

    //---------以下为扩展方法------------

//    public static <T> DrawableTypeRequest<T> loadSplash(Context context, T model)
//    {
//        return (DrawableTypeRequest<T>) Glide.with(PaoApp.getInstance()).load(model)
//                .error(R.drawable.img_welcome)
//                .dontAnimate();
//    }

    /**
     * 加载用户头像方法
     *
     * @param url String, byte[], File, Integer, Uri
     * @param <T>
     * @return
     */
    public static <T> DrawableTypeRequest<T> loadHeadImage(T url) {
        return (DrawableTypeRequest<T>) load(url)
                .placeholder(R.drawable.ic_default_head)
                .error(R.drawable.ic_default_head)
                .dontAnimate();
    }

    /**
     * 加载通用图片
     */
    public static <T> DrawableTypeRequest<T> loadDefaultImage(T url) {
        return (DrawableTypeRequest<T>) load(url)
                .placeholder(R.drawable.ic_pic_loading)
                .error(R.drawable.ic_pic_loadfail)
                .dontAnimate();
    }
}
