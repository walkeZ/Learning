package com.hui.mvptest.banner.model;

import com.hui.mvptest.banner.model.bean.Banner;

import java.util.List;

/**
 * Created by walke.Z on 2017/8/11.
 */

public interface IBannerModel {
    List<Banner> loadBannerData(String url);
    List<Banner> loadBannerData2(String url);
}
