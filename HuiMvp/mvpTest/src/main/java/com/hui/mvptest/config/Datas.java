package com.hui.mvptest.config;

/**
 * Created by walke.Z on 2017/8/8.
 * * 据说在JVM里面有一个常量池，如果是这个值存在于这个常量池里，
 * 那么jvm会直接拿常量池里的对象进行替换。所以你值小的时候得到的结果是相等的
 * <p>
 * JVM虚拟机的的优化，范围 -128 到 127之间有缓存
 * 静态(属性、方法)会在第一时间分配内存地址，会在实例前
 * Static 块仅在该类被加载时执行一次
 * 如果某个类里 直接在全局中有
 * {
 * 一些代码
 * }
 * 则表示这是个静态代码块 隐藏了static(这里可隐藏可显示)
 */

public class Datas {

    public static String[] home = {
            "TestActivity",
            "ScrollActivity",
            "ScrollActivity2",
            "MVPDemo",
            "MVPSystemLocation",
            "SystemLocation",
            "VerticalPagerActivity",
            "RefreshWebViewActivity",
            "CustomBannerActivity",
            "BannerActivity",
            "MVPBanner",
            "MVPDemo",
            "MVPDemo",

    };

    public static String[] view = {
            "SystemLocation",
            "MVPDemo",
    };
    public static String[] news = {
            "SystemLocation",
            "MVPDemo",
    };
    public static String[] mine = {
            "SystemLocation",
            "MVPDemo",
    };
}
