package com.walke.demo;

/**
 * Created by walke.Z on 2018/8/10.
 *
 * 主持层(逻辑处理层)，持有model层对象调用model逻辑代码(数据获取)，通过持有IView的实现类对象来唤起
 *  (如：数据获取结果)回调，
 *
 * 父类封装:①让子类必须做一些事(父类为抽象类或者接口),构造器传入model对象
 *
 */

public abstract class BasePresenter<V extends BaseIView> {

     private V mIView;// 持有IView对象


    protected V getIView(){
        return mIView;
    }

    /** 初始化IView，当，初始化布局时应该调用，
     *
     * @param iView
     */
    public void attachView(V iView){
        mIView=iView;
    }

    /**
     * 回收IView，当Activity/Fragment不可视
     */
    public void dettachView(){
        mIView.hideLoading();
        mIView=null;
    }



}
