package com.walke.testespresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** Android自动化测试--Espresso使用案例:
 *  https://www.jianshu.com/p/74af4c7043c9
 *
 *  可另参考
 *      https://www.jianshu.com/p/22a09ae7b995
 *      https://www.jianshu.com/p/cb06c4be07fa
 *
 *  进阶 https://www.jianshu.com/p/22a09ae7b995
 *
 *  APPIUM框架
 *  https://www.cnblogs.com/fnng/p/4540731.html
 *  安装Selenium:命令：python -m pip install --upgrade pip 出现bug：
 *      You are using pip version 9.0.3, however version 10.0.1 is available.
 *      You should consider upgrading via the 'python -m pip install --upgrade pip' command.
 *  解决方案：以管理员身份运行cmd（C:\Windows\System32\cmd.exe-》右键），
 *  pip install selenium 命令bug: Could not install packages due to an EnvironmentError: [WinError 5] 拒绝访问。:
 *  解决方案同上
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etNumber1)
    EditText mEditText1;
    @BindView(R.id.etNumber2)
    EditText mEditText2;
    @BindView(R.id.tvResult)
    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btCalculate, R.id.btWebView, R.id.btRecyclerView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btCalculate:
                String num1 = mEditText1.getText().toString().trim();
                if (TextUtils.isEmpty(num1)) {
                    Toast.makeText(this, "请输入数字1", Toast.LENGTH_SHORT).show();
                    return;
                }
                String num2 = mEditText2.getText().toString();
                if (TextUtils.isEmpty(num2)) {
                    Toast.makeText(this, "请输入数字2", Toast.LENGTH_SHORT).show();
                    return;
                }
                mTvResult.setText("计算结果："+(Integer.parseInt(num1)+Integer.parseInt(num2)));
                break;
            case R.id.btWebView:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url","http://www.baidu.com/");
                startActivity(intent);
                break;
            case R.id.btRecyclerView:
                startActivity(new Intent(this,RecyclerViewActivity.class));
                break;
        }
    }
}
