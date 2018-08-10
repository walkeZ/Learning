package com.walke.demo.main.presenter;

import com.walke.demo.BasePresenter;
import com.walke.demo.main.model.DataCallBack;
import com.walke.demo.main.model.MainModel;
import com.walke.demo.main.views.MainIView;

import java.util.List;

/**
 * Created by walke.Z on 2018/8/10.
 */

public class MainPresenter extends BasePresenter<MainIView> {

    MainModel mMainModel;

    public MainPresenter() {
        mMainModel = new MainModel();
    }

    /**
     * @param api
     */
    public void getMainData(String api){

        if (getIView()!=null)
            getIView().showLoading();

        mMainModel.loadData(api, new DataCallBack<List<String>>() {

            @Override
            public void onSuccess(List<String> data) {
                if (getIView()!=null) {
                    getIView().getDataSuccess(data);
                    getIView().hideLoading();
                }
            }

            @Override
            public void onFail(Exception exc) {
                if (getIView()!=null) {
                    getIView().getDataFail(exc);
                    getIView().hideLoading();
                }
            }
        });

    }

}
