package com.gdcaihui.luckycoin.android.config;

import android.Manifest;
import android.os.Environment;

/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2016/11/24.
 */
public class Contants {

    public static final String APP_LOCATION = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LuckyCoin/";
    public static final String JAVA_SCRIPT_INTERFACE = "com_gdcaihui_luckycoin_android";
    public final static int COUNT_DOWN_TIME=30;//60

    public static final int LOGIN_SUCCESS_CODE = 77;
    public static final int REGISTER_SUCCESS_CODE =76;
    public static final int INTENT_FROM_SCAN_CASH =75;

    public static final int PERMISSION_SDCARD_REQUEST_CODE = 74;//系统授权sd卡权限管理页面时的结果参数
    public static final int PERMISSION_CAMERA_REQUEST_CODE = 73;//系统授权摄像头管理页面时的结果参数

    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_TOKEN = "token";

    public static final String INTENT_JUMP = "jump";
    public static final String INTENT_TITLE = "title";

    public static final String PERSONAL_TO_LOGIN = "个人到登录";

    public static final String ERROR_NO_NET = "网络不给力";//没有网络\n请检查手机的网络状态
    public static final String ERROR_SEVER_NET = "与服务器连接异常\n请检查您手机的网络状态";

    public static final String INTENT_URL = "url";
    public static final String FIRST_OPEN = "firstOpen";
    public static final String HOME_INFO = "homeinfo";

    public static final String WEBVIEW_COOKIE = "cookie";
    public static final String IS_FIRST_VERSION_ERROR = "isFirstVersionError";//是否是第一次进来检测
    public static final String LAST_LONGIN_NAME = "lastLoginName";

    public static final String ERROR_SERVICE_BACK = "服务器返回异常";
    public static final String ERROR_SERVICE = "服务器异常";
    public static final String ERROR_SERVICE_BACK_DATA = "返回数据异常";
    public static final String ERROR_SERVICE_BACK_DATA_CAST = "返回数据类型转换异常";
    public static final String VERSION_NAME = "versionName";
    public static final String WEBVIEW_SHARE_HIDE = "webview_share_hide";
    public static final String INTENT_SCAN_RESULT = "intent_scan_result";
    public static final String CHANGE_HEADER_BY_PHOTOS = "change_header_by_photos";
    public static final String CHANGE_HEADER_BY_CAMERA = "change_header_by_camera";

    /**
     * 配置SD卡需要的权限集
     */
    public static final String[] PERMISSION_SDCARD = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 配置摄像头需要的权限集
     */
    public static final String[] PERMISSION_CAMERA = new String[]{Manifest.permission.CAMERA};
    /**
     * 配置app需要的权限集
     */
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    /**
     * 配置我们自己的网址(用于识别)
     * //https://luckycoin.guaguayi.com
     */
    public static final String[] URL_US = {
//            "http://luckycoin.gdcaihui.com",
//            "https://luckycoin.gdcaihui.com",
//            "http://luckycoin.guaguayi.com",
//            "https://luckycoin.guaguayi.com",
            Request.URL_BASE,
            Request.HTTP_URL_BASE,

    };

    /**
     * 配置白名单
     * //https://luckycoin.guaguayi.com
     */
    public static final String[] URL_TRUST = {
            "https://www.gdlottery.cn/odata/weixin_kjhm.jspx",
            "http://www.gdtcps.cn",
            "https://www.gdtcps.cn",
            "http://mp.weixin.qq.com",
            "https://mp.weixin.qq.com",
            "http://luckycoin.gdcaihui.com",
            "https://luckycoin.gdcaihui.com",
            "http://luckycoin.guaguayi.com",
            "https://luckycoin.guaguayi.com",
            "www.gdlottery.cn",
            "https://hm.baidu.com/",
            Request.URL_BASE,
            Request.HTTP_URL_BASE,

    };


    public static final String PRODUCT_PLACEMENT = "product_placement";
    public static final String EVENT_TEST = "event_test";
    public static String FILE_PROVIDER_PATHS="LuckyCoin";



}
