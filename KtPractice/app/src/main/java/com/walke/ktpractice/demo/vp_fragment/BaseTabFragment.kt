package com.walke.ktpractice.demo.vp_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * author Walke - 2020/12/1 10:02 AM
 * email 1126648815@qq.ocm
 * dec : Fragment基础类
 *
 * 主要实验：懒加载。知其然，并知其所以然。
 * 场景：头条tab新闻分类，
 * 补充知识：生命周期常用
 * 开始：Fragment is add 后才进入生命周期，如Main2Activity中创建了实例但为setAdapter时tabFragment的onCreateView的log不打印
 * addFragment到布局之后：
 * onAttach > onCreate > onCreateView【root布局，布局中子控件不一定也实例了，参考TabFragment】 >
 * onActivityCreate【布局中子控件的】 > onStart > onResume > 【前台可见】>
 * onPause【休眠，如home键、打电话】> onStop > onDestroyView > onDestroy > onDetach 【完】
 *
 *
 */
abstract class BaseTabFragment:Fragment(){

    private var rootView:View?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView==null)
            rootView=inflater.inflate(layoutId(),null)

//        initView() // 这里initVIew会报错，主要原因是：子类中kt布局的控件(ft_text)还没完成实例化。换句话说，kt中不是在这方法实例化布局控件的
        bundleData()
        printlnLog("onCreateView ---> 即创建了")
        return rootView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView() //
        printlnLog("onViewCreated ---> 初始化View")
    }

    /**
     * 这个在onViewCreate后，生命周期顺序：onCreateView > onViewCreate > onActivityCreate
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initView()
        printlnLog("onActivityCreated ---> 初始化View2")
    }

    /**
     * 点击查看源码发现主要在FragmentPagerAdapter和FragmentPagerStateAdapter中调用。
     * 在instantiateItem、setPrimaryItem中设置为false
     * 在setPrimaryItem中设置为true
     * 所以这个的生命周期是不严谨的。当然需要懒加载的情况通常也是因为使用了多个Fragment常切换。
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        printlnLog("setUserVisibleHint ---> $isVisibleToUser")
    }

    override fun onDestroy() {
        super.onDestroy()
        printlnLog("onDestroy ---> 即销毁了")
    }

    abstract fun layoutId(): Int
    open fun bundleData(){}
    abstract fun initView()

    abstract fun loadData()


    open fun printlnLog(msg:String){
        Log.i("Hui", "BaseTabFragment.printLog: ----->  $msg")
//        (activity as Main2Activity).printlnLog(msg)
    }



}