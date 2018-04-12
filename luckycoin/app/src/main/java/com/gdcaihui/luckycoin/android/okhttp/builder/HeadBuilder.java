package com.gdcaihui.luckycoin.android.okhttp.builder;


import com.gdcaihui.luckycoin.android.okhttp.OkHttpUtils;
import com.gdcaihui.luckycoin.android.okhttp.request.OtherRequest;
import com.gdcaihui.luckycoin.android.okhttp.request.RequestCall;

/**
 * Created by View on 2016 11/14
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
