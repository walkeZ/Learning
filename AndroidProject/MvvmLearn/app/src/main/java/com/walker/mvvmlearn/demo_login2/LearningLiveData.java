package com.walker.mvvmlearn.demo_login2;

import androidx.lifecycle.MutableLiveData;

// JetPackt的LiveData学习
public class LearningLiveData {
    // MutableLiveData 可变状态生命周期数据。类型String。数据名称info
    // MutableLiveData是androidx.lifecycle包下的。导包implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.1'
    private MutableLiveData<String> info;

    //对外暴露liveData
    public MutableLiveData<String> getInfo() {
        if (info == null) info =new MutableLiveData<>();
        return info;
    }

    // 单例模式，饿汉式，一开始就new
    private static LearningLiveData liveData = new LearningLiveData();
    public static LearningLiveData getInstance() {
        return liveData;
    }
}
