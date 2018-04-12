package com.gdcaihui.luckycoin.android.okhttp.builder;


import com.gdcaihui.luckycoin.android.okhttp.request.PostStringRequest;
import com.gdcaihui.luckycoin.android.okhttp.request.RequestCall;

import okhttp3.MediaType;

/**
 * Created by View on 2016 11/14
 */
public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder>
{
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, params, headers, content, mediaType,id).build();
    }


}
