package com.walker.usb.USBSerial.util;

import android.util.Log;

import java.util.Locale;

/**
 * @author walker
 * @date 2023/4/12
 * @description 日志工具类
 */
public class LogUtil {
    public static final String TAG = "het_1.0.0";
    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4; // 只 log ERROR 的
    public static final int NONE = 5;
    public static int mLevel = VERBOSE;

    public static void init(int level) {
        mLevel = level;
    }

    public static void e(String msg) {
        if (mLevel <= ERROR) {
            String method = getClassMethod();
            Log.e(TAG, buildMessage(method, msg));
        }
    }

    public static void w(String msg) {
        if (mLevel <= WARN) {
            String method = getClassMethod();
            Log.w(TAG, buildMessage(method, msg));
        }
    }

    public static void i(String msg) {
        if (mLevel <= INFO) {
            String method = getClassMethod();
            Log.i(TAG, buildMessage(method, msg));
        }
    }

    public static void d(String msg) {
        if (mLevel <= DEBUG) {
            String method = getClassMethod();
            Log.d(TAG, buildMessage(method, msg));
        }
    }

    public static void v(String msg) {
        String method = getClassMethod();
        Log.v(TAG, buildMessage(method, msg));
    }

    private static String buildMessage(String method, String msg) {
        return String.format(Locale.CHINA, "%s %s", method, msg);
    }

    public static void st() {
        try {
            String classMethod = getClassMethod();
            String stackTrace = Log.getStackTraceString(new Throwable());
            if (stackTrace.length() > 500) {
                int indexOf = stackTrace.indexOf("\n", 100);
                int indexEnd = stackTrace.indexOf("\n", 500);
                stackTrace = stackTrace.substring(indexOf, indexEnd);
            }
            Log.i(TAG, classMethod + stackTrace);
        } catch (Exception e) {
        }
    }

    public static void st(String content) {
        try {
            String classMethod = getClassMethod();
            String stackTrace = Log.getStackTraceString(new Throwable());
            if (stackTrace.length() > 350) {
                int indexOf = stackTrace.indexOf("\n", 100);
                int indexEnd = stackTrace.indexOf("\n", 350);
                stackTrace = stackTrace.substring(indexOf, indexEnd);
            }
            Log.i(TAG, classMethod + content + ", " + stackTrace);
        } catch (Exception e) {
            Log.e(TAG,  "st " + content);
        }
    }

    public static String getSt() {
        try {
            String stackTrace = Log.getStackTraceString(new Throwable());
            if (stackTrace.length() > 500) {
                int indexOf = stackTrace.indexOf("\n", 200);
                int indexEnd = stackTrace.indexOf("\n", 500);
                stackTrace = stackTrace.substring(indexOf, indexEnd);
            }
            return "  st  " + stackTrace;
        } catch (Exception e) {
            return "  st  null";
        }
    }

    private static String getClassMethod() {
        StackTraceElement caller = getCallerStackTraceElement();
        if (caller == null) return "";
        return generateTag(caller);
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

    private static String generateTag(StackTraceElement caller) {
        String tag = "(%s:%d)";
        String callerClazzName = caller.getFileName();
        tag = String.format(Locale.getDefault(), tag, callerClazzName, caller.getLineNumber());
        return tag;
    }
}
