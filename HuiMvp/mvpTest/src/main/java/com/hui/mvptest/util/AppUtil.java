package com.hui.mvptest.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by walke.Z on 2017/8/4.
 */

public class AppUtil {


    /**
     * 打开系统设置界面,并依据包名定位到具体应用界面
     */
    public void openSystemSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }


}
