package com.hui.mvptest.banner.presenter;

import android.content.Context;

import com.hui.mvptest.banner.model.BannerModel;
import com.hui.mvptest.banner.model.IBannerModel;
import com.hui.mvptest.banner.model.bean.Banner;
import com.hui.mvptest.banner.view.BannerActivity;
import com.hui.mvptest.banner.view.IBannerView;
import com.hui.mvptest.banner.view.widget.BannerAdapter;
import com.hui.mvptest.banner.view.widget.BannerAdapter1;
import com.hui.mvptest.banner.view.widget.BannerAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walke.Z on 2017/8/11.
 */

public class BannerPresenter {

    private Context mContext;
    private IBannerView mIBannerView;
    private IBannerModel mIBannerModel;
    private BannerAdapter2 mAdapter2;
    private BannerAdapter mAdapter;

    public BannerPresenter(BannerActivity activity) {
        mContext=activity;
        mIBannerView = activity;
        mIBannerModel = new BannerModel();
    }

    public void setData(String url){
        List<Banner> bannerList = mIBannerModel.loadBannerData(url);
        BannerAdapter1 adapter=new BannerAdapter1(mContext,bannerList);
        mIBannerView.setAdapter1(adapter);

        List<Banner> bannerList2 = mIBannerModel.loadBannerData2(url);
        mAdapter2 = new BannerAdapter2(mContext,bannerList2);
        mIBannerView.setAdapter2(mAdapter2);

        mAdapter = new BannerAdapter(mContext,bannerList2);
        mIBannerView.setAdapter(mAdapter);

       /* mAdapter2.setOnDataChangeListener(new BannerBaseAdapter2.OnDataChangeListener() {
            @Override
            public void dataChange(List list) {

            }
        });*/

        mIBannerView.startScroll(bannerList.size()*0,2000);

    }


    public void changeDatas() {
        List<Banner> list=new ArrayList<>();
        list.add(new Banner("广告02","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-019.jpg"));
        list.add(new Banner("广告03","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-010.jpg"));
        list.add(new Banner("广告04","http://p2.so.qhmsg.com/t01154029b7a35cf927.jpg"));
        mAdapter.changeDatas(list);

    }
    public void changeDatas2() {
        List<Banner> list=new ArrayList<>();
        list.add(new Banner("广告03","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-011.jpg"));
        list.add(new Banner("广告04","http://p2.so.qhmsg.com/t01154029b7a35cf927.jpg"));
        mAdapter.changeDatas(list);
    }
}
