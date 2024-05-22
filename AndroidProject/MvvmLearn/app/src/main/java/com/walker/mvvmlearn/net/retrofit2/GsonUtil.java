package com.walker.mvvmlearn.net.retrofit2;


import com.google.gson.Gson;

public class GsonUtil {
    public static String toStr(Object o) {
        return new Gson().toJson(o);
    }
}
