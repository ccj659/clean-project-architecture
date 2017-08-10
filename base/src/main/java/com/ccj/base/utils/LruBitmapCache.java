package com.ccj.base.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 图片缓存池
 * Created by ccj on 2016/1/6.
 */
public class LruBitmapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> mCache;

    public LruBitmapCache() {
        int maxSize = 10 * 1024 * 1024;

        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }

}
