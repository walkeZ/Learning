package com.walke.ktpractice.demo.vp_fragment

import android.os.Bundle
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
        printlnLog("------------------->  $mTitle")
    }

    override fun initView() {
        ft_text.text = mTitle ?: ""
    }

    override fun loadData() {
        printlnLog("")
    }

    override fun printlnLog(msg: String) {
        super.printlnLog("$msg   ---  $mTitle")
    }

}