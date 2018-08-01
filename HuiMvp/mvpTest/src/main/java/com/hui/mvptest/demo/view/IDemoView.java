package com.hui.mvptest.demo.view;

import com.hui.mvptest.demo.bean.DemoInfo;

/**
 * 2.
 * Created by walke.Z on 2017/8/4.
 * 响应具体视图变换的接口，即 控制器/表示器 通过该接口 实现改变View层的界面
 *
 * 该接口应在presenter中被调用，在activity中实现--响应具体视图变换
 *
 * //设计时注意界面中有哪些视图控件有与用户的交互(那些控件会变化,或用到[比如获取内容])，则对应应有方法回调
 * 设计时注意界面中有哪些功能/逻辑需求
 */

public interface IDemoView {
//    int getId();
//    int getAge();
//    String getName();
//    void setId(int id);
//    void setAge(int age);
//    void setName(String name);

    DemoInfo getInfo();
    void setInfo(DemoInfo demoInfo);

}
