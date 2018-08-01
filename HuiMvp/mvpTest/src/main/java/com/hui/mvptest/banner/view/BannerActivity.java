package com.hui.mvptest.banner.view;

import android.view.View;

import com.hui.mvptest.R;
import com.hui.mvptest.banner.model.bean.Banner;
import com.hui.mvptest.banner.presenter.BannerPresenter;
import com.hui.mvptest.banner.view.widget.BannerAdapter;
import com.hui.mvptest.banner.view.widget.BannerAdapter1;
import com.hui.mvptest.banner.view.widget.BannerAdapter2;
import com.hui.mvptest.banner.view.widget.BannerView;
import com.hui.mvptest.banner.view.widget.BannerView2;
import com.hui.mvptest.banner.view.widget.BannerViewPager1;
import com.hui.mvptest.base.activity.TitleActivity;
import com.hui.mvptest.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告轮播
 */
public class BannerActivity extends TitleActivity implements IBannerView  {


    private BannerViewPager1 mBannerViewPager1;
    private BannerPresenter mBannerPresenter;
    private BannerView2 mBannerView2;
    private BannerView mBannerView;

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView(TitleLayout titleLayout) {
        titleLayout.setTitleText("Banner横幅广告轮播");
        mBannerViewPager1 = ((BannerViewPager1) findViewById(R.id.ab_bannerViewPager));
        mBannerView2 = ((BannerView2) findViewById(R.id.ab_bannerView2));
        mBannerView = ((BannerView) findViewById(R.id.ab_bannerView));
        mBannerPresenter = new BannerPresenter(this);

    }

    @Override
    protected void initData() {
        mBannerPresenter.setData("http://www.baidu.com/");
        //mBannerPresenter.startScroll();
    }

    @Override
    public void setAdapter1(BannerAdapter1 adapter) {
        /*mBannerViewPager.setBannerAdapter(adapter);*/
        mBannerViewPager1.setAdapter(adapter);
    }

    @Override
    public void setAdapter2(BannerAdapter2 adapter) {
        mBannerView2.setAdapter(adapter);
    }

    @Override
    public void setAdapter(BannerAdapter adapter) {
        mBannerView.setAdapter(adapter);
    }

    @Override
    public void startScroll(int startPosition, int xtime) {

        mBannerViewPager1.setCurrentItem(startPosition);
        mBannerViewPager1.startRoll();

        mBannerView2.startRoll(startPosition,xtime);
        mBannerView.startRoll(startPosition,xtime);

    }

    public void click1(View view){
        //mBannerPresenter.changeDatas();

        List<Banner> list=new ArrayList<>();
        list.add(new Banner("广告20","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-019.jpg"));
        list.add(new Banner("广告30","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-010.jpg"));
        list.add(new Banner("广告40","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-010.jpg"));
        mBannerView2.changeDatas(list);

    }
    public void click2(View view){
        mBannerPresenter.changeDatas();



    }
    public void click3(View view){
        mBannerPresenter.changeDatas2();
    }
}
