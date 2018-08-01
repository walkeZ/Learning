package com.hui.mvptest.banner.custom;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.ButterKnifeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2017/8/16.
 */

public class CustomBannerActivity extends ButterKnifeActivity {
    @BindView(R.id.abc_banner)
    CustomBannerView mBanner;
    @BindView(R.id.abc_banner2)
    CustomBannerView mBanner2;
    @BindView(R.id.abc_banner3)
    CustomBannerView mBanner3;
    @BindView(R.id.abc_fab1)
    FloatingActionButton mFab1;
    @BindView(R.id.abc_fab2)
    FloatingActionButton mFab2;

    private boolean slideable;

    @Override
    public int layoutId() {
        return R.layout.activity_banner_custom;
    }

    @Override
    protected void initData() {
        List<Integer> viewRes = new ArrayList<>();
        viewRes.add(R.mipmap.meizi);
        viewRes.add(R.mipmap.meizi2);
        viewRes.add(R.mipmap.meizi3);
        viewRes.add(R.mipmap.meizi4);
        viewRes.add(R.mipmap.meizi5);

        mBanner.setViewRes(viewRes);

        List<String> urls = new ArrayList<>();
        urls.add("http://pic.58pic.com/58pic/13/20/45/08h58PICR7Y_1024.jpg");
        urls.add("http://pic36.nipic.com/20131227/10080014_160615476000_2.jpg");
        urls.add("http://img3.3lian.com/2013/v10/4/d/81.jpg");
        urls.add("http://pic9.nipic.com/20100814/668573_170129076821_2.jpg");

        mBanner2.setViewUrls(urls);

        mBanner2.setOnBannerItemClickListener(new CustomBannerView.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(CustomBannerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mBanner3.setLoop(false);
        mBanner3.setViewRes(viewRes);
    }


    @OnClick({R.id.abc_fab1, R.id.abc_fab2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.abc_fab1:
                mBanner.setAutoPlay(true);
                mBanner.startAutoPlay();
                break;
            case R.id.abc_fab2:
                slideable = !slideable;
                mBanner.setSlideable(slideable);
                Toast.makeText(this, "最上方view可滑动：" + slideable, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
