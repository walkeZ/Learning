package com.walke.ktpractice.kotlin_test.base

import android.util.Log

/**
 * author Walke - 2020/10/9 5:07 PM
 * email 1126648815@qq.ocm
 * dec: Kotlin关键字：by 参考：https://blog.csdn.net/wzgiceman/article/details/82689135
 * Kotlin 中 by 关键字用来简化实现代理 (委托) 模式，不仅可以类代理，还可以代理类属性, 监听属性变化，下面我们来介绍by的几种主要使用场景:
 * ~类的代理 class
 * ~属性延迟加载 lazy ; 可以理解成按需加载,使用时生成一个类单例对象,不使用无须生成对象
 * ~可观察属性 Delegates.observable ( 扩展 Delegates.vetoable )
 * ~自定义监听属性变化 ReadWriteProperty
 * ~属性非空强校验 Delegates.notNull()
 * ~Map值 映射到类属性 map
 */

// 类的的代理(代理委托模式)
class ByTest {

    // 定义一个接口，和一个方法 show
    interface IBase{
        fun show()
    }

    // 定义一个IBase的子类(实现类)，用open修饰，使之可被继承; 实现show方法
//    open class BaseImpl:IBase{
    class BaseImpl:IBase{
        override fun show() {
            println("Hui BaseImpl.show---> ") // 2020/10/9
        }
    }

    // 定义代理类BaseProxy实现IBase接口，构造函数参数是一个IBase对象
    // by 后跟IBase对象，不需要再实现show，已经带这个方法了
//     abstract class BaseProxy(base:IBase):IBase  by base{
    class BaseProxy(base:IBase):IBase  by base{
        //拓展另外方法 因为这里是base的实例化，故不能添加抽象方法[即使abstract修饰BaseProxy也不能]
        fun show2(msg:String){
            println("Hui BaseProxy.show---> $msg") // 2020/10/9
        }
    }

    fun test(){
        var baseImpl = BaseImpl()
        var baseProxy = BaseProxy(baseImpl)
        baseProxy.show()
        baseProxy.show2("ttttt")
    }





}

fun main(vararg msg:String) {
    ByTest().test()
}