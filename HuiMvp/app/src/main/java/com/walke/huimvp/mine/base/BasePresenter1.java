package com.walke.huimvp.mine.base;

/**
 * Created by walke.Z on 2018/8/6.
 * <p>
 * BasePresenter需要持有IView的子类引用，以便于使用其(IView的子类)对应的回调方法，通知View层(然后针对性刷新界面)
 * 故用class而不是interface。
 * 然后也想在BasePresenter的子类中对应做一些必要操作故应该用 abstract 抽象修饰,
 * <p>
 * 进一步分析：当耗时操作执行完，回调到对应的View层(Activity/Fragment)，已切换，可能就没必要执行回调(通知UI刷新)或者其他耗时操作了(下一个请求)
 * 故添加 attachView、detachView方法，并设一个全局变量用于获取
 */

public abstract class BasePresenter1<V extends BaseIView1> {

    private V mIView;

    /**
     * 关联的View层是否还是可视(还在前台)，
     * PS：知识回顾：这里切记不能用 静态 static 修饰，因为所有对象共享静态内存(即共享 static 成员变量)。
     *              当你其中一个对象改了对应static的值会影响到其他对象。可谓牵一发而动全身呐。
     */
    private boolean isAttachView = false;

    public BasePresenter1(V iView) {
        mIView=iView;
//        throw new RuntimeException("Stub!"); // 让子类必须重写改构造器,不能随便使用呀
    }

    public V getIVIew() {
        return mIView;
    }

    public void attachView(V iView) {
        this.mIView = iView;
        isAttachView = true;
    }

    public void detachView() {
        mIView = null;
        isAttachView = false;
    }

    public boolean isAttachView() {
        return isAttachView;
    }
}
