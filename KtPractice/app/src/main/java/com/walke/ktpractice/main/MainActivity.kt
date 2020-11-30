package com.walke.ktpractice.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.walke.ktpractice.R

/**
 * main包中完全就是复制创建项目是选中Navigation Activity。。。
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        // 报错了： Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void androidx.appcompat.app.ActionBar.setTitle(java.lang.CharSequence)' on a null object
        // 断点进源码发现：ActionBarOnDestinationChangedListener的setTitle方法中actionBar为null了
        // 这个报错与主题有关。之前默认主题是：...NoActionBar.
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}