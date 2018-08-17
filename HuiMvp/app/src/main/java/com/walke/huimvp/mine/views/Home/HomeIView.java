package com.walke.huimvp.mine.views.Home;

import com.walke.huimvp.mine.base.BaseIView;

/**
 * Created by walke.Z on 2018/8/6.
 * 首页模拟加载首页数据,回调
 */

public interface HomeIView<D> extends BaseIView {

    void loadSuccess(D titles);

    void loadFail(Exception exc);

}
