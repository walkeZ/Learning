package com.walke.asproject

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Build.MODEL 手机(设备)厂商
        var model = Build.MODEL
        // 就是java中的 model.trim().length()<1然后判断长度 :如 java_demo中的HomeActivity.phoneCompany
        model = if (model.trim { it <= ' ' }.length < 1) "" else model
        println(model)


        var model2 = Build.MODEL

        model2 = model2.trim { it <= ' ' } //
        println(model2)

    }
    fun getModel():String{
        var model = Build.MODEL
        //  ?:  组合运算符，如果'?'前为空则返回':'后
        model=model?.trim{it<=' '}?:""
        return model
    }
}