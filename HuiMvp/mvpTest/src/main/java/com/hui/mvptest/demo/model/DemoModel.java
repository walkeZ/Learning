package com.hui.mvptest.demo.model;

import android.util.SparseArray;

import com.hui.mvptest.demo.bean.DemoInfo;

/**
 * Created by walke.Z on 2017/8/4.
 * 数据层
 */

public class DemoModel implements IDemoModel {
    //先用一个集合存储数据
    private SparseArray<DemoInfo> mInfoSparseArray=new SparseArray<>();

    @Override
    public DemoInfo read(int id) {
        return mInfoSparseArray.get(id);
    }

    @Override
    public void setTestInfo(DemoInfo demoInfo) {
        mInfoSparseArray.append(demoInfo.getId(), demoInfo);
    }


}
