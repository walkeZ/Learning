package com.walke.ktpractice.kotlin_test

fun main(args: Array<String>) {
    var a = 10
    print("a=  $a")

    print(" 89 是   ${scoreLevel(89)}")
}

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

