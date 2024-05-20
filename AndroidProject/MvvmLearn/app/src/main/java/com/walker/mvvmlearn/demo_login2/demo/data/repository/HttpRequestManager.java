package com.walker.mvvmlearn.demo_login2.demo.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.walker.mvvmlearn.demo_login2.demo.data.entity.Login2Response;

import java.lang.reflect.Array;
import java.util.Arrays;

// 仓库层，包含 数据库数据的请求，本地数据的请求，网络数据的请求
public class HttpRequestManager implements IDatabaseRequest, ILocalRequest, INetRequest {

    public static String[] NAME_LIST = {"Hui","Jone","Jack","dfg",};

    // 单例 静态内部类
    private static class Instance {
        public static HttpRequestManager instance = new HttpRequestManager();
    }

    public static HttpRequestManager getInstance() {
        return Instance.instance;
    }

    /**
     * 网络，请求服务，登录 标准制定
     *
     * @param name             用户名
     * @param psw              密码
     * @param loginSuccessData 登录成功的数据接收
     * @param loginFailData    登录失败数据的接收
     */
    @Override
    public void login(String name, String psw, MutableLiveData<Login2Response> loginSuccessData, MutableLiveData<String> loginFailData) {
        new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 模拟登录
                   if (Arrays.toString(NAME_LIST).contains(name)) {
                       // 名单有就是登录成功
                       Login2Response login2Response = new Login2Response();
                       login2Response.setCode(200);

                       login2Response.setMsg("Login Success");
                       login2Response.setUserName(name);
                       // postValue 推送到主线程. MutableLiveData收到后会通过DataBinding的机制更新到UI
                       // 体现：数据驱动UI的思想，数据变了UI就变，不要要MVP那要自己调用代码修改UI
                       loginSuccessData.postValue(login2Response);
                   } else {
                        loginFailData.postValue(name + " 用户，您登录失败了，请检查...");
                   }
                }
            }
        }.start();
    }

    // TODO: 2024/5/20

}
