package com.hui.mvptest.pager;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.ButterKnifeActivity;
import com.hui.mvptest.pager.widget.VerticalPager;

import butterknife.BindView;

/**
 * Created by walke.Z on 2017/8/14.
 */

public class VerticalPageActivity extends ButterKnifeActivity {
    @BindView(R.id.av_vp)
    VerticalPager mVp;

    @Override
    public int layoutId() {
        logI("layoutId");
        return R.layout.action_vertical_page;
    }

    @Override
    protected void initData() {
        mVp.setPageChangeListener(new VerticalPager.OnPageChangeListener() {
            @Override
            public void onPageChange(int currentPage) {
                toast(currentPage+"");
            }
        });
    }

}
