package com.walke.huimvp.mine.models.bean;

/**
 * Created by walke.Z on 2018/8/6.
 *
 * Model层：模型层；个人理解：应该包含当不限于数据模型(JavaBean)，还可以包括网络模块和数据库模块等
 *          辅助presenter完成具体业务逻辑。不与View直接关联，即有效隔离、解耦。
 *
 * 数据模型；
 *
 */

public class UserInfo {

    private String name;
    private String password;
    private int age;

    public UserInfo() {
    }

    public UserInfo(String name, String password, int age) {
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
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
