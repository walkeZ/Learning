package com.walke.ktpractice.constant

import android.content.Context
import android.widget.Toast


/**
 * 该文件放置一些通用公用方法。通过拓展的方式。
 * 可以通过类名.方法名的方式。给该类拓展成员(方法)。 在其他地方使用时可以直接  对象(该类的).方法名调用。
 * expand : 拓展，扩大
 */

// 如 toast的拓展，"拓展吐司".toast()
fun String.toast(ctx:Context){
    // 方法体内this即该类的对象
    Toast.makeText(ctx, this, Toast.LENGTH_SHORT).show()
}
fun Context.toast(msg:String){
    Toast.makeText(this@toast, msg, Toast.LENGTH_SHORT).show()
}

// 常量

const val key_flutter_engine="my_engine_key"
const val key_flutter_engine2="my_engine_key2"
const val key_flutter_engine3="my_engine_key3"
