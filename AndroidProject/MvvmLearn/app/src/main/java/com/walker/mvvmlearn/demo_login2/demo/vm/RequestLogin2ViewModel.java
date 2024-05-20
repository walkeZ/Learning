package com.walker.mvvmlearn.demo_login2.demo.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walker.mvvmlearn.demo_login2.demo.data.entity.Login2Response;
import com.walker.mvvmlearn.demo_login2.demo.data.repository.HttpRequestManager;

// TODO: 2024/5/20 请求登录的ViewModel
/**
 * https://www.bilibili.com/video/BV13Y4y1H7FG?p=4&vd_source=412650ca810562b4df78e0e3fa9484f6
 * 26'03'
 */
public class RequestLogin2ViewModel extends ViewModel {

    // 登录成功的状态 LiveData
    public MutableLiveData<Login2Response> loginSuccessData = new MutableLiveData<Login2Response>();

    // 登录失败的状态 LiveData
    public MutableLiveData<String> loginFileData = new MutableLiveData<>();

    public void requestLogin(String username,String psw) {
        // TODO: 2024/5/20
        // 可以做很多的事，校验、转换等
        // 检查、校验都没问题后，直接调用仓库

        HttpRequestManager.getInstance().login(username, psw, loginSuccessData, loginFileData);
    }

}
