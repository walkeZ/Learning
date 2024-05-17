package com.walker.mvvmlearn.demo_login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.walker.mvvmlearn.R;

/**
 * 源于：https://www.bilibili.com/video/BV13Y4y1H7FG/?p=4&vd_source=412650ca810562b4df78e0e3fa9484f6
 * <p> 一、历史
 * 架构发展简要：
 * 2011年 ，MVC 天然的架构模式，原因是Android项目天然就是图与逻辑分开的结构。
 * Main文件中分出2大类（1、java即逻辑代码包；2、res资源包(图片、xml(字串、color、view、layout)等)）
 * > Activity（controller） + xml（view） + entity（Model）
 * > Activity（窗口，主要承接逻辑/交互含网络请求 等即controller）Activity臃肿，还包括修改UI及UI交互响应，网络请求等(VIEW,controller，data)，做到东西太多，违背单一职责原则。MVC模式本身没有问题，主要是在Android上不纯粹，不契合,Activity失控了。
 * > Xml（布局视图，Layout\view等 即：view）
 * > entity（数据实体对象，网络请求返回解析，在Activity中与view关联，如User有头像、名称登录后从网络返回然后显示到界面 即：Model）
 *
 * 2015~18 MVP
 * > V：Activity 是View层了。仅负责更新UI，响应UI交互（用户点击xml->activity->presenter->逻辑完了->activity->xml）
 * > P：P层承接逻辑层，承接了网络请求，交互响应内容等逻辑业务，最后出现接口越来越多，即接口地域的问题。EventBus减轻了一定的接口两
 * > M: 还是数据实体变化不大，还是数据实体层(对象)
 *
 * 2019 ~ 今天 MVVM 首先，四个字母3个绝角色
 * > V:XMl、Activity 还是View层，引入了datBinding库，减少了大量的设置UI代码，由datBinding代工实现了。【弊端项目大了，dataBinding会生长大量环境包代工代码会影响一定的性能】后面Google对MVVM升级推出更优一点的架构框架（MVVM + JetPack全家桶）
 * > VM: 代替了P层，引入了一个全新库datBinding，可以直接联通V(xml)与M（数据），ViewModel是官方类(工具)
 * > M:还是数据实体变化不大，还是数据实体层(对象)
 * >
 *
 * 架构选型：
 * >> MVC  1.灵活小项目（结构简单明了）Activity的任务不会过重，能清晰实现；2 个人开发，原因该模式：解耦低，容易有交叉冲突。
 * >> MVP  1.大型项目 意味着业务多->接口多(接口地狱)->需要人多。2.业务重-P层重的
 * >> MVVM  1.UI经常性改变(如直播、社区)视图画面更新频繁的(如点赞、收藏、评论等待多)的
 */
public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }
}