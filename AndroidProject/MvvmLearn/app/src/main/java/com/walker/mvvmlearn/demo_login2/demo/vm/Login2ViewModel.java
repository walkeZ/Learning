package com.walker.mvvmlearn.demo_login2.demo.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Login2ViewModel extends ViewModel {

    // 所有的状态数据，全部保存到此处

    public MutableLiveData<String> loginState = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userPwd = new MutableLiveData<>();

    public void clearUserPsw() {
        userPwd.setValue("");
    }

}
