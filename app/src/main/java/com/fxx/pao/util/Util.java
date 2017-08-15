package com.fxx.pao.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.fxx.pao.PaoApp;

/**
 *
 * Created by fxx on 2017/8/14 0014.
 */

public class Util {

    public static void startBrower(Context context,String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /***
     * 关闭软键盘
     * @param editText et
     */
    public static void hideInput(EditText editText){
        InputMethodManager imm = (InputMethodManager) PaoApp.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0) ;
    }

}
