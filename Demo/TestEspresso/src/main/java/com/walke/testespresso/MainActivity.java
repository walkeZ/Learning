package com.walke.testespresso;

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

/**
 *  https://www.jianshu.com/p/74af4c7043c9
 *  Android自动化测试--Espresso使用案例
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
                mTvResult.setText("结果："+(Integer.parseInt(num1)+Integer.parseInt(num2)));
                break;
            case R.id.btWebView:

                break;
            case R.id.btRecyclerView:

                break;
        }
    }
}
