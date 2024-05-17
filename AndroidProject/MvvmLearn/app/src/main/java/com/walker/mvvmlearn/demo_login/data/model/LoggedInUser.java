package com.walker.mvvmlearn.demo_login.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 * 用于捕获从LoginDepository检索到的已登录用户的
 * 用户信息的数据类
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}