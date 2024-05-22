package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walker.mvvmlearn.net.model.NetResponse;
import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.NetRequest;
import com.walker.mvvmlearn.net.retrofit2.base.BaseHttpObserver;
import com.walker.mvvmlearn.net.retrofit2.base.vo.VoBase;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NRViewModel extends ViewModel {

    public MutableLiveData<NetResponse<String>> response = new MutableLiveData<>();
    public MutableLiveData<String> responseStr = new MutableLiveData<>("hello");
    public MutableLiveData<BaseBean<List<BannerBean>>> bannerBean = new MutableLiveData<>();

    public MutableLiveData<BaseBean<List<BannerBean>>> bannerData = getBanner();

    public MutableLiveData<BaseBean<List<BannerBean>>> getBanner() {
        return new BannerRepository().getBanner();
    }

    public MutableLiveData<VoBase<List<BannerBean>>> myBanner = getMyBanner();

    private MutableLiveData<VoBase<List<BannerBean>>> getMyBanner() {
        BaseHttpObserver<VoBase<List<BannerBean>>> voBaseBaseHttpObserver = new BaseHttpObserver<>();
        NetRequest.createApi().getMyBanner()
                .subscribeOn(Schedulers.io()) // io线程提交
                .observeOn(AndroidSchedulers.mainThread()) // Android main线程订阅
                .subscribe(voBaseBaseHttpObserver);
        return voBaseBaseHttpObserver.getData();
    }
}
