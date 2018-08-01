package com.walke.anim.fireworks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.walke.anim.AppActivity;
import com.walke.anim.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2018/4/16.
 * Android开发——为EditText添加烟花效果的实现：
 * 参考： https://blog.csdn.net/SEU_Calvin/article/details/54745708
 */

public class FireworksEditTextActivity extends AppActivity {

    @BindView(R.id.afe_edit_text)
    EditText mEditText;
    @BindView(R.id.afe_fire_work)
    FireworkView mFireWork;
    @BindView(R.id.afe_day)
    TextView tvDay;
    @BindView(R.id.afe_night)
    TextView tvNight;
    @BindView(R.id.afe_layout)
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fireworks_edittext);
        ButterKnife.bind(this);

        mFireWork.bindEditText(mEditText);
    }

    @OnClick({R.id.afe_day, R.id.afe_night})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.afe_day:
                rootLayout.setBackgroundColor(0xFFFFFFFF);
                break;
            case R.id.afe_night:
                rootLayout.setBackgroundColor(0xFF000000);
                break;
        }
    }
}
