package com.walker.mvvmlearn.net.model;

public class NetResponse<T> {
    String content;
    T data;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
