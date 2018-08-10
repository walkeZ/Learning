package com.walke.demo.main.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walke.Z on 2018/8/10.
 */

public class MainModel {


    public void loadData(String api, final DataCallBack callBack) {

        // 模拟获取数据(网络/数据库)

        final List<String> data = new ArrayList<>();
        data.add("3D相册");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callBack != null)
                    callBack.onSuccess(data);
            }
        }, 2500);


    }
}
