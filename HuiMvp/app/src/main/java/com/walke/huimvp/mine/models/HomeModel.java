package com.walke.huimvp.mine.models;

import com.walke.huimvp.mine.models.net.HomeIModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walke.Z on 2018/8/6.
 */

public class HomeModel {

    /** 协议api，区分具体业务
     * @param api  如：1000 是注册，1001是登录，1002是首页等
     */
    public void loadData(String api, HomeIModel iModel){
        // 模拟获取网络数据
        // TODO: 2018/8/6 网络模块请求首页数据
        List<String> titles=new ArrayList<>();
        titles.add("mine-UserLoginActivity1");
        titles.add("mine-UserLoginActivity2");
        titles.add("google_demo-UserInfoActivity1");
        titles.add("google_demo-UserInfoActivity2");
        if (iModel!=null)
            iModel.onSuccess(titles);



    }

}
