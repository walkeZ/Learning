package com.walke.jardemo;

/**
 * Created by walke.Z on 2018/7/31.
 * 单例模式模式练习
 * 恶汉式：
 * 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getInstance方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到lazy loading[懒加载]的效果。
 */

public class SortTools1 {

    // 单例模式：
    // 构造器私有化，不能外部另建实例，注：但可以通过反射创建实例，
    private SortTools1() {

    }

    // 单例模式 ： 恶汉式(一开始就创建实例，当没用到时，就对应有内存浪费[即内存分配给了一个不工作的对象])，天生线程安全，
    private static SortTools1 instance = new SortTools1(); // 静态成员是在类的加载中就分配内存的，故实例一开始就new了。

    /**
     * 获取单例
     *
     * @return
     */
    public static SortTools1 getInstance() {
        return instance;
    }


    /**
     * int 数组排序，自定义方法
     *
     * @param arr
     * @param isDesc 是否降序
     * @return
     */
    public int[] sortArr1(int[] arr, boolean isDesc) {  ///

//        for (int i ;arr) {  // (这里)不能直接用 for each
        for (int i = 0; i < arr.length; i++) {  // 第一层循环遍历每一个元素
            int temp = 0; // 中间变量
            for (int j = 1; j < arr.length; j++) { // 第二层循环：遍历另外的元素，与第一层的元素进行比较
                if (isDesc) { // 降序，将大的放到前面，即两两对比后将小的赋值给第一层循环到的对应下标的元素
                    if (arr[i] < arr[j]) { // 将第一层循环到的元素与后一个元素比较，然后进行换位
                        temp = arr[i];
                        arr[i] = arr[j];//将大的放到前面。发现此时需要一个中间变量先存储arr[i]的，固有 int temp=0; // 中间变量 ，这行代码
                        arr[j] = temp;
                    }
                } else {// 升序
                    if (arr[i] > arr[j]) { // 将第一层循环到的元素与后一个元素比较，然后进行换位
                        temp = arr[j];
                        arr[j] = arr[i];//将小的放到前面。发现此时需要一个中间变量先存储arr[i]的，固有 int temp=0; // 中间变量 ，这行代码
                        arr[i] = temp;
                    }
                }
            }
        }
        return arr;
    }


    /**
     * int 数组排序，自定义方法，优化改进
     *
     * @param arr
     * @param isDesc 是否降序
     * @return
     */
    public int[] sortArr2(int[] arr, boolean isDesc) {  ///

        for (int i = 0; i < arr.length; i++) {  // 第一层循环遍历每一个元素
            int temp = 0; // 中间变量
            for (int j = i + 1; j < arr.length; j++) { // 第二层循环：遍历另外的元素，与第一层的元素进行比较，发现，当i>i 时，有混乱比较(重复比较)，
                //
                if (isDesc) { // 降序，将大的放到前面，即两两对比后将小的赋值给第一层循环到的对应下标的元素
                    if (arr[i] < arr[j]) { // 将第一层循环到的元素与后一个元素比较，然后进行换位
                        temp = arr[i];
                        arr[i] = arr[j];//将大的放到前面。发现此时需要一个中间变量先存储arr[i]的，固有 int temp=0; // 中间变量 ，这行代码
                        arr[j] = temp;
                    }
                } else {// 升序
                    if (arr[i] > arr[j]) { // 将第一层循环到的元素与后一个元素比较，然后进行换位
                        temp = arr[j];
                        arr[j] = arr[i];//将小的放到前面。发现此时需要一个中间变量先存储arr[i]的，固有 int temp=0; // 中间变量 ，这行代码
                        arr[i] = temp;
                    }
                }
            }
        }
        return arr;
    }

    /**
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

    //  当前类测试
    // main方法快捷方式： 首字母：psvm
    public static void main(String[] args) {
        int[] a1 = new int[]{5, 3, 8, 2};
        a1 = SortTools1.getInstance().sortArr1(a1, true);
        for (int i : a1) {
            System.out.println(i);//  8,2,3,5 有毛病
        }
        System.out.println("----——sortArr2------");

        a1 = SortTools1.getInstance().sortArr2(a1, true);
        for (int i : a1) {
            System.out.println(i);//  8,2,3,5 有毛病
        }

        System.out.println("----——bubbleSort------");

        a1 = SortTools1.getInstance().bubbleSort(a1);
        for (int i : a1) {
            System.out.println(i);//  8,2,3,5 有毛病
        }
    }


}
