package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walker.mvvmlearn.net.model.NetResponse;
import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.base.BaseRepository;

import java.util.List;

import io.reactivex.Observable;

public class NRViewModel extends ViewModel {

    public MutableLiveData<NetResponse<String>> response = new MutableLiveData<>();
    public MutableLiveData<String> responseStr = new MutableLiveData<>("hello");
    public MutableLiveData<BaseBean<List<BannerBean>>> bannerBean = new MutableLiveData<>();

    public MutableLiveData<BaseBean<List<BannerBean>>> bannerData = getBanner();

    public MutableLiveData<BaseBean<List<BannerBean>>> getBanner() {
        return new LoginRepository().getBanner();
    }
}
