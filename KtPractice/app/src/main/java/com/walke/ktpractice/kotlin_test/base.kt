package com.walke.ktpractice.kotlin_test

import kotlin.concurrent.thread

//fun main(args: Array<String>) {
//    var a = 10
//    print("a=  $a")
//
//    print(" 89 是   ${scoreLevel(89)}")
//}

// 语法 基本与Java相同 ，声明方式不同。
// fun 方法声明。
// var 变量 ， val 定量 (与Java的final有点像，const val 声明常量)
// Companion object 伴生对象，想Java的 静态成员。在类加载的时候也加载(进内存)。


// 运算符：没有了switch ，改用when。如下：

fun scoreLevel(score: Int): String {
    when (score / 10) {
        0, 9 -> {
            return "优秀"
        }
        7, 8 -> {
            return "良"
        }
        6 -> return "及格"
        else -> return "不及格"

    }
}

// "?"  加在变量名后，系统在任何情况不会报它的空指针异常。
// "!!" 加在变量名后，如果对象为null，那么系统一定会报异常！
// 参考：https://www.jianshu.com/p/51b2e5aa3dd8


private var lastClickTime: Long = -1

/**
 * 默认2秒内再次点击就是2次/多次点击
 * 严格上来说可能会出现交叉事件。即某两个/多个地方再时间范围内(2秒)分别调用方法。就可能导致异常效果。
 * 比如，第一个事件点击后，1秒另一个事件点击【那么之后的这个事件就是在时间范围内，即被判断为重复点击了】
 * 但是该方法仅用于点击事件。一般的情况还是够用的。重载了一个方法绑定事件，并自定义时间范围
 */
private val lock = java.lang.Object()
fun  isTwiceClick(tag:String):Boolean{
    synchronized(lock){
        var currentTimeMillis = System.currentTimeMillis()
        var interal = currentTimeMillis - lastClickTime // 时间间隔
        println("$tag------------->  interal:$interal     lastClickTime: $lastClickTime")
        return if (interal<2000){
            true
        }else{
            lastClickTime=currentTimeMillis
            false
        }
    }
}

private var eventMap = hashMapOf<String, Long>()
/**
 * 传入的事件，在时间范围内多次点击
 * @param event 点击事件
 * @param tagTime 自定义时间范围 单位毫秒
 *
 */
@Synchronized
fun isTwiceClick(event: String, tagTime: Long): Boolean {
    var currentTimeMillis = System.currentTimeMillis()
    var getLastClickTime = eventMap[event]
    var interval = currentTimeMillis - (getLastClickTime ?: 0) // 时间间隔
    return if (interval < tagTime) {
        true
    } else {
        eventMap[event] = currentTimeMillis
        false
    }
}

fun main(args: Array<String>) {
    println("111111 ${isTwiceClick("111",1000)}")
    Thread {
        Thread.sleep(2500)
        println("222222 ${isTwiceClick("222",500)}")

    }.start()
    Thread {
        Thread.sleep(500)
        println("111111-3 ${isTwiceClick("333",500)}")

        Thread.sleep(3500)
        println("444444 ${isTwiceClick("444",500)}")
    }.start()



}