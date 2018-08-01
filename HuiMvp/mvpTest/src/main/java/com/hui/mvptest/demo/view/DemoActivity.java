package com.hui.mvptest.demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hui.mvptest.R;
import com.hui.mvptest.demo.bean.DemoInfo;
import com.hui.mvptest.demo.model.DemoModel;
import com.hui.mvptest.demo.presenter.DemoPresenter;
import com.hui.mvptest.location.SeatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1.
 * MVP设计模式(架构) Controller/Presenter负责逻辑的处理，Model提供数据，View负责显示(与用户的可视化交互，界面点击响应效果)
 * 先写view视图层，先写activity 然后写一个对应的接口(响应用户操作[点击等])
 * 再presenter(不可视逻辑层/控制层),对应在Activity中应有一个presenter的对象
 * 然后model(数据层)，
 */
public class DemoActivity extends AppCompatActivity implements IDemoView {

    @BindView(R.id.btnSave)
    Button mButton;
    @BindView(R.id.btnRead)
    Button mButton2;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.editText2)
    EditText mEditText2;
    @BindView(R.id.editText3)
    EditText mEditText3;
    @BindView(R.id.textView4)
    TextView mTextView4;
    private DemoPresenter mDemoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
        mDemoPresenter = new DemoPresenter(this, new DemoModel());
    }


    @OnClick({R.id.btnSave, R.id.btnRead,R.id.textView4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                mDemoPresenter.saveTestInfo(getInfo());
                break;
            case R.id.btnRead:
                int id = Integer.parseInt(mEditText.getText().toString());
                mDemoPresenter.readTestInfo(id);
                break;
            case R.id.textView4:
               startActivity(new Intent(this, SeatActivity.class));
                break;
        }
    }

    /**
     * 从界面获取信息
     */
    @Override
    public DemoInfo getInfo() {
        DemoInfo demoInfo = new DemoInfo();
        demoInfo.setId(Integer.parseInt(mEditText.getText().toString()));
        demoInfo.setName(mEditText2.getText().toString());
        demoInfo.setAge(Integer.parseInt(mEditText3.getText().toString()));
        return demoInfo;
    }

    /**
     * 设置界面内容
     */
    @Override
    public void setInfo(DemoInfo demoInfo) {
        mTextView4.setText(demoInfo.toString());
        mEditText2.setText(demoInfo.getAge() + "");
        mEditText3.setText(demoInfo.getName() + "");
    }

}
