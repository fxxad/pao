package com.fxx.pao.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.fxx.pao.view.HtmlTextView;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.InputStream;


/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class UrlImageGetter implements Html.ImageGetter {
    //    private Map<String, WeakReference<Drawable>> mCache = Collections.synchronizedMap(new WeakHashMap<String, WeakReference<Drawable>>());
    private TextView mTextView;

    public UrlImageGetter(TextView textView) {
        mTextView = textView;
    }

    @Override
    public Drawable getDrawable(final String source) {


        final HtmlTextView.URLDrawable urlDrawable = new HtmlTextView.URLDrawable();
        //TODO 显示GIF有问题，待完成
//        AppClient.getApiService().getImages(source)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResponseBody>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        Drawable drawable = null;
//                        try {
//                            drawable = new GifDrawable(responseBody.bytes());
//                        } catch (IOException e) {
//                            e.printStackTrace();
////                            drawable = new BitmapDrawable();
//                        }
//                        int tvWidth = mTextView.getMeasuredWidth();
//                        InputStream inputStream = responseBody.byteStream();
//                        int[] imageSize = getImageWidthHeight(inputStream);
//                        int width = tvWidth;
//                        int height = tvWidth * imageSize[1] / imageSize[0];
//
//                        drawable.setBounds(0, 0, width, height);
//                        urlDrawable.setBounds(0, 0, width, height);
//                        urlDrawable.setDrawable(drawable);
//                        mTextView.setText(mTextView.getText());
//                    }
//                });

        //暂不支持GIF，之后会添加
        ImageLoaderUtils.loadImage(source, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                int tvWidth = mTextView.getMeasuredWidth();
                int width = tvWidth;
                int height = tvWidth * loadedImage.getHeight() / loadedImage.getWidth();
                BitmapDrawable drawable = new BitmapDrawable(loadedImage);
                drawable.setBounds(0, 0, width, height);
                urlDrawable.setBounds(0, 0, width, height);
                urlDrawable.setDrawable(drawable);
                mTextView.setText(mTextView.getText());
            }
        });

        return urlDrawable;
    }

    public int[] getImageWidthHeight(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options); // 此时返回的bitmap为null
        return new int[]{options.outWidth, options.outHeight};
    }

}
