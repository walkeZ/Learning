package com.gdcaihui.luckycoin.android.config;

/**
 * Created by Xiongrui on 2016/12/14.
 */
public class Request {
    //  局域网内的测试地址：http://192.168.1.223:47080/
    //  http://luckycoin.gdcaihui.com:58081/
    // https://luckycoin.guaguayi.com
    /**
     * 接口url地址 IP
     */
//    public final static String URL_DOMAIN = "192.168.1.223:47080";
//    public final static String URL_DOMAIN = "luckycoin.gdcaihui.com";
//    public final static String URL_DOMAIN = "luckycoin.guaguayi.com";// --https
    public final static String URL_DOMAIN = "192.168.1.91:8080";//91 59

    /**
     * http请求路径
     */
//    public final static String URL_BASE = "https://" + URL_DOMAIN;
    public final static String URL_BASE = "http://" + URL_DOMAIN;

       /**
     * https请求路径
     */
    public final static String HTTP_URL_BASE = "http://" + URL_DOMAIN ;

    /**
     * 接口请求地址_http
     */
    public final static String API_URL = URL_BASE + "/api/client.php";

    /**
     * 接口请求地址_https
     */
//    public final static String API_HTTP_URL = HTTP_URL_BASE + "/api/client.php";
    /**
     * 获取图片验证码url地址
     */
    public final static String VALIDATE_CODE_IMAGE_URL = URL_BASE + "/api/client/validatecode.php";

    /**
     * 忘记密码链接url地址
     */
    public final static String FORGOT_PASSWORD_URL = URL_BASE + "/mobile/forget/index.shtml";
    /**
     * 忘记支付密码链接url地址
     */
    public final static String FORGOT_PAY_PASSWORD_URL = URL_BASE + "/mobile/forget/index.shtml?type=2";
    /**
     * 资金记录链接url地址
     */
    public final static String FUNDS_RECORD_URL = URL_BASE + "/mobile/record/index.php?id=";
    /**
     * 中奖后查看明细链接url地址
     */
    public final static String FUNDS_DETAILS_URL = URL_BASE + "/mobile/record/reward.php?ticket=";
    /**
     * 提款历史链接url地址
     */
    public final static String WITHDRAWALS_HISTORY_URL = URL_BASE + "/mobile/record/withdraw/list.php";
    /**
     * 提款详情链接url地址
     */
    public final static String WITHDRAWALS_DETAILS_URL = URL_BASE + "/mobile/record/withdraw/detail.php?id=";

    /**
     * 关于我们链接url地址
     */
    public final static String ABOUT_LC_URL = URL_BASE + "/mobile/about.html";
    //http://luckycoin.gdcaihui.com/mobile/about_yangjiang.html
    public final static String ABOUT_LC_URL_YANG_JIANG = URL_BASE + "/mobile/about_yangjiang.html";

    /**
     * 世界排名
     */
    public final static String WORLD_RANKING = URL_BASE + "/mobile/index.php";
    /**
     * 最新活动
     */
    public final static String NEW_ACTIVE = URL_BASE + "/mobile/news/";
    /**
     * 我的幸运积分： /mobile/record/index.php
     */
    public final static String MY_LUCKY_COIN = URL_BASE + "/mobile/record/index.php";
    /**
     * 协议
     */
    public final static String LUCKY_COIN_AGREEMENT = URL_BASE + "/mobile/agreement.html";
    //http://luckycoin.gdcaihui.com/mobile/agreement_yangjiang.html
    public final static String LUCKY_COIN_AGREEMENT_YANG_JIANG = URL_BASE + "/mobile/agreement_yangjiang.html";
    /**
     * 兑换规则
     */
    public final static String CASH_RULE = URL_BASE + "/mobile/rule.html";
    /**
     * 收获地址
     */
    public final static String ADDRESS = URL_BASE + "/mobile/address/";

    /**
     * 软件声明
     */
    public final static String APP_STATEMENT = URL_BASE + "/mobile/declaration.shtml";
    //http://luckycoin.gdcaihui.com/mobile/declaration_yangjiang.shtml
    public final static String APP_STATEMENT_YANG_JIANG = URL_BASE + "/mobile/declaration_yangjiang.shtml";


    /**
     * 手工输入
     */
    public final static String HAND_INPUT = URL_BASE + "/mobile/exchange/index.php";

}
