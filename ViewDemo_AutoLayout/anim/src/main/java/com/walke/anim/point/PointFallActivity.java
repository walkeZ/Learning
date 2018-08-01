package com.walke.anim.point;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.walke.anim.AppActivity;
import com.walke.anim.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2018/4/16.
 *
 * Android 属性动画：实现小球坠落
 * 参考：https://blog.csdn.net/happy_horse/article/details/64502556
 */

public class PointFallActivity extends AppActivity {

    @BindView(R.id.point_view1)
    AnimPointView mPointView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_fall);
        ButterKnife.bind(this);
//        mPointView1.invalidate();
    }
}
