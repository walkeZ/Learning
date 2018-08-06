package com.walke.mianshi.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

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

public class SimpleServices extends Service {
    /**
     * 普通启动生命周期：onCreate()->onStartCommand(intentData...)->运行状态(Running)->onDestroy()
     */
    private boolean isStop = false;


    /**
     * 1.如果service没被创建过，调用startService()后会执行onCreate()回调；
     * 2.如果service已处于运行中，调用startService()不会执行onCreate()方法。也就是说，
     * onCreate()只会在第一次创建service时候调用，多次执行startService()不会重复调用onCreate().
     * 此方法适合完成一些初始化工作。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("walke: SimpleServices", "onCreate:------> intentData: ");
    }

    /** Service中的onBind()方法是抽象方法，Service类本身就是抽象类，所以onBind()方法是必须重写的，即使我们用不到。
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("walke: SimpleServices", "onBind:------> ");
        return null;
    }

    /**  如果多次执行了Context的startService()方法，那么Service的onStartCommand()方法也会相应的多次调用。
     *  onStartCommand()方法很重要，我们在该方法中根据传入的Intent参数进行实际的操作，比如会在此处创建一个线程用于下载数据或播放音乐等。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {// 特点：根据startService()的次数会执行多次，
        String name = Thread.currentThread().getName();
        String author = intent.getStringExtra("author");
        Log.i("walke: SimpleServices", "onStartCommand:------> threadName = " + name + "   author = " + author);// main  主(UI)线程
        Log.i("walke: SimpleServices", "onStartCommand:------> intentData:" + intent.toString() + "   flags: " + flags + "   startId: " + startId);
        new Thread(new Runnable() {
            @Override
            public void run() {

                /*  flag位置放错了
                if (!isStop) {
                    for (int i = 0; i < 50; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i("walke: SimpleServices", "onStartCommand:------> currentThread：" + Thread.currentThread().getName() + "   i = " + i);
                    }
                } else {
                    Log.i("walke: SimpleServices", "run:------>  isStop=true");
                }
                */

                for (int i = 0; i < 50; i++) {
                    if (isStop) {
                        Log.i("walke: SimpleServices", "run:------>  isStop=true--->break");
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("walke: SimpleServices", "onStartCommand:------> currentThread：" + Thread.currentThread().getName() + "   i = " + i);
                }

            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 在销毁的时候会执行Service该方法。
     * 如：在Activity中调用stopService(serviceIntent)
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("walke: SimpleServices", "onDestroy:------> ");
        // TODO: 2018/8/1 要在这里做一些，资源回收，
        // 比如当线程没必要继续进行时 可以添加一个flag（isStop），
        // 当线程要继续走(比如终端项目中压缩日志文件)时可以不管，但还是要调用stopService[并不会直接影响线程]
        isStop = true;

    }
}
