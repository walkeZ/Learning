package com.walker.mvvmlearn.demo1;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

// ② 实体数据，继承BaseObservable，省略点击事件中的更新数据
//public class User {
public class User extends BaseObservable {
    private String name;
    private String userInput;

    public String hint = "请输入修改的名字";

    public int sex = 0;// 0 男，1 女

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name); // 一开始会爆红当直接跑项目即可，name要对应属性
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
