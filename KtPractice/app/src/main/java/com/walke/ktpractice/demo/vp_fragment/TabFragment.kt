package com.walke.ktpractice.demo.vp_fragment

import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import com.walke.ktpractice.R
import kotlinx.android.synthetic.main.fragment_tab.*

/**
 * author Walke - 2020/12/1 1:53 PM
 * email 1126648815@qq.ocm
 * dec : 练习
 */
class TabFragment private constructor() : BaseTabFragment() {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): TabFragment {
            var tabFragment = TabFragment()
            tabFragment.arguments = Bundle().apply { putString("title", title) }
            return tabFragment
        }
    }

    override fun layoutId() = R.layout.fragment_tab
    override fun bundleData() {
        mTitle = arguments?.getString("title")
        printlnLog("-------------------> 创建了： $mTitle")
    }

    override fun initView() {
//        printlnLog("initView-------------------> ： $userVisibleHint") // 首次进来FragM 1焦点item为true
        ft_text.text = mTitle ?: ""
    }

    override fun loadData() {
        printlnLog("loadData ---- > 下载数据")
    }

    override fun printlnLog(msg: String) {
        super.printlnLog("$msg   ---  $mTitle")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        printlnLog("onDestroyView ---> 即销毁了")
    }

    /**
     * 打出日志可知：其实在Fragment的切换中onDestroy方法是没有走的。根据生命周期
     * onAttach > onCreate > onCreateView【root布局，布局中子控件不一定也实例了，参考TabFragment】 >
     * onActivityCreate【布局中子控件的】 > onStart > onResume > 【前台可见】>
     * onPause【休眠，如home键、打电话】> onStop > onDestroyView > onDestroy > onDetach 【完】
     * 所以当滑到FragM 5、6时FragM1这个实例是没有被完全会受到。所以部分全局变量的的值是不变的。切换Fragment主要是回收View这些较占内存资源的实例。
     */
    override fun onDestroy() {
        super.onDestroy()
        printlnLog("onDestroy ---> 即销毁了")
    }

}