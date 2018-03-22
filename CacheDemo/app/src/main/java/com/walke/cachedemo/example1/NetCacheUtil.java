package com.walke.cachedemo.example1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by walke.Z on 2018/3/12.
 * 网络缓存使用异步加载AsyncTask，使用其有二种原因:

 1.doInBackground运行在子线程，做网络请求耗时操作，避免主线程堵塞；

 2.onPreExecute和onPostExecute便于更新UI提高用户体验。

 */

public class NetCacheUtil {

    private MemoryCacheUtil memoryCacheUtil;
    private LocalCacheUtil localCacheUtil;
    private RecyclerView rvImageList;

    public NetCacheUtil(MemoryCacheUtil memoryCacheUtil,LocalCacheUtil localCacheUtil){
        this.memoryCacheUtil = memoryCacheUtil;
        this.localCacheUtil = localCacheUtil;
    }


    public void display(ImageView imageView ,String url, RecyclerView lv_image_list){
        this.rvImageList = lv_image_list;
        new MyAsyncTask(imageView).execute(new Object[]{url,imageView});
    }
    class MyAsyncTask extends AsyncTask<Object, Void, Bitmap> {
        private ImageView imageView;
        private int position;
        public MyAsyncTask(ImageView imageView2) {
            position = (Integer) imageView2.getTag();
        }

        // 运行在主线程，做准备操作，在doInBackground之前,可以放置加载条提高用户体验
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 运行在子线程，做耗时操作
        @Override
        protected Bitmap doInBackground(Object... params) {
            // 获取url，下载图片
            String url = (String) params[0];
            // 获取ImageView
            imageView = (ImageView) params[1];
            try {
                // 下载图片
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.connect();// 连接网络
                // 获取响应码
                int resCode = conn.getResponseCode();
                if(resCode==200){// 访问成功
                    // 把服务器返回的输入流转换成Bitmap对象
                    Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                    // 保存到本地和内存
                    memoryCacheUtil.putBitmap(url, bitmap);
                    localCacheUtil.saveBitmap(url, bitmap);
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        // 运行在主线程，更新界面，在doInBackground之后
        @Override
        protected void onPostExecute(Bitmap result) {
            // 判断线程开始时，那个位置是否还在Listview中
            ImageView view = (ImageView) rvImageList.findViewWithTag(position);
            if(view!=null){
                view.setImageBitmap(result);
            }
            super.onPostExecute(result);
        }
    }


}
