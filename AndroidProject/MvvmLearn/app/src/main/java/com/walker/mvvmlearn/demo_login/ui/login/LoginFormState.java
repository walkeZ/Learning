package com.walker.mvvmlearn.demo_login.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 *
 * 登录表单的数据验证状态。即用户输入界面的条件判断
 */
class LoginFormState {
    @Nullable
    private Integer usernameError; // 用户名有误
    @Nullable
    private Integer passwordError; // 密码有误
    private boolean isDataValid; // 数据是否可用

    LoginFormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}