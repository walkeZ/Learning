package com.walker.mvvmlearn.net.model.bean;

import java.io.Serializable;

/**
 * Created on 2021/12/28 14:18
 *
 * @author Gong Youqiang
 */
public class BaseBean<T> implements Serializable {
    private static int SUCCESS_CODE = 0;//接口访问成功的errorCode
    private int errorCode;
    private String errorMsg;
    private T data;
    /**
     * 是否成功获取到数据
     */
    public boolean isSuccess() {
        return getErrorCode() == SUCCESS_CODE;
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
