package com.walke.xietiaozhebuju;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/13 0013.
 * 引用OkHttp依赖
 */
public class MineOkHttpUtils {
   private static ExecutorService mService;
   private static OkHttpClient mOkHttpClient;
   private static Handler handler;

   static {
      mService= Executors.newFixedThreadPool(5);
      mOkHttpClient=new OkHttpClient();
      handler=new Handler();
   }

   /*************接口回调****************/
   /*
   * 1.定义接口
   * */
   public interface loadDataListener{
      void loadedData(String data);
   }
//   /*
//   * 2.声明接口
//   * */
//   public static loadDataListener listener;
//
//   /*
//   * 3.提供一个方法供外界设置(创建)接口
//   * */
//   public static void setLoadDataListener(loadDataListener listener){
//      this.listener=listener;
//   }

   public static void loadData(final String url, final loadDataListener listener ){
      //在线程池中分配线程去下载（会new线程）
      mService.execute(new Runnable() {
         @Override
         public void run() {
            Request rq=new Request.Builder().url(url).tag(url).build();
//            mOkHttpClient.newCall(rq ).enqueue();enqueue---异步(自设定有线程)；；
//           execute同步(需new线程,然后在new出来的线程中执行下载操作)，
            try {
               Response response = mOkHttpClient.newCall(rq).execute();
               if (response.isSuccessful()){
                  //获取下载到的字符串
//                  final String data = response.body().toString();
                  final String data = response.body().string();
//                  Log.d("MyOkHttpUtilsshowBug", "run: >>>"+Thread.currentThread().getName()+"data>>>"+data);
                  //pool-7-thread-2；线程池线程
                  handler.post(new Runnable() {
                     @Override
                     public void run() {
//                        Log.d("MyOkHttpUtilsshowBug", "run2: --->"+Thread.currentThread().getName()+"data>>>"+data);
                        listener.loadedData(data);//main线程
                     }
                  });

               }else {
                  handler.post(new Runnable() {
                     @Override
                     public void run() {
                        listener.loadedData("fail");//main线程
//                        Log.d("MyOkHttpUtilsshowBug", "run2: --->"+Thread.currentThread().getName()+"data>>>--fail");
                     }
                  });

               }
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      });

   }
}
