package com.walker.mvvmlearn.demo_login2.demo.data.repository;


import androidx.lifecycle.MutableLiveData;

import com.walker.mvvmlearn.demo_login2.demo.data.entity.Login2Response;

// 网络请求
public interface INetRequest {

    /**
     * 网络，请求服务，登录 标准制定
     *
     * @param name             用户名
     * @param psw              密码
     * @param loginSuccessData 登录成功的数据接收
     * @param loginFailData    登录失败数据的接收
     */
    void login(String name, String psw, MutableLiveData<Login2Response> loginSuccessData, MutableLiveData<String> loginFailData);

    // TODO: 2024/5/20 待扩展

}
