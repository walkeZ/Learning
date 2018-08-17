package com.walke.huimvp.google_demo.contract;

import com.walke.huimvp.google_demo.presenters.BasePresenter;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 参考： MVP google官方demo比较分析： https://www.jianshu.com/p/14283d8d3a60
 *
 * 以往的MVC：  异 -- MVC中是允许Model和View进行交互的
 * 1.M-Model：数据模型层，即javaBean数据模型层：
 *      PS: 有一些说法(博客)，model层也放置数据相关逻辑，比如：https://www.jianshu.com/p/9d40b298eca9
 *      个人想法(20180806)：model应该包括并不限于JavaBean(数据模型)，可以包扣网络模块封装(即NetModel)
 * 2.V-view：视图层，即XML布局和控件
 * 3.C-control:业务逻辑层，Activity、Fragment等
 *
 * 什么是MVP：  异 -- Model与View之间的交互由Presenter完成 ，还有一点就是Presenter与View之间的交互是通过接口的
 * 1.M-model：数据模型层。即javaBean
 * 2.V-view：视图层，常用的即Activity、Fragment，这里是定义一个接口IView，写法：Activity/Fragment去实现IView
 * 3.P-presenter：(代理者)数据处理层，所有的的数据逻辑，业务逻辑都在这里处理。【PS: presenter 翻译： 提出者；推荐者；赠送者；任命者；主持人】
 *
 * 主要逻辑思路：
 *      1.view层，根据设计图实现基本布局，然后根据业务变化而引起布局显示变化，则构思设计为IView接口(业务变化是回调给View层(Activity/Fragment))，
 *        Activity/Fragment实现该接口，在对应回调(比如显示/隐藏进度条，(登录成功)界面切换)中做响应处理。
 *      2.数据模型层：即 JavaBean 的设计思路，①布局中动态改变的属性为必备[后台，移动端都具备]
 *        (常见的商品：name、price、total(库存)、discount、number(选择)，desc(描述)，time,browse(浏览记录);用户信息：name、age、sex、address、email等)，
 *        ②可以本地增设个别属性(没必要上传同步服务器的，比如商品列表中购物车选择了多种商品，此时可以增设一个selected(boolean)属性)，
 *        ③
 *      3.presenter:主要逻辑承担者，以前写在Activity/Fragment的的业务逻辑代码，比如加载首页信息，我们在HomePresenter加载数据，
 *        将加载好的数据回调给View层(Activity/Fragment)。比如登录逻辑(UserInfoPresenter)，将登录结果回调给View层即可，View通过Presenter反馈的信息做界面调整
 *        应当按业务模块多划分几个独立的Presenter类如(HomePresenter、LoginPresenter等)，遵循职责单一原则。
 *        在各个业务模块中构思代码结构。
 *        比如LoginPresenter中：
 *           ①login方法：会在View层(如Activity)中调用，故改设为public访问权限，具体实现网络请求登陆用户账号。
 *           ②进度条显示回调方法(这个比较频繁，应该设置在父类Presenter中，也在Activity/Fragment父类中做处理)，Java面向对象的继承特性体现。
 *           等等
 *        父类代码构思：
 *          1.IView：presenter与Activity(IView的实现类)的交互，应该有一个Presenter的类,因为Presenter有多个不同子类应该用泛型封装。
 *            View层的实现类基本(必然)用到Presenter，故为避免调用时发生空指针异常，加个注释
 *          2.Presenter:
 *          3.
 *
 *
 */

public interface BaseIView<P extends BasePresenter> {

    /**  presenter与Activity(IView的实现类)的交互，应该有一个Presenter的类,因为Presenter有多个不同子类应该用泛型封装。
     *   View层的实现类基本(必然)用到Presenter，故为避免调用时发生空指针异常，加个注释,
     *
     *   在IView实现类(子类)中重写该方法并new一个对应Presenter的子类对象返回
     * @return
     */
    P getPresenter();

    /**
     * @return 设置presenter，必要步骤，presenter是IView实现类的必要属性
     */
    void setPresenter(P p);


}
