package com.walker.mvvmlearn.net.model.bean;

import com.walker.mvvmlearn.net.retrofit2.IModel;
import com.walker.mvvmlearn.net.retrofit2.NetConfig;

import java.io.Serializable;

/**
 * Created on 2021/12/28 14:18
 *
 * @author Gong Youqiang
 */
public class BaseBean<T> implements Serializable, IModel {

    private int errorCode;
    private String errorMsg;
    private T data;
    /**
     * 是否成功获取到数据
     */
    public boolean isSuccess() {
        return getErrorCode() == NetConfig.SUCCESS_CODE;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
