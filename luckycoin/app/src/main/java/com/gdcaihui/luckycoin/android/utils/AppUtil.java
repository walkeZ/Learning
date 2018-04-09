package com.gdcaihui.luckycoin.android.utils;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.webkit.WebView;

import com.gdcaihui.luckycoin.android.config.Contants;
import com.gdcaihui.luckycoin.android.entity.vo.MemberInfo;
import com.gdcaihui.luckycoin.android.entity.vo.VersionInfo;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import walke.baselibrary.activity.BaseActivity;
import walke.baselibrary.tools.MD5Util;
import walke.baselibrary.tools.SharePreferenUtil;

public class AppUtil {


    public static boolean isYangJiang(Context context){
        if (context.getPackageName().isEmpty())
            return false;
        return context.getPackageName().endsWith("yangjiang");
    }

    public static boolean isLogined(MemberInfo memberInfo){
        boolean b = memberInfo != null && memberInfo.getQualifying() != null;
        return b;
    }

    /**
     * 文本替换
     *
     * @param oldText
     * @param newText
     * @return
     */
    public static String textReplace(String oldText, String newText) {
        if (TextUtils.isEmpty(oldText))
            return newText;
        else
            return oldText;
    }

    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }




    public static void saveCookie(Context context,OkHttpClient httpClient, HttpUrl httpUrl) {
        List<Cookie> cookies = httpClient.cookieJar().loadForRequest(httpUrl);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String cookieStr = cookie.toString();
            if (!TextUtils.isEmpty(cookieStr)) {
                sb.append(cookieStr + "=");//最后的“=”不能少
            }
        }
        SharePreferenUtil.putString(context, Contants.WEBVIEW_COOKIE, sb.toString());
    }

    /**
     * @return 当前版本名
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String version = "";
        try {
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (Exception e) {
        }
        return version;
    }

    public static void loadApk(Context context, VersionInfo versionInfo) {
        if (versionInfo==null) return;
        String md5Encode = MD5Util.MD5Encode(versionInfo.getUpdateDownloadUrl());
//        String savePath = Contants.APP_LOCATION + md5Encode + ".apk";
        // Android7.0适配 3
        String savePath = (context.getExternalFilesDir(Contants.FILE_PROVIDER_PATHS) + File.separator +md5Encode+ ".apk");
        LoadDialog.loadingApkDialog(context, versionInfo, savePath);
        //LoadDialog.apkLoading(context,versionInfo,savePath);
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }


    public interface OnInstallListener {
        void onInstallFuncation(Context context, File file);
    }

    public static boolean isUsUrl(String url){
        boolean isUsUrl = false;
        for (int i = 0; i < Contants.URL_US.length; i++) {
            isUsUrl = url.startsWith(Contants.URL_US[i]);
            if (isUsUrl)
                break;
        }
        return isUsUrl;
    }

    //
    public static boolean isTrustUrl(String url){
        boolean isUsUrl = false;
        for (int i = 0; i < Contants.URL_TRUST.length; i++) {
            isUsUrl = url.contains(Contants.URL_TRUST[i]);
            if (isUsUrl)
                break;
        }
        return isUsUrl;
    }


    /** 网址重定向
     * @param view
     * @param url
     */
    public static void overriedUrl(BaseActivity activity, WebView view, String url) {
        String simpleName = activity.getClass().getSimpleName();
        activity.logI(simpleName);
        if (AppUtil.isUsUrl(url)) {
            if (url.contains("weixin:")) {
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // TODO: handle exception
                    activity.toast("检查到您手机没有安装微信，请安装后使用该功能");
                }
            } else if (url.contains("tel:")) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            } else if (url.contains("mailto:")) {
                Uri uri = Uri.parse(url);
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(it);
            } else {
                view.loadUrl(url);
            }
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            activity.startActivity(intent);
            activity.logI("shouldOverrideUrlLoading--去系统浏览器");
        }
    }




    /**  参考链接：http://www.jb51.net/article/113307.htm
     *  Android7.0适配 4  安装 apk 文件  注意apk文件下载后保存路径
     * String cachePath = (getExternalFilesDir("LC") + File.separator + "abab141acb.apk");//成功
     * String cachePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"AC"+ File.separator + "abab141acb.apk";//失敗
     * @param apkFile
     */
    public static void installApk(Context context,File apkFile) {
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            String authority = context.getPackageName() + ".file_provider";
            Uri uriForFile = FileProvider.getUriForFile(context, authority, apkFile);
            installApkIntent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
//            installApkIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }

        if (context.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            context.startActivity(installApkIntent);
        }
    }



}
