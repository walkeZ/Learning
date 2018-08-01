package com.hui.mvptest.banner.model;

import com.hui.mvptest.banner.model.bean.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walke.Z on 2017/8/11.
 */

public class BannerModel implements IBannerModel {

    @Override
    public List<Banner> loadBannerData(String url) {
        //模拟网络请求数据
        List<Banner> bannerList=new ArrayList<>();
        bannerList.add(new Banner("广告1","http://p2.so.qhmsg.com/t01154029b7a35cf927.jpg"));
        bannerList.add(new Banner("广告2","http://img.sucai.redocn.com/attachments/images/201208/20120822/Redocn_2012080618372383.jpg"));
        bannerList.add(new Banner("广告3","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-019.jpg"));
        bannerList.add(new Banner("广告4","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-010.jpg"));
        bannerList.add(new Banner("广告5","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-011.jpg"));
        return bannerList;
    }

    @Override
    public List<Banner> loadBannerData2(String url) {
        //模拟网络请求数据
        List<Banner> bannerList=new ArrayList<>();
        bannerList.add(new Banner("广告5","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-011.jpg"));
        bannerList.add(new Banner("广告4","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-010.jpg"));
        bannerList.add(new Banner("广告3","http://img.ivsky.com/img/tupian/pre/201707/05/kekou_de_tiantianquan-019.jpg"));
        bannerList.add(new Banner("广告2","http://img.sucai.redocn.com/attachments/images/201208/20120822/Redocn_2012080618372383.jpg"));
        bannerList.add(new Banner("广告1","http://p2.so.qhmsg.com/t01154029b7a35cf927.jpg"));
        return bannerList;
    }


}
