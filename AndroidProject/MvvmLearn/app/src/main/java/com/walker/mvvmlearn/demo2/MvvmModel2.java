package com.walker.mvvmlearn.demo2;

import java.util.Random;

public class MvvmModel2 {
    // 模拟网络请求数据
    public void getHttpData(String user, MCallback callback) {
        Random random = new Random();
        boolean isSuccess = random.nextBoolean();
        if (isSuccess){
            callback.success("数据获取成功：" + user);
        } else {
            callback.failed("网络异常，获取失败");
        }

    }

}
