package com.walke.unittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/** 测试model
 *  单元测试
 *  参考链接：https://www.jianshu.com/p/62dfbd6ea077
 *          https://www.jianshu.com/p/67c2f4ae9a6e?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 *
 *  AS设置：https://blog.csdn.net/wutongyu0123wutongyu/article/details/51898642
 *  AS 2.3.0+ 已经支持了
 *
 *  压力测试：monkey(乱点、乱操作)
 *      adb shell monkey -p 你想测试程序的包名 -v 500
 *      adb shell monkey -p com.guaguayi.app.android -v 500
 *      使用流程：进入cmd的控制台
 *               -> adb shell【进入Android系统】
 *               -> adb devices【查看连接的设备(模拟器)】
 *               -> adb shell monkey -p 你想测试程序的包名 -v 500
 *
 *   Android 程序员必须掌握的三种自动化测试方法
 * https://blog.csdn.net/whatnamecaniuse/article/details/52451782
 */
public class UnitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
    }
}

