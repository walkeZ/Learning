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
 *      adb shell monkey -p com.guaguayi.terminal -v 500
 *      adb shell monkey -p com.guaguayi.terminal --pct-syskeys 0 -v 500
 *      使用流程：进入cmd的控制台
 *               -> adb shell【进入Android系统】
 *               -> adb devices【查看连接的设备(模拟器)】
 *               -> adb shell monkey -p 你想测试程序的包名 -v 500
 *     异常：SYS_KEYS has no physical keys but with factor 2.0%.   --：解决直接搜索
 *     #  https://blog.csdn.net/u010983881/article/details/51954076
 *     方案： monkey命令中加入--pct-syskeys 0
 *     例如 adb shell monkey -p com.android.settings --pct-syskeys 0 -v 500
 *
 *     https://blog.csdn.net/u010296640/article/details/72773167
 *     -p后面是自己程序的包名；-v后面的测试的次数；>后面是测试日志在pc端的路径;–throttle：
 *     延迟时间，如–throttle 300 2000表示每个事件执行时间为300ms,执行2000个事件：
 *     adb shell monkey -p com.fs.gz -v -v –throttle 300 2000 > D:\MonkeyTestLog.txt
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

