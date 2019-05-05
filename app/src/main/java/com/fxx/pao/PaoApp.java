package com.fxx.pao;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;

/**
 * application
 * Created by fxx on 2017/8/10 0010.
 */

public class PaoApp extends Application {

    //PaoApp实例
    private static PaoApp instance;
    //修改测试1

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader();
        LeakCanary.install(this);
    }

    /**
     * 获取app实例
     *
     * @return application对象
     */
    public static PaoApp getInstance() {
        return instance;
    }


    /**
     * 初始化imageloader
     */
    private void initImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                instance);
        config.memoryCacheExtraOptions(480, 800);
        config.diskCacheExtraOptions(480, 800, null);
        // 100 MiB
        config.diskCacheSize(100 * 1024 * 1024);
        if (BuildConfig.DEBUG) {
            // Remove for release app
            config.writeDebugLogs();
        }
        ImageLoader.getInstance().init(config.build());
    }
}
