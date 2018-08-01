package com.walke.jardemo;

/**
 * Created by walke.Z on 2018/7/31.
 */

public class SortTools2 {

    // 单例模式：
    // 构造器私有化，不能外部另建实例，注：但可以通过反射创建实例，
    private SortTools2() {

    }

    private static SortTools2 instance;

    //  单例模式 懒汉式 1 ，有线程安全问题

    public SortTools2 getInstance() {
        if (instance == null) {
            instance = new SortTools2();
        }
        return instance;
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
