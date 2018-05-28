package com.walke.realm.entity;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by walke.Z on 2018/5/28.
 * 参考：https://www.jianshu.com/p/28912c2f31db
 * Teacher： 商品
 */

public class Teacher extends RealmObject implements Serializable {//课程：course，age :分数
    private String name;
    private int age;

    @PrimaryKey
    private int id;

    public Teacher() {
    }

    public Teacher(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
