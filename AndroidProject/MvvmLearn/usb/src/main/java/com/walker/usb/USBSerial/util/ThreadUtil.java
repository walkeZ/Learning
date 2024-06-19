package com.walker.usb.USBSerial.util;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.Executors;

/**
 * @author walker
 * @date 2022/8/12
 * @description 线程工具
 */
public class ThreadUtil {
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static boolean isMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void runOnMain(Runnable action) {
        if (isMain()) {
            action.run();
        } else {
            mHandler.post(action);
        }
    }

    public static void runOnMainDelay(Runnable action, int delay) {
        mHandler.postDelayed(action, delay);
    }

    public static void runOnNew(Runnable action) {
        Executors.newSingleThreadExecutor().submit(action);
    }

    public static void runMsgOnMainDelay(Runnable run, String handlerToken, int delay) {
        HandlerCompat.postDelayed(mHandler, run, handlerToken, delay);
    }

    public static void removeMsgWithToken(String handlerToken) {
        mHandler.removeCallbacksAndMessages(handlerToken);
    }
}
