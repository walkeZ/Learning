package com.walke.mianshi.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by walke.Z on 2018/8/1.
 * 关于Android Service真正的完全详解，你需要知道的一切
 * https://blog.csdn.net/javazejian/article/details/52709857
 * android中Service使用详解
 * https://blog.csdn.net/baidu_26994091/article/details/51780052
 * https://www.jianshu.com/p/95ec2a23f300
 * android中的service到底起什么作用 博客
 * https://wenda.so.com/q/1461290654721961
 * <p>
 * Service: 服务，Android 4大组件之一，主要应用场景有两个：
 * ①：后台运行，
 * ②：跨进程访问
 * 1.Service 若用于做耗时操作：通常会在service内部启动一个新的线程。
 * <p>
 * 一、Service的简单使用：
 * (1) 自定义一个Service的子类继承于Service
 * (2) 在Manifest.xml文件中注册;与Activity同级
 * (3) 启动：三种启动模式：https://blog.csdn.net/imxiangzi/article/details/76039978
 * https://www.jianshu.com/p/2fb6eb14fdec
 * a.startService(serviceIntent) ：主要用于启动一个服务执行后台任务，不进行通信。停止服务使用stopService;
 * b.bindService ：该方法启动的服务可以 进行通信，停止服务用unbindService;
 * c.startService+bindService ：可进行通讯，停止服务使用stopService+unbindService
 * (4)停止：stopService(serviceIntent)
 * (5)
 */
// Android Service两种启动方式详解（总结版）
// https://blog.csdn.net/imxiangzi/article/details/76039978
    // https://blog.csdn.net/baidu_26994091/article/details/51780052
public class BindServices extends Service {
    /**
     * 普通启动生命周期：onCreate()->onStartCommand(intentData...)->运行状态(Running)->onDestroy()
     */
    private MyBinder mMyBinder;
    private Random mRandom;

    /**
     * 1.自定义一个MyBinder类
     */
    public class MyBinder extends Binder {
        public BindServices getService(){
            return BindServices.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("walke: BindServices", "onCreate:------> intentData: ");
        /**
         * 2.MyBinder 类+其他初始化
         */
        mMyBinder = new MyBinder();
        mRandom = new Random();
    }

    /** 3. 将MyBinder对象 返回给 activity中的connect的onServiceConnected()
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("walke: BindServices", "onBind:------> threadName = "+Thread.currentThread().getName());
        return mMyBinder;// 返回给 activity中的connect的onServiceConnected()
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {// 会执行
        String name = Thread.currentThread().getName();
        String author = intent.getStringExtra("author");
        Log.i("walke: BindServices", "onStartCommand:------> threadName = " + name + "   author = " + author);// main  主(UI)线程
        Log.i("walke: BindServices", "onStartCommand:------> intentData:" + intent.toString() + "   flags: " + flags + "   startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    public int getRandomNumber(int around) {
        Log.i("walke: BindServices", "getRandomNumber:------> around = "+around);
        return mRandom.nextInt(around);
    }

    /** 当activity调用用unbindService(conn)，这个方法不会触发onDestroy
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("walke: BindServices", "onUnbind:------> ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("walke: BindServices", "onDestroy:------> ");
        super.onDestroy();
        // TODO: 2018/8/1 要在这里做一些，资源回收，
        // 比如当线程没必要继续进行时 可以添加一个flag（isStop），
        // 当线程要继续走(比如终端项目中压缩日志文件)时可以不管，但还是要调用stopService[并不会直接影响线程]

    }
}
