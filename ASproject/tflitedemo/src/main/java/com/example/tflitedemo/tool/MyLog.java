package com.example.tflitedemo.tool;

import android.util.Log;

import java.util.Locale;

/**
 * @author walker
 * @date 2023/1/6
 * @description 项目的Log工具
 */
public class MyLog {
    private static final String TAG = "MyLog";

    public static void v(String content) {
        Log.v(TAG, getClassMethod() + content);
    }

    public static void v(String uTag, String content) {
        Log.v(uTag + getClassMethod(), content);
    }

    public static void d(String content) {
        String classMethod = getClassMethod();
        Log.d(TAG, classMethod + content);
    }

    public static void d(String uTag, String content) {
        String uTag1 = uTag + getClassMethod();
        Log.d(uTag1, content);
    }

    public static void i(String content) {
        String classMethod = getClassMethod();
        Log.i(TAG, classMethod + content);
    }

    public static void i(String uTag, String content) {
        String uTag1 = uTag + getClassMethod();
        Log.i(uTag1, content);
    }

    public static void w(String content) {
        String classMethod = getClassMethod();
        Log.w(TAG, classMethod + content);
    }

    public static void w(String uTag, String content) {
        String uTag1 = uTag + getClassMethod();
        Log.w(uTag1, content);
    }

    public static void e(String content) {
        String classMethod = getClassMethod();
        Log.e(TAG, classMethod + content);
    }

    public static void e(String uTag, String content) {
        String uTag1 = uTag + getClassMethod();
        Log.e(uTag1, content);
    }

    private static String getClassMethod() {
        StackTraceElement caller = getCallerStackTraceElement();
//        String filename = "";
//        String funName = "";
//        int line = 0;
//        if (caller != null) {
//            filename = caller.getFileName();
//            funName = caller.getMethodName();
//            line = caller.getLineNumber();
//        }
//        return filename + " " + funName + " " + line + " ";
        return generateTag(caller);
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "(%s:%d).%s";
        String callerClazzName = caller == null ? "" : caller.getFileName();
        tag = String.format(Locale.getDefault(), tag, callerClazzName, caller == null ? 0 : caller.getLineNumber(), caller == null ? "" : caller.getMethodName());
        return tag + " ";
    }

    private static StackTraceElement getCallerStackTraceElement() {
        Thread cThread = Thread.currentThread();
        if (cThread != null) {
            StackTraceElement[] stackTrace = cThread.getStackTrace();
            if (stackTrace != null && stackTrace.length >= 6) {
                return stackTrace[5];
            }
        }
        return null;
    }
}
