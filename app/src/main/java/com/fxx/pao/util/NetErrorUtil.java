package com.fxx.pao.util;

import android.content.Context;

import com.fxx.pao.PaoApp;
import com.fxx.pao.R;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 *处理网络请求错误信息
 * Created by fxx on 2017/8/17 0017.
 */

public class NetErrorUtil {

    public static String handleThrowable(Throwable throwable){
        //HTTP错误
        if (throwable instanceof HttpException){
            return "网络错误";
        }else if(throwable instanceof IOException){
            return "连接失败";
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException){
            //解析错误
            return "解析出错";
        } else {
            return "未知错误";
        }
    }

    /**
     * 处理网络请求错误信息
     * @param context 上下文
     * @param throwable 具体的错误
     * @return 转化后的错误
     */
    public static String handleThrowable(Context context , Throwable throwable){
        if (throwable instanceof HttpException){
            return context.getString(R.string.error_net_error);
        }else if(throwable instanceof IOException){
            return context.getString(R.string.error_con_fail);
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException){
            return context.getString(R.string.error_parse_error);
        } else {
            return context.getString(R.string.error_unknown);
        }
    }
}
