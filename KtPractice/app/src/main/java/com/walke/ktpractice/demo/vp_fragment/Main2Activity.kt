package com.walke.ktpractice.demo.vp_fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.walke.ktpractice.R
import com.walke.ktpractice.constant.toast
import com.walke.ktpractice.kotlin_test.main
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private val tabTitles = ArrayList<String>()
    private val tabFragments = ArrayList<BaseTabFragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initView()
    }

    private fun initView() {
        for (i in 1 .. 7) {
            tabTitles.add("FragM $i")
            tabFragments.add(TabFragment.getInstance("FragM $i"))
        }

//        main2_viewPager.adapter=MyFragmentPagerAdapter(supportFragmentManager, tabTitles,tabFragments)
//        main2_tabLayout.setupWithViewPager(main2_viewPager)

    }

    fun printlnLog(msg: String) {
        Log.i("Hui", "Main2Activity.printlnLog: ----->  $msg")
        main2_llLog.addView(TextView(this).apply { text = msg })
    }

    fun onClick(view: View) {
        when (view) {
            main2_flbMenu -> {
                if (main2_vLog.visibility == View.VISIBLE)
                    main2_vLog.visibility = View.GONE
                else
                    main2_vLog.visibility = View.VISIBLE
            }
            main2_btCleanLog -> main2_llLog.removeAllViews()
            main2_btOffset2 -> {
                // 重新打开自己，默认启动模式所以都会新建
                startActivity(Intent(this, Main2Activity::class.java))
                finish()
            }
            else -> {
                toast("other click")
            }
        }
    }
}


private class MyFragmentPagerAdapter(
    fm: FragmentManager,
    var titles: ArrayList<String>,
    var fgs: ArrayList<BaseTabFragment>
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = fgs[position]

    override fun getCount() = titles.size


    /**
     * 查看super的代码可知已默认实现了里面具体的Fragment管理，同理destroy方法也是
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }


    override fun getPageTitle(position: Int) = titles[position]

}