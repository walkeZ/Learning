package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walker.mvvmlearn.net.model.NetResponse;
import com.walker.mvvmlearn.net.model.bean.BannerBean;
import com.walker.mvvmlearn.net.model.bean.BaseBean;
import com.walker.mvvmlearn.net.retrofit2.NetRequest;
import com.walker.mvvmlearn.net.retrofit2.base.BaseHttpObserver;
import com.walker.mvvmlearn.net.retrofit2.base.BaseHttpSubscriber2;
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

    // DataBinding下，在xml中使用后会自动调用一次，直接在xml中使用方法名(对象成员)会导致调用多次
    public MutableLiveData<VoBase<List<BannerBean>>> myBanner = getMyBanner();

    private MutableLiveData<VoBase<List<BannerBean>>> getMyBanner() {
        BaseHttpObserver<VoBase<List<BannerBean>>> voBaseBaseHttpObserver = new BaseHttpObserver<>();
        NetRequest.createApi().getMyBanner()
                .subscribeOn(Schedulers.io()) // io线程提交
                .observeOn(AndroidSchedulers.mainThread()) // Android main线程订阅
                .subscribe(voBaseBaseHttpObserver);
        return voBaseBaseHttpObserver.getData();
    }

    // 返回的数据是json格式是对象，不能转成String
    // reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String
    public MutableLiveData<String> myBannerObject2 = getMyBannerObject();

    //
    public MutableLiveData<String> getMyBannerObject() {
        //  Flowable return type must be parameterized as Flowable<Foo> or Flowable<? extends Foo>
        // Flowable return type must be parameterized as Flowable<Foo> or Flowable<? extends Foo>   原因是接口定义是没有
        BaseHttpSubscriber2<String> voBaseBaseHttpObserver = new BaseHttpSubscriber2<>();
        NetRequest.createApi().getBannerObject()
                .subscribeOn(Schedulers.io()) // io线程提交
                .observeOn(AndroidSchedulers.mainThread()) // Android main线程订阅
                .subscribe(voBaseBaseHttpObserver);
        return voBaseBaseHttpObserver.get();
    }
}
