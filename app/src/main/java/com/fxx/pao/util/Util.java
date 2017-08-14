package com.fxx.pao.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

}
