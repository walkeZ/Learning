package com.walker.mvvmlearn.usbSdk;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.walker.mvvmlearn.BuildConfig;
import com.walker.mvvmlearn.R;
import com.walker.usb.USBSerial.util.LogUtil;

import java.util.UUID;

/**
 * @author walker
 * @date 2024/5/27
 * @description 系统工具类：
 */
public class SystemUtil {


    /**
     * 获取设备制造商名称.
     *
     * @return 设备制造商名称
     */
    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备名称.
     *
     * @return 设备名称
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * 获取产品名称.
     *
     * @return 产品名称
     */
    public static String getProductName() {
        return Build.PRODUCT;
    }

    /**
     * 获取品牌名称.
     *
     * @return 品牌名称
     */
    public static String getBrandName() {
        return Build.BRAND;
    }

    /**
     * 获取操作系统版本号.
     *
     * @return 操作系统版本号
     */
    public static int getOSVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取操作系统版本名.
     *
     * @return 操作系统版本名
     */
    public static String getOSVersionName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取操作系统版本显示名.
     *
     * @return 操作系统版本显示名
     */
    public static String getOSVersionDisplayName() {
        return Build.DISPLAY;
    }

    /**
     * 获取主机地址.
     *
     * @return 主机地址
     */
    public static String getHost() {
        return Build.HOST;
    }

    /**
     * 唯一识别码ID
     * @param context
     * @return
     */
    public static String getDeviceId(final Context context){


        String tmDevice = "";
        String tmSerial = "";
        String deviceid = "";
        try{
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= 23){
                int permissionCheck = ContextCompat.checkSelfPermission(context,
                        Manifest.permission.READ_PHONE_STATE);
                if (permissionCheck!=-1){
                    // tmDevice = "" + tm.getDeviceId();
                    tmSerial = "" + tm.getSimSerialNumber();
                }
            }else {
                tmDevice = "" + tm.getDeviceId();
                tmSerial = "" + tm.getSimSerialNumber();
            }
            String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
            deviceid = deviceUuid.toString();
        } catch (Exception ex){
            LogUtil.e("get imei error" + ex.toString());
        }

        return deviceid;
    }

    /**
     * 获取渠道，用于打包，by weatherfish
     *
     * @param context
     * @param metaName
     * @return
     */
    public static String getAppSource(Context context, String metaName) {
        String result = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData == null)
                return "Android";
            result = appInfo.metaData.getString(metaName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static String getNetType(final Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
//		networkInfo.getDetailedState();//获取详细状态。
//		networkInfo.getExtraInfo();//获取附加信息。
//		networkInfo.getReason();//获取连接失败的原因。
//		networkInfo.getType();//获取网络类型(一般为移动或Wi-Fi)。
//		networkInfo.getTypeName();//获取网络类型名称(一般取值“WIFI”或“MOBILE”)。
//		networkInfo.isAvailable();//判断该网络是否可用。
//		networkInfo.isConnected();//判断是否已经连接。
//		networkInfo.isConnectedOrConnecting();//：判断是否已经连接或正在连接。
//		networkInfo.isFailover();//：判断是否连接失败。
//		networkInfo.isRoaming();//：判断是否漫游
        return networkInfo == null ? "" : networkInfo.getTypeName();
    }

    /**
     * 国际化
     * @param context
     * @return
     */
    public static String getSysInternationalization(Context context) {
        Resources resources = context.getResources();// 获得res资源对象
        Configuration config = resources.getConfiguration();// 获得设置对象
        return config.locale.toString();
    }

    /**
     * 版本是19之后，Android 4.4(api 19) +
     */
    public static boolean android4_4Later() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
    }

    /**
     * 版本是Android 5.0(api 21)+
     */
    public static boolean android5Later() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    /**
     * 版本是，Android6.0 (api 23)+
     */
    public static boolean android6Later() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    /**
     * 版本是Android 10(api 29)+
     */
    public static boolean android10Later() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q);
    }

    /**
     * 版本是Android 11(api 30)+
     */
    public static boolean android11Later() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R);
    }

    /**
     * 版本是Android 12(api 31)+
     */
    public static boolean android12Later() {
        return (Build.VERSION.SDK_INT >= 31);
    }

    /**
     * @param activity
     */
    @NonNull
    public static void setNavigationBar(Activity activity) {
        if (android4_4Later()) {
            // 透明导航栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public static String getUserAgent(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static String getPhoneInfo(Context context) {
        StringBuffer userAgent = new StringBuffer();
        // ==============================================================
//		User-Agent
//		格式：
//		应用名称;应用版本;平台;OS版本;OS版本名称;厂商;机型;分辨率(宽*高);安装渠道;网络;
//		示例：
//		HET;2.2.0;Android;4.2.2;N7100XXUEMI6BYTuifei;samsung;GT-I9300;480*800;360;WIFI;
        userAgent.append(context.getString(R.string.app_name));// 应用名称
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(BuildConfig.VERSION_NAME); // App版本
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(CommonConsts.SourceType);// 平台
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getOSVersionName()); // OS版本
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getOSVersionDisplayName()); // OS显示版本
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getBrandName()); // 品牌厂商
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getModelName()); // 机型
        userAgent.append(CommonConsts.SEMICOLON);
//        userAgent.append(DensityUtils.getPhoneSize(context)); // 分辨率
//        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getDeviceId(context)); // 设备唯一标识IMEI
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getAppSource(context, CommonConsts.APP_SOURCE)); // 渠道标识
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getNetType(context)); // 网络类型
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(BuildConfig.APPLICATION_ID); // 包名
        userAgent.append(CommonConsts.SEMICOLON);
        userAgent.append(getSysInternationalization(context)); // 国际化
        userAgent.append(CommonConsts.SEMICOLON);

        String userAgents = userAgent.toString();
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgents.length(); i < length; i++) {
            char c = userAgents.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public final class CommonConsts {

        /**
         * 设备分类
         */
        public static final String SourceType = "Android";

        /**
         * 空格字符�?
         */
        public static final String SPACE = " ";

        /**
         * 逗号.
         */
        public static final String COMMA = ",";

        /**
         * 句点.
         */
        public static final String PERIOD = ".";

        /**
         * 左引�?
         */
        public static final String LEFT_QUOTES = "'";

        /**
         * 右引�?
         */
        public static final String RIGHT_QUOTES = "'";

        /**
         * 左圆括号.
         */
        public static final String LEFT_PARENTHESIS = "(";

        /**
         * 右圆括号.
         */
        public static final String RIGHT_PARENTHESIS = ")";

        /**
         * 左方括号.
         */
        public static final String LEFT_SQUARE_BRACKET = "[";

        /**
         * 右方括号.
         */
        public static final String RIGHT_SQUARE_BRACKET = "]";

        /**
         * 换行�?
         */
        public static final String LINE_BREAK = "\r\n";

        /**
         * 换行�?
         */
        public static final String LINE_BREAK_SHORT = "\n";


        /**
         * 问号.
         */
        public static final String QUESTION_MARK = "?";

        /**
         * 符号&.
         */
        public static final String AMPERSAND = "&";

        /**
         * 等于�?
         */
        public static final String EQUAL = "=";

        /**
         * 分号.
         */
        public static final String SEMICOLON = ";";
        /**
         * App渠道对应的meta名字
         */
        public static final String APP_SOURCE = "HET_CHANNEL";

        /**
         * 私有的构造方�?
         */
        private CommonConsts() {
        }
    }

}
