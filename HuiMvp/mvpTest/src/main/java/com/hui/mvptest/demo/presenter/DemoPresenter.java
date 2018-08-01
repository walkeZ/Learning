package com.hui.mvptest.demo.presenter;

import com.hui.mvptest.demo.bean.DemoInfo;
import com.hui.mvptest.demo.model.IDemoModel;
import com.hui.mvptest.demo.view.IDemoView;

/**
 * 3.
 * Created by walke.Z on 2017/8/4.
 * presenter 控制器/表示器  通过接口实现view层(视图、界面)与model层(数据)的交互
 * 属性应有view、model的对应接口实例
 * 方法实现对应的(不可视/直观)逻辑，功能需求
 */

public class DemoPresenter {
    private IDemoView mIDemoView;
    private IDemoModel mIDemoModel;

    public DemoPresenter(IDemoView IDemoView, IDemoModel IDemoModel) {
        mIDemoView = IDemoView;
        mIDemoModel = IDemoModel;
    }
    /**
     * (根据id)读取数据(model)并显示在界面(view)
     *
     */
    public void readTestInfo(int id){
        DemoInfo demoInfo = mIDemoModel.read(id);
        mIDemoView.setInfo(demoInfo);
    }
    /**
     * (根据id)读取数据(model)并显示在界面(view)
     *
     */
    public void saveTestInfo(DemoInfo demoInfo){

        mIDemoModel.setTestInfo(demoInfo);
    }


}
