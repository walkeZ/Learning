package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walker.mvvmlearn.net.model.NetResponse;
import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;

import java.util.List;

public class NRViewModel extends ViewModel {

    public MutableLiveData<NetResponse<String>> response = new MutableLiveData<>();
    public MutableLiveData<String> responseStr = new MutableLiveData<>("hello");
    public MutableLiveData<BaseBean<List<BannerBean>>> bannerBean = new MutableLiveData<>();
}
