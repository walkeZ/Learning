package com.walke.realm.entity;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by walke.Z on 2018/5/24.
 *
 * 一个java对象占多少个字节？-- https://blog.csdn.net/qlmmys/article/details/53213857
 *
 * Error:Execution failed for task ':realm:compileDebugJavaWithJavac'.>
 * Compilation failed; see the compiler error output for details.
 * 原因：使用Realm数据库框架时javaBean类去少一个没有参数的构造方法
 *
 */

public class Student extends RealmObject implements Serializable{
    @PrimaryKey //主键，不可改
    private String id;
    private String name;
    private int age;
    private boolean sex;//因为Boolean 类型占的空间小，boolean 1位、byte 1字节8位、short/char 2字节16位、int/float 4字节32位、 long/double 8字节64位。
    private String city;//String可称为引用类型，在32位系统上占4字节32位，在64为系统上占8字节64位

    public Student() {
    }

    public Student(String id, String name, int age, boolean sex, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                '}';
    }
}
