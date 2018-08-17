package com.walke.huimvp.mine.presenters;

import com.walke.huimvp.mine.base.BasePresenter;
import com.walke.huimvp.mine.models.HomeModel;
import com.walke.huimvp.mine.models.net.HomeIModel;
import com.walke.huimvp.mine.views.Home.HomeIView;

import java.util.List;

/**
 * Created by walke.Z on 2018/8/6.
 */

public class HomePresenter extends BasePresenter<HomeIView<List<String>>> {

    private HomeModel mHomeModel;

    public HomePresenter(HomeIView iView) {
        super(iView);
        mHomeModel=new HomeModel();

    }

    public void loadHomeData(){

        mHomeModel.loadData("1002", new HomeIModel<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                HomeIView ivIew = getIVIew();
                if (ivIew!=null)
                    ivIew.loadSuccess(data);
            }

            @Override
            public void onFail(Exception exc) {
                if (getIVIew()!=null)
                    getIVIew().loadFail(new Exception("数据加载失败"));
            }
        });
    }

}
