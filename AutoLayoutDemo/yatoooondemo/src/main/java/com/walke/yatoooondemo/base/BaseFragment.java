package com.walke.yatoooondemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by walke.Z on 2018/4/13.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(rootLayoutId(), container, false);
        //拿到布局填充器返回的view后
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        return view;
    }

    protected abstract int rootLayoutId();


}
