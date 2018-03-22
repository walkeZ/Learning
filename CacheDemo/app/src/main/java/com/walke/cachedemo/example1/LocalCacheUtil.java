package com.walke.cachedemo.example1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.walke.cachedemo.MD5Encoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by walke.Z on 2018/3/12.
 *
 * 本地缓存:根据url，获取本地文件，把url进行md5加密，作为文件名,保证文件的正确性.
 *
 */

public class LocalCacheUtil {

    private String CACHE_URl;
    private MemoryCacheUtil memoryCacheUtil;

    public LocalCacheUtil(MemoryCacheUtil memoryCacheUtil){
        // 初始化本地存储的路径
        CACHE_URl = Environment.getExternalStorageDirectory().getAbsoluteFile()+ "/test";
        this.memoryCacheUtil = memoryCacheUtil;
    }

    // 从本地sdcard取图片
    public Bitmap getBitmap(String url){
        // 根据url，获取本地文件，把url进行md5加密，作为文件名
        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(CACHE_URl, fileName);
            if(file.exists()){// 判断当前文件是否存在
                // 把当前文件转换成Bitmap对象
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                // 需往内存中存一份
                memoryCacheUtil.putBitmap(url, bitmap);
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 往本地存图片的方法
    public void saveBitmap(String url,Bitmap bitmap){
        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(CACHE_URl, fileName);
            // 判断是否需要创建父目录
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            // 把Bitmap对象保存到文件中 质量越高压缩速度越慢
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);//第一个参数可以设置图片格式,第二个图片压缩质量,第三个为图片输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
