package com.walke.ktpractice.kotlin_test.base

import com.google.gson.annotations.Until

/**
 * author Walke - 2020/10/13 11:30 AM
 * email 1126648815@qq.ocm
 * dec : kotlin for 循环使用： https://www.jianshu.com/p/27646c6561a7
 */

/**
 * forTest1。 fun：声明方法,默认访问权限就是公开。
 */
public fun forTest1() {

    println("遍历1~10包含两端：")
    for (index in 1..10) {
        print("$index  ")
    }
    println()
    println("遍历1~10不含尾端：")
    for (index in 1 until 10) {
        print("$index  ")
    }
    println()
    println("遍历10~1包含两端：")
    for (index in 10 downTo 1) {
        print("$index  ")
    }
    println()

    println("遍历1~9包含两端：间隔2, 即：1 3 5 7 9")
    for (index in 1..9 step 2) {
        print("$index  ")
    }
    println()

    println("遍历9~1包含两端：间隔2, 即(反)：1 3 5 7 9")
    for (index in 9 downTo 1 step 2) {
        print("$index  ")
    }
    println()
    // 注没找到倒数然后不含尾数的关键字,按数学的区间去理解，确实很少这样实际需求(情况)。
    // [1,10) 就是until 不含尾数的区间整数
    // [1,10] 就是.. 包含尾数的区间整数。 然后反序就是应用一些常见场景，既然是反序就通常两端包含。

    println("\n数组：0, 2, 4, 6, 8")
    var arrayInt = arrayOf(0, 2, 4, 6, 8)
    print("\n只取元素：")
    for (i in arrayInt) {
        print("$i ")
    }
    print("\n只取下标：")
    for (index in arrayInt.indices) {
        print("$index ")
    }
    print("\n都取.withIndex-->(index,e)中下标：")
    for ((index, e) in arrayInt.withIndex()) {
        print("$index  ${if (index == arrayInt.size - 1) "。" else ","}")
    }
    print("\n都取.withIndex-->(index,e)中元素：")
    for ((index, e) in arrayInt.withIndex()) {
        print("$e  ${if (index == arrayInt.size - 1) "。" else ","}")
    }
    println("\n数组：a, b, c, 6, 8 ，''你好'' [即默认是所有类型]")
    var array2 = arrayOf('a', 'b', 'c', 6, 8, "你好")
    for (i in array2) {
        print("$i ")
    }

    println()

}

/**
 * while 循环
 */
fun for_while() {

//    var i=j=0  kotlin 不支持这样写
    var i = 0
    var j = 0
    var k = 3
    println(" while 循环 \n while(i<10)  while具体条件")
    while (i <= 10) {
        i++
        print("$i  ")
    }
    println()
    println(" while(true)--> break")
    while (true) {
        print("$j  ")
        j++
        if (j > 9) break
    }

    println()
    println("do{ 循环语句 } while(true)--> continue、break 【与Java一样】")
    do {
        k--
        if (k == 0) continue // 与Java一样
        if (k < -2) break
        print("hello $k  -- ")
    } while (true)

}

/**
 * 递归
 */
fun digui() {
    println(" sum(5) = ${sum(5)}  |  sum(10) = ${sum(10)}")
    println(" fibonacci(5) = ${fibonacci(5)}  |  fibonacci(10) = ${fibonacci(10)}")

}

// 递归1,累加
fun sum(num: Int): Int {
    return if (num < 1) {
        return num // 出口 ,return关键字可省略
    } else { // 最后项总和=最后项+前些项总和
        num + sum(num - 1)
    }
}

/** 参考：https://jingyan.baidu.com/article/4f7d5712f1493c1a201927bd.html
 * 我们先来看看数学上有名的兔子数列，假设一对刚出生的小兔子一个月后就能长成大兔子，再过一个月就能生下一对小兔子，
 * 并且此后每个月都生一对小兔子，一年内没有死亡，请问一对刚出生的兔子，在一年内能繁殖成多少对兔子？
 * 根据下图的推算我们可以总结出如下的规律：第三个数是前两数之和，第四个数是第二个数和第三个数之和，...
 */
// 递归2,斐波纳契数列,求第几个；【 0,1, 1, 2, 3, 5, 8, 13, 21,34 ... 】
fun fibonacci(index: Int): Int {
    return if (index==1){ // 出口
        return 0
    }else if(index==2||index==3){
        return 1
    }else{ // 当前的数值等于前两个数字之和
        fibonacci(index-2)+ fibonacci(index-1)
    }
}