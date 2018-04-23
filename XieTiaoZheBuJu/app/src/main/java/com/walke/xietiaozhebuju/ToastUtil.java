package com.walke.xietiaozhebuju;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by View on 2016/10/8.
 * <p/>
 * 单例吐司
 */
public class ToastUtil {
    private static final String TAG = "ToastUtil";
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
     * 默认展示吐司
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
     * 显示屏中部展示吐司
     *
     * @param context 环境
     * @param text    内容
     */
    public static void showMidlleToast(Context context, String text) {
        if (text != null) {
            ToastUtil.initToastInstance(context);
            ToastUtil.mToast.setDuration(Toast.LENGTH_SHORT);
            ToastUtil.mToast.setText(text);
            ToastUtil.mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            ToastUtil.mToast.show();

        }
        return;
    }

}
