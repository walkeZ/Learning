package com.walker.mvvmlearn.demo_login.data;

import com.walker.mvvmlearn.demo_login.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 * 用于处理带有登录凭据的身份验证并检索用户信息的类
 * 具体登录逻辑，在登录仓库中使用
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser); // Result.Success、Result.Error 是 Result<T>的子类
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}