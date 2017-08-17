package com.fxx.pao.util;

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
        if (throwable instanceof HttpException){             //HTTP错误
            return "网络错误";
        }else if(throwable instanceof IOException){
            return "连接失败";
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException){//解析错误
            return "解析出错";
        } else {
            return "未知错误";
        }
    }
}
