package com.walke.huimvp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by View on 2016/10/8. ：单例吐司
 */
public class ToastUtil {
    private static final Object SYNC_LOCK = new Object();
    private static Toast mToast;

    /**
     * 获取toast环境，为toast加锁
     *
     * @param
     * @return
     */
    private static void initToastInstance(Context context) {
        if (ToastUtil.mToast == null) {
            synchronized (SYNC_LOCK) {
                if (ToastUtil.mToast == null) {
                    ToastUtil.mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                }
            }
        }
        return;
    }

    /**
     * 展示吐司
     *
     * @param context 环境
     * @param text    内容
     */
    public static void showToast(Context context, String text) {

        if (text != null) {
            ToastUtil.initToastInstance(context);
            ToastUtil.mToast.setDuration(Toast.LENGTH_SHORT);
            ToastUtil.mToast.setText(text);
            ToastUtil.mToast.show();
        }
        return;
    }
    /**
     * 展示吐司
     *
     * @param context 环境
     * @param text    内容
     */
    public static void toastLong(Context context, String text) {
        if (text != null) {
            ToastUtil.initToastInstance(context);
            ToastUtil.mToast.setDuration(Toast.LENGTH_LONG);
            ToastUtil.mToast.setText(text);
            ToastUtil.mToast.show();
        }
        return;
    }

    /**
     * 展示吐司
     *
     * @param context 环境
     * @param text    内容
     */
    public static void showMiddleToast(Context context, String text) {
        if (text != null) {
            ToastUtil.initToastInstance(context);
            ToastUtil.mToast.setDuration(Toast.LENGTH_SHORT);
            ToastUtil.mToast.setText(text);
            ToastUtil.mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            ToastUtil.mToast.show();

        }
        return;
    }
    /**
     * 展示吐司
     *
     * @param context 环境
     * @param text    内容
     */
    public static void showToastXY(Context context, String text, int x, int y) {
        if (text != null) {
            ToastUtil.initToastInstance(context);
            ToastUtil.mToast.setDuration(Toast.LENGTH_LONG);
            ToastUtil.mToast.setText(text);
            ToastUtil.mToast.setGravity(Gravity.TOP|Gravity.LEFT,x,y);
            ToastUtil.mToast.show();
        }
        return;
    }

    /**
     * 可设置时间的默认展示吐司
     *
     * @param context 环境
     * @param text    内容
     */
    public static void showToastWithTime(Context context, final String text, int time) {
        if (!TimeUtil.isMeetIntervalTime(time)){
            return;
        }
        if (text != null) {
            ToastUtil.initToastInstance(context);
            final Timer timer = new Timer();
            ToastUtil.mToast.setText(text);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ToastUtil.mToast.show();
                }
            }, 0, 2000);// 2000表示点击按钮之后，Toast延迟3000ms后显示
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ToastUtil.mToast.cancel();
                    timer.cancel();
                }
            }, time);// 5000表示Toast显示时间为5秒
        }
        return;
    }

}
