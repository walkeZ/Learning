package com.walke.java_demo.bean;

import java.io.Serializable;

/**
 * author Walke - 2020/9/18 7:01 PM
 * email 1126648815@qq.ocm
 * dec
 * Mac中 快速选择生成get、set方法的快捷键：control + N
 */
public class VoBase implements Serializable {
    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
