package com.walke.ktpractice.kotlin_test.base


/**
 * author Walke - 2020/12/2 9:35 AM
 * email 1126648815@qq.ocm
 * dec : kotlin 内联函数的认识与使用
 * 参考：https://www.jianshu.com/p/ab877fe72b40
 * 经典常用的内联函数
 * PS：
 * ?.意思是这个对象可以为空,但不执行对象的调用，且程序继续运行下去
 * !!.的意思是这个对象如果为空,此时执行对象的调用，并抛出空指针异常。
 */

// inline ：声明内联函数，action: () -> Any 【这是一个lambda表达式，指意函数】action函数名，()参数结构，any 返回值类型
inline fun inlineFunc(prefix: String, action: () -> Any) {
    println("$prefix call before inlineF() ")
    action() // 按照指定的顺序(位置)执行传入的参数。
    println(
        "$prefix call after inlineF()" +
                "\n-----\n"
    )
}

inline fun inlineFunc2(prefix: String, action: (n: Boolean) -> Any) {
    println("$prefix call before inlineF2() ")
    action(true) // 按照指定的顺序(位置)执行传入的参数。
    println(
        "$prefix call after inlineF2()" +
                "\n-----\n"
    )
}

inline fun inlineFunc3(prefix: String, action: (n: Int, b: Boolean) -> Any) {
    println("$prefix call before inlineF2() ")
    action(3, true) // 按照指定的顺序(位置)执行传入的参数。
    println(
        "$prefix call after inlineF2()" +
                "\n-----\n"
    )
}

fun func00(): Int {
    return -1
}

fun func01(): Any {
    return "func01"
}

fun func02(n: Int): Any {
    return "func02 $n"
}

fun func03(b: Boolean) {
    println("func03 $b \n")
}

fun func04(a: Boolean, b: Boolean) {
    println("func03 $b \n")
}

fun func05(a: Int, b: Boolean): Any {
    println("func03 $b \n")
    return 5
}

class InlineTest {
    fun test() {
        // ① 常用好理解的写法，参数
//        inlineFunc("11-params 1",  func01() ) 语法错误，-> {}符号--这里指的是lambda表达式的符号。可以看作声明/创建一个lambda表达式[即函数]
        inlineFunc("11-params 1", { func01() })
        inlineFunc("11-params 1", { func02(3) })
        inlineFunc2("21-params 1", { it -> func01() }) // inlineFunc2的内联函数带一个参数，没用到刻意隐藏，但还是不同于inlineFunc
        inlineFunc2("22-params 1", { func02(3) })
        inlineFunc2("23-params 1", { func03(false) })
        // 当lambda表达式作为方法的最后一个参数时，可以lambda表达式放到小括号外面。而如果只有一个参数就是lambda表达式，那么括号可以省略
        // https://blog.csdn.net/enjoyedu/article/details/104280922
        inlineFunc2("23-params 1") { func03(false) }
        inlineFunc2("23-params 1") { var num = 11;num }
        // ② lambda 表达式写法
        inlineFunc("12-params 2") {
            var i = 10
            println(i) // 没有这个会编译出错，即编辑器识别出语法出错。 其实在Kt中{}中常已最后一行为返回值。
//        i=20 // 这会语法出错，默认最后一行是返回值
        }
        inlineFunc2("22-params 2") {
            print("22-params 2 --> $it")
        }

        // 补充 {}符号--这里指的是lambda表达式的符号。可以看作声明/创建一个lambda表达式[即函数]
        // 参考： https://www.jb51.net/article/182816.htm
        // kt 中方法(函数)也会返回一个对象，所以方法也可以作为一个参数。【https://blog.csdn.net/zybieku/article/details/79107326?utm_source=blogxgwz4】
        // 【Kotlin 中的函数也是对象，可作为参数和返回值，也可以不用写在类里面】
        // 声明一个callback变量，该变量是函数。 格式：（(参数:类型)->返回值)
        var callback: ((str: String) -> Any)? = null
//        callback={str-> println(str)} //
        callback = { func03(true) } // 这个成功，所以这样的参数作为参数时，对函数格式不是严格一致的。只要是函数就可以
        callback?.invoke("hello") // 函数的invoke方法。

//        inlineFunc3("hui", func03(true)) // 语法报错
//        inlineFunc3("hui", func02(2)) //
//        inlineFunc3("hui", { func05(2,true)}) // 这也是有语法错误的
        inlineFunc3("hui", { i: Int, b: Boolean -> func05(2, true) })  // 这样的写法才符合语法
        inlineFunc3("hui", { i: Int, b: Boolean -> func03(true) })  // 这样的写法才符合语法
        inlineFunc3("hui") { i: Int, b: Boolean -> func03(true) }  // 这样的写法才符合语法

    }

    /**
     * 常用内联函数：let/run、with、apply/also、。 也被叫做标准函数
     * https://blog.csdn.net/woxinqidai/article/details/82763801
     * https://youkmi.blog.csdn.net/article/details/78786646?
     * https://blog.csdn.net/ifmvo/article/details/89207930?
     * https://www.jianshu.com/p/be78824ce1c2
     */
    fun test2() {
        //
        var car = Car("BaoMa", 50.0, "xxx/xxx/xxx")
        // let函数内可通过it引用当前对象，返回值为最后一行，可以与当前对象类型不同
        var let = car.let {
            println(it.logoUrl);
            var n = 10 // undefined
//             n // let 为 10
        }
        // 与let相似，不同点内部引用当前对象是this，
        // 还有一个不同，run可以不依赖对象
        var run = car.run {
            println(name);true // run = true   ；即let、run默认无返回值，或者说最后一行语句的值是返回值
        }
        // run 不依赖对象
        kotlin.run {

        }
        // 这是Inline
        run{
//            test()
        }
        println("-------> $let  -->$run")
        // apply 函数内可以通过 this引用当前对象, 返回当前对象。常用于改变对象属性，如listViewAdapter中的getView方法里的holder。
        var apply = car.apply {
            start() // 返回的是this
//            return 0 // 语法错误，看错误信息可知这个return直接return到test2方法，
            0
        }

        var apply2 = car.apply { start();name = "nameZ" } // 也是返回this
        // 与apply基本一样，不同点：函数内通过it引用当前对象。
        var also = car.also { it.start();it.logoUrl = "baidu/xx/xx.jpg";true }
        var also2 = car.also { it.start();it.logoUrl = "baidu/xx/xx.jpg" }
        // with函数接收两个参数, 一个对象, 一个函数, 函数内可用this引用对象, 返回值为最后一行, 可以与当前对象类型不同
        // 好理解的写法
        var with = with(car, { println(name) })
        var with2 = with(car) { name = "zzz";name }
        println("-------> $apply -->$apply2   -->$also  -->$also2   -->$with  -->$with2")

    }

    /**
     * 高阶函数：使用函数作为参数或者返回函数的函数。另一个理解，高阶就是函数里的函数
     * https://kotlinlang.org/docs/reference/lambdas.html
     * https://blog.csdn.net/enjoyedu/article/details/104280922  【主】
     * 使用高阶函数会给运行时带来一些坏处：每个函数都是一个对象，捕获闭包（如：访问函数体内的变量），内存分配（函数对象或Class），虚拟调用引入的运行过载。
     * 使用内联Lambda表达式在多数情况下可以消除这种过载。比如下面的函数就是这种情况下的很好的例子，lock()函数可以很容易地在调用点进行内联扩展。
     * https://zhuanlan.zhihu.com/p/27500059
     */
    fun test3() {

        // 这是函数变量
        var sum = { x: Int, y: Int -> print("求和：");x + y }
        var isBig = { x: Int, y: Int -> print("前比后大：");x > y }

        // kotlin 中可以把lambda表达式当做一个普通变量一样，去传递实参

        calculate(2, 3, sum, isBig)

//        testLambda1("hahaha") { n1, n2 -> sum(1, 2) } // 注意sum的结果是3，即完整调用了传入的函数，
        testLambda1("hahaha") { n1, n2 -> sum(n1, n2) } // 注意sum的结果是6，函数内部参数有效，
        testLambda2 { func00() }

        println("\nHui InlineTest.test3---> ---------------------------------------------\n") // 2020/12/2

        // 把lambda表达式当作参数去使用. 这种将lambda表达式作为参数或者返回值的函数，就叫高阶函数
        // 官方高阶函数：标准/内联函数、lazy
    }


}

class Car(var name: String, var price: Double, var logoUrl: String) {
    fun start() {

    }

    fun check(float: Float = 0.8f): Boolean {
        return float > 0.8f
    }
}

/**
 * https://blog.csdn.net/enjoyedu/article/details/104280922
 * lambda 表达式：
 *   结构： {     x:Int    ,    y:Any  ->  "$x <> $y" }
 *         {参数一，类型Int   参2       符号      方法体  }
 *  外围大括号，参数列表x:Int,y:Any  ，连接符 -> ， 方法体 "$x <> $y"
 *
 *  https://blog.csdn.net/enjoyedu/article/details/104280922
 */
// (Int,Int)->Int 相对于一个类型(函数的)
fun calculate(n1: Int, n2: Int, fun01: (Int, Int) -> Int, fun02: (a: Int, b: Int) -> Boolean) {
    println("执行默认---->")
    println("执行fun01--->${fun01(n1, n2)}")
    println("执行fun02--->${fun02(n1, n2)}")
}

// 当lambda表达式作为方法的最后一个参数时，可以lambda表达式放到小括号外面。而如果只有一个参数就是lambda表达式，那么括号可以省略
// https://blog.csdn.net/enjoyedu/article/details/104280922
fun testLambda1(str: String, fun01: (Int, Int) -> Int) {
    println("Hui .testLambda1---> 11111") // 2020/12/2
    var fun011 = fun01 // 这只是对象，
    var fun012 = fun01(1, 5) // 这是执行方法 ,注意结构是 3 。即完整调用了传入的函数，qie z这里的fan01(1,5中的参数无效)

    println("Hui .testLambda1---> f11: $fun011  -- >  f12 :$fun012") // 2020/12/2
    println("Hui .testLambda1---> 222222") // 2020/12/2
}

fun testLambda2(fun01: () -> Int) {
    fun01()
}


