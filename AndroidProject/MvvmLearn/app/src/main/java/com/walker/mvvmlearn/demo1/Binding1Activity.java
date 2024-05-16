package com.walker.mvvmlearn.demo1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityBinding1Binding;

public class Binding1Activity extends AppCompatActivity {

    private User user;
    private ActivityBinding1Binding viewDataBinding; // 自动生成与xml的命名有关 activity_binding1

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding1);

        user = new User();
        user.setName("张三");
        user.hint = "您要修改名字吗？";
        user.setUserInput("默认输入");
        viewDataBinding.setMyUser(user); //绑定User

        viewDataBinding.setMyActivity(this); //绑定Activity
        MyClick myClick = new MyClick();
        // 绑定事件
        viewDataBinding.setClick(myClick);
    }

    public void myClick(View v) {
        // 替换名字
        user.setName(user.getUserInput());
        viewDataBinding.setMyUser(user);//更新数据
    }

    public int getAge() {
        return 28;
    }

    public String getSexText() {
        return user.sex == 0 ? "男":"女";
    }


    // 女士改名次数，验证入参
    public int getAge(boolean isFemale) {
        return isFemale ? 18 : 68;
    }

    public class MyClick {
        public void innerClick() {
            Toast.makeText(Binding1Activity.this, "innerClick " + user.getName(), Toast.LENGTH_LONG).show();
            user.setName(user.getUserInput() + " -321");
//            viewDataBinding.setMyUser(user);//更新数据 不调用不更新数据
        }
    }


}