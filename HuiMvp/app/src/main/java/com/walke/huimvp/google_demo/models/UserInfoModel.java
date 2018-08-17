package com.walke.huimvp.google_demo.models;

/**
 * Created by walke.Z on 2018/8/6.
 */

public class UserInfoModel {

    private String name;
    private String password;
    private int age;

    public UserInfoModel() {
    }

    public UserInfoModel(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
