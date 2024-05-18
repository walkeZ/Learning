package com.walker.mvvmlearn.demo_login2;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * @author walker
 * @date 2024/5/15
 * @description 登录的LoginViewModel 直接与xml关联
 */
public class Login21Model extends BaseObservable {

    public int status = 1;
    public String btnText = "登录";

    @Bindable
    private String username1 = ""; //

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
//        notifyChange();
        notifyPropertyChanged(BR.username1); //  @Bindable 后才可以BR.username1
    }
}
