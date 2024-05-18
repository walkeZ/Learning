package com.walker.mvvmlearn.demo_login2;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author walker
 * @date 2024/5/15
 * @description 登录的LoginViewModel 直接与xml关联
 */
public class Login21ViewModel extends ViewModel {

    public int status = 1;

    // 所有的状态数据(用户可见)，全部保存到此处
    public MutableLiveData<String> loginState = new MutableLiveData<>();

    public String username = ""; //

    public MutableLiveData<String> username2 = new MutableLiveData<>();// 对比 username,username2直接双向绑定
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> btnText = new MutableLiveData<>("登录");


    public MutableLiveData<Boolean> isInputPassword = new MutableLiveData<>();
    public ObservableBoolean isInputNotEmpty() {
        return new ObservableBoolean(password.getValue() != null && !password.getValue().isEmpty());
    }


}
