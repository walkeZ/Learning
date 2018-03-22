package com.walke.cachedemo.example1;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by walke.Z on 2018/3/12.
 * 内存缓存，android为我们提供LruCache=其中维护着一个LinkedHashMap。
 * LruCache可以用来存储各种类型的数据,我们设置它的大小，一般是系统最大存储空间的1/8
 */

public class MemoryCacheUtil {
    private LruCache<String, Bitmap> lruCache;

    public MemoryCacheUtil(){
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/6);

        // 一般获取当前应用的最大内存的1/8作为LruCache的容量
        lruCache = new LruCache<String, Bitmap>(maxSize){
            // 设置当前添加的图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    // 从内存缓存取图片
    public Bitmap getBitmap(String url){
        return lruCache.get(url);
    }

    // 在内存缓存存图片
    public void putBitmap(String url,Bitmap bitmap){
        lruCache.put(url, bitmap);
    }

}
