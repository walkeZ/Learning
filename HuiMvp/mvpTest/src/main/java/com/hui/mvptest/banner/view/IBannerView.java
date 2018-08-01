package com.hui.mvptest.banner.view;

import com.hui.mvptest.banner.view.widget.BannerAdapter;
import com.hui.mvptest.banner.view.widget.BannerAdapter1;
import com.hui.mvptest.banner.view.widget.BannerAdapter2;

/**
 * Created by walke.Z on 2017/8/11.
 */

public interface IBannerView {

    void setAdapter1(BannerAdapter1 adapter);
    void setAdapter2(BannerAdapter2 adapter);
    void setAdapter(BannerAdapter adapter);
    void startScroll(int startPosition,int xtime);

}
