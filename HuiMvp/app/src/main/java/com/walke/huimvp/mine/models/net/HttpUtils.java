package com.walke.huimvp.mine.models.net;

import android.os.Handler;
import android.util.Log;

import com.walke.huimvp.mine.models.bean.UserInfo;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * Model层：模型层；个人理解：应该包含当不限于数据模型(JavaBean)，还可以包括网络模块和数据库模块等
 *          辅助presenter完成具体业务逻辑。不与View直接关联，即有效隔离、解耦。
 * 网络模块。
 *
 */

public class HttpUtils {

    // TODO: 2018/8/6 网咯框架封装

    // 测试
    public static void login(final String name, final String psw, final LoginCallBack callBack){


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("walke".equals(name)&&"123".equals(psw)){
                    if (callBack!=null)
                        callBack.onSuccess(new UserInfo(name,psw,26));
                }else {
                    Log.i("walke: HttpUtils", "run:------> 登录失败");
                    if(callBack!=null)
                        callBack.onFail(new Exception("用户名或者密码错误"));
                }

            }
        },2500);
    }

}
