package com.walker.mvvmlearn.demo2;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

// 1.实现MvvmViewModel,数据实体，---(ViewModel的好处：保障数据稳定，例如横竖屏切换数据不会被重置,待定)
// ViewModel 实现数据(网络、本地文件、数据库)与View（xml）的关联，类似于桥梁。用于xml的<data>节点
public class MvvmViewModel2 extends BaseObservable {

    //2.模拟的网络请求对象
    private MvvmModel2 mvvmModel2; // 模拟的网络请求

    //EditText数据
    private String userInput;
    //网络返回数据
    private String result;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyChange(); // BaseObservable方法 通知所有对象变化
        notifyPropertyChanged(BR.result); // BaseObservable方法 通知属性变化
//        notify(); 这是object的方法
//        notifyAll();这也是是object的方法
    }

    // 暂时不知道 Application application 有啥用，可能与上下文或者生命周期有关
    public MvvmViewModel2(Application application) {
        this.mvvmModel2 = new MvvmModel2(); // 创建网络请求对象
    }

    // 3.模拟的网络请求发起
    public void getNetData() {
        mvvmModel2.getHttpData(userInput, new MCallback() {
            @Override
            public void success(String msg) {
                Log.i("MvvmViewModel2", "success: " + msg);
                setResult(msg);
            }

            @Override
            public void failed(String err) {
                Log.e("MvvmViewModel2", "success: " + err);
            }
        });
    }
}
