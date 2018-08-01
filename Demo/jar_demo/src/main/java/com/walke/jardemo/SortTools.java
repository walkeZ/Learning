package com.walke.jardemo;

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
 *
 */

public class SortTools {

    // 单例模式：
    // 构造器私有化，不能外部另建实例，注：但可以通过反射创建实例，
    private SortTools() {

    }

    //  单例模式 懒汉式 end ，线程安全，绝对单例
    private static class SortToolsBuilder {
        // 静态内部类不是随着类的加载而加载(分配内存)的，是在第一次使用时，加载到内存的
        // 即懒加载效果
        // 静态变量随着类(静态内部类)的加载而加载，只new一次，正真意义上的单例。故线程安全，
        static final SortTools instance=new SortTools();
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
