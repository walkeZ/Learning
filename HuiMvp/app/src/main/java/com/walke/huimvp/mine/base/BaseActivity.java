package com.walke.huimvp.mine.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * 发现BaseIView中的通用方法showLoadingProgress()、hideLoadingProgress()应该独立新一个IView子接口
 *
 *
 * tos、logi （模板）自动补全设置：File->Settings->Editor->Live Templates->Android(tos),AndroidLog(logi)
 * 参考 ：https://www.jianshu.com/p/3dac8317c687 ；  https://blog.csdn.net/qq137722697/article/details/74085789
 */

//public  class BaseActivity1<P extends BasePresenter1,V extends BaseIView1> extends AppCompatActivity implements BaseIView1<P> {
public  class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseIView1 {

    @Override
    public void showLoading() {
        Toast.makeText(this,"showLoadingProgress",Toast.LENGTH_LONG).show();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this,"hideLoadingProgress",Toast.LENGTH_LONG).show();
    }
}
