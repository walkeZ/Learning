package com.walker.mvvmlearn.net.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walker.mvvmlearn.net.model.NetResponse;

public class NRViewModel extends ViewModel {

    public MutableLiveData<NetResponse<String>> response = new MutableLiveData<>();
    public MutableLiveData<String> responseStr = new MutableLiveData<>("hello");

}
