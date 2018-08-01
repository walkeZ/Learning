package com.walke.myjar;

/**
 * Created by walke.Z on 2018/7/31.
 * AndroidStudio项目打包成jar :
 *      https://www.cnblogs.com/xxdh/p/6703746.html
 *
 *      发现与AS的版本有关(用2.1.2成功，经检测jdk都是1.8+)
 *      https://www.aliyun.com/jiaocheng/8069.html
 *      https://blog.csdn.net/wpyily/article/details/52913168
 *      将电脑jdk版本改为1.8，将gradle版本降为2.2以下,但是注意as2.2必须要相对应版本以上的gradle(2.2)。
 *      具体bug：
 *      java.lang.UnsupportedClassVersionError: com/android/build/gradle/AppPlugin :
 *      Unsupported major.minor version 52.0
 *
 * 单利模式参考 ：https://blog.csdn.net/h28496/article/details/49884469
 * 为什么说单例模式的饿汉式是天生线程安全的？
 * 答：因为饿汉式是确保整个程序只有一个实例的(即只new一次)，这时静态变量instance一直只保存一个对象地址，
 *    在Java和大部分语言中，一个对象不可能同时做两种或两次行为，即当对象在执行某个任务(某个代码块)时，该对象正在被使用中，
 *    无法另作他用，要当该对象空闲下来才能做其他任务。即线程安全。
 *    而普通的的懒汉式(懒加载思想)，是不一定只new一次的。当多线程执行 getInstance(){
 *    if(instance==null){instance=new ...} };这时可能出先多个线程同时走了非空判断，
 *    可能同时都为空，然后都走了new，该懒汉式模式并非在整整意义上实现了单例(多线程情况下多个实例)
 *
 */

public class SortTools {

    // 单例模式：
    // 构造器私有化，不能外部另建实例，注：但可以通过反射创建实例，
    private SortTools() {

    }

    //  单例模式 懒汉式 end ，线程安全，绝对单例
    // 改进 1 ：加锁

    private static class SortToolsBuilder {
        // 静态内部类不是随着类的加载而加载(分配内存)的，是在第一次使用时，加载到内存的
        // 即懒加载效果

        static final SortTools instance=new SortTools(); // 静态变量随着类(静态内部类)的加载而加载，只new一次，正真意义上的单例。故线程安全，
    }

    public static SortTools getInstance() {
        return SortToolsBuilder.instance;
    }

    /** 具体排序练习优化可看 SortTools1 ，也在待完善、待添加内容
     * 冒泡排序， 优点：每进行一趟排序，就会少比较一次，因为每进行一趟排序都会找出一个较大值
     *
     * @param arr
     * @return
     */
    public int[] bubbleSort(int[] arr) {  ///

        for (int i = 0; i < arr.length - 1; i++) {  // 第一层循环遍历每一个元素,最后一个不用遍历，因为已在第二层循环比较。
            int temp = 0; // 中间变量
            for (int j = i + 1; j < arr.length; j++) { // 第二层循环：遍历后面的元素，与第一层的当前元素进行比较，
//                if(arr[i]<arr[j]) { // 降序
                if(arr[i]>arr[j]) { // 升序的
                    temp = arr[i];
                    arr[i] = arr[j];//将大的放到前面。发现此时需要一个中间变量先存储arr[i]的，固有 int temp=0; // 中间变量 ，这行代码
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }



}
