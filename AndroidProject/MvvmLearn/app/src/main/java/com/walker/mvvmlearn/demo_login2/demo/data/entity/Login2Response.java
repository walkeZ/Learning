package com.walker.mvvmlearn.demo_login2.demo.data.entity;

import java.io.Serializable;

// 登录成功 所响应的Bean
public class Login2Response implements Serializable {
    private int code;
    private String msg;
    private String userName;

    public Login2Response() {
    }

    public Login2Response(int code, String msg, String userName) {
        this.code = code;
        this.msg = msg;
        this.userName = userName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
