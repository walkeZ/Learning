package com.walker.mvvmlearn.demo_login2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author walker
 * @date 2024/5/18
 * @description Mvvm架构学习,学习ViewModel，直观释义：视图的数据对象实体。也叫状态数据(用户在页面上可见的数据)
 * > 一般指用于dataBinding的layout种的数据对象,Google对其进行了规范性的封装，关联了生命周期。
 * > 使用定义一个业务ViewModel 继承于 androidx.lifecycle.ViewModel
 *
 * > 好处：可以保障数据的稳定性，例如横竖屏切(生命周期变化)换时，自动稳定数据，不需要额外处理。
 * > 重点：保证状态数据的稳定性
 *
 * >VM层，里面放了各种数据
 */
public class LearnViewModel extends ViewModel {

    public int dataCore;

    public MutableLiveData<Integer> data1 = new MutableLiveData<>();
    public MutableLiveData<String> data2 = new MutableLiveData<>();
    public MutableLiveData<String> data3 = new MutableLiveData<>();
}
