package com.walker.mvvmlearn.demo_login.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 * 向UI公开经过身份验证的用户详细信息的类
 */
class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}