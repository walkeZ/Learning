package com.hui.mvptest.demo.model;

import com.hui.mvptest.demo.bean.DemoInfo;

/**
 * 4.
 * Created by walke.Z on 2017/8/4.
 * 在presenter中调用的model接口，在model中实现--响应具体数据操作(增删改查)
 *
 */

public interface IDemoModel {

    DemoInfo read(int id);

    void setTestInfo(DemoInfo demoInfo);

}
