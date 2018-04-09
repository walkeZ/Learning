package com.gdcaihui.luckycoin.android.config;

/**
 * Created by Xiongrui on 2016/12/14.
 */
public class Api {
    /**
     * api版本
     */
    public final static String VERSION = "1.0";

    /**
     * 安卓客户端KEY
     */
    public final static String KEY = "luckycoin_for_android";

    /**
     * 客户端对应密钥
     */
    public final static String SECRET_KEY = "9281CB02B4797CB310B17067FDD27D57";

    /**
     * 加盐码-客户端
     */
    public final static String CLIENT_SALT = "GD_CAIHUI_LUCKY_COIN_CLIENT";

    /**
     * 参数：key
     */
    public final static String PARAMS_KEY = "key";

    /**
     * 参数：消息体
     */
    public final static String PARAMS_MESSAGE = "message";

    /**
     * 成功
     */
    public final static int OK = 1;

    /**
     * 失败
     */
    public final static int ERROR = 0;

    /**
     * 版本异常
     */
    public final static int VERSION_ERROR = 3333;

    /**
     * 未登录
     */
    public final static int NO_LOGIN = 5555;

    /**
     * 余额不足
     */
    public final static int INSUFFICIENT_BALANCE = 7777;

    // 接口指令 ============================ begin ============================
    /**
     * 首页信息
     */
    public final static String HOME = "4000";
    /**
     * 登录指令
     */
    public final static String LOGIN = "4001";

    /**
     * 注册指令
     */
    public final static String REGISTER = "4002";

    /**
     * 发送验证码指令  （手机）
     */
    public final static String VALIDATE_CODE = "4003";
    /**
     * 会员信息
     */
    public final static String MEMBER = "4004";

    /**
     * 修改密码指令
     */
    public final static String PASSWORD = "4005";

    /**
     * 绑定手机号码
     */
    public final static String BIND_MOBILE = "4006";

    /**
     * 退出登录指令
     */
    public final static String EXIT = "4007";

    /**
     * 资讯
     */
    public final static String APP_NEWS = "4008";

    /**
     * 版本检测
     */
    public final static String VERSION_CHECK = "4009";

    /**
     * 扫码获取积分
     */
    public static final String REWARD = "4010";


    // 接口指令 ============================ end ============================
}
