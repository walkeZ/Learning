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
abstract class BaseTabFragment : Fragment() {

    private var rootView: View? = null

    /**
     * View是否准备好即：执行了onActivityCreate
     */
    private var isViewReady = false

    /**
     * 是否对用户可见，或者换句话讲是否是焦点Item
     */
    private var isFocusItem = false

    /**
     * 是否下载过
     */
    private var isLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutId(), null)

//        initView() // 这里initVIew会报错，主要原因是：子类中kt布局的控件(ft_text)还没完成实例化。换句话说，kt中不是在这方法实例化布局控件的
        bundleData()
//        printlnLog("onCreateView ---> 即创建了")
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initView() // 这时kt将布局中的控件完成实例化了
//        printlnLog("onViewCreated ---> 初始化View")

    }

    /**
     * 这个在onViewCreate后，生命周期顺序：onCreateView > onViewCreate > onActivityCreate
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView() // 还是放到这里吧，这onActivityCreate方法列入了常规生命周期。
//        printlnLog("onActivityCreated ---> 初始化View2")


//        loadData()// 直接放到这里，就会在addFragment的时候随着生命周期把，adapter中左右缓存的页面也一起执行了，这是耗费不必要资源不科学。
        isViewReady = true
        lazyLoad()

    }

    /**
     * 点击查看源码发现主要在FragmentPagerAdapter和FragmentPagerStateAdapter中调用。
     * 在instantiateItem、setPrimaryItem中设置为false
     * 在setPrimaryItem【设置主item，即焦点item】中设置为true，
     * 所以这个的生命周期是不严谨的。当然需要懒加载的情况通常也是因为使用了多个Fragment常切换。
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
//        printlnLog("setUserVisibleHint ---> $isVisibleToUser")

//        printlnLog("首次进来---》 $isVisibleToUser")// 首次进来没有日志
//        if (isVisibleToUser) loadData() // 直接放到这里，看日志可以发现，
        // 有两个问题：①首次进入的FragM 1没有加载，查看adapter代码，并断点发现，首次进入没有执行setPrimaryItem方法。
        // ② 切换后切回如【FragM 1 > 2 > 1】FragM 1切回时再次执行了loadData。
        // 基于以上两个情况，结合onActivityCreated中的情况，定义一个lazyLoad（）
//         isFocusItem = isVisibleToUser
        if (isVisibleToUser) lazyLoad()

    }

    /**
     * 当首次进来时
     */
    fun lazyLoad() {
        if (isViewReady && userVisibleHint && !isLoaded) {
            loadData()
            isLoaded = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false // 不在缓存的要重新下载了
    }


//    override fun onDestroy() {
//        super.onDestroy()
////        printlnLog("onDestroy ---> 即销毁了")
//    }

    abstract fun layoutId(): Int
    open fun bundleData() {}
    abstract fun initView()

    /** 参考：https://www.cnblogs.com/kma-3/p/7096057.html
     * 其实这种封装是设计模式中的：模板方法模式。
     */
    abstract fun loadData()


    open fun printlnLog(msg: String) {
        Log.i("Hui", "BTF.printLog: ----->  $msg")
        try {
            (activity as Main2Activity).printlnLog(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}