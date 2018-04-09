package com.gdcaihui.luckycoin.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.gdcaihui.luckycoin.android.config.Contants;
import com.gdcaihui.luckycoin.android.entity.vo.HomeInfo;
import com.gdcaihui.luckycoin.android.entity.vo.MemberInfo;
import com.gdcaihui.luckycoin.android.entity.vo.VersionInfo;
import com.gdcaihui.luckycoin.android.okhttp.OkHttpUtils;
import com.gdcaihui.luckycoin.android.okhttp.cookie.CookieJarImpl;
import com.gdcaihui.luckycoin.android.okhttp.cookie.store.MemoryCookieStore;
import com.gdcaihui.luckycoin.android.okhttp.log.LoggerInterceptor;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import walke.baselibrary.tools.SharePreferenUtil;

/**
 * Created by View on 2016/11/14.
 */
public class LuckyCoinApplication extends Application {

    private static LuckyCoinApplication instance;
    private MemoryCookieStore mMemoryCookieStore;
    private Gson mGson;

    public MemoryCookieStore getMemoryCookieStore() {
        return mMemoryCookieStore;
    }

    public void setMemoryCookieStore(MemoryCookieStore memoryCookieStore) {
        this.mMemoryCookieStore = memoryCookieStore;
    }

    public synchronized static LuckyCoinApplication getInstance() {
        if (null == instance)
            instance = new LuckyCoinApplication();
        return instance;
    }

    private final HashMap<String, SoftReference<Activity>> taskMap = new HashMap<String, SoftReference<Activity>>();

    private OkHttpClient mOkHttpClient;

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    private MemberInfo memberInfo;

    public MemberInfo getMemberInfo() {
        if (this.memberInfo == null) {
            memberInfo = new MemberInfo();
            memberInfo.setAccount(SharePreferenUtil.getString(this, Contants.KEY_ACCOUNT));
            memberInfo.setToken(SharePreferenUtil.getString(this, Contants.KEY_TOKEN));
        }
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
        if (memberInfo == null) {
            SharePreferenUtil.putString(this, Contants.KEY_ACCOUNT, null);
            SharePreferenUtil.putString(this, Contants.KEY_TOKEN, null);
        } else {
            SharePreferenUtil.putString(this, Contants.KEY_ACCOUNT, memberInfo.getAccount());
            SharePreferenUtil.putString(this, Contants.KEY_TOKEN, memberInfo.getToken());
        }
    }

    private int identifyCodeTime;//记录验证码请求时间，(默认30秒内不能再次请求)

    public int getIdentifyCodeTime() {
        return identifyCodeTime;
    }

    public void setIdentifyCodeTime(int identifyCodeTime) {
        this.identifyCodeTime = identifyCodeTime;
    }

    private int modifyMobileTime;//记录修改手机号请求时间，(根据服务器返回时间XX秒内不能再次请求)

    public int getModifyMobileTime() {
        return modifyMobileTime;
    }

    public void setModifyMobileTime(int modifyMobileTime) {
        this.modifyMobileTime = modifyMobileTime;
    }

    private VersionInfo mVersionInfo;
    private Dialog mDialog;

    public VersionInfo getVersionInfo() {
        return mVersionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        mVersionInfo = versionInfo;
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setDialog(Dialog dialog) {
        mDialog = dialog;
    }

    private HomeInfo mHomeInfo;

    public HomeInfo getHomeInfo() {
        if (mHomeInfo==null){
            String home = SharePreferenUtil.getString(this, Contants.HOME_INFO);
            if (!TextUtils.isEmpty(home))
                mHomeInfo = mGson.fromJson(home, HomeInfo.class);
        }
        return mHomeInfo;
    }

    public void setHomeInfo(HomeInfo homeInfo) {
        mHomeInfo = homeInfo;
        String home = mGson.toJson(mHomeInfo);
        SharePreferenUtil.putString(this, Contants.HOME_INFO, home);
    }

    @Override
    public void onCreate() {
        super.onCreate();


        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5a25fc74a40fa3346400007d");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.setEncryptEnabled(false);
        mGson=new Gson();
        getHomeInfo();//(检测)初始首页缓存信息
        initCrashSetting();
        initHttpRequest2();
        SharePreferenUtil.putBoolean(this, Contants.FIRST_OPEN, true);
        SharePreferenUtil.putBoolean(this, Contants.IS_FIRST_VERSION_ERROR, true);
    }
    /**
     * OKHttp信任所有证书
     */
    private void initHttpRequest2() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }
                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
        // Install the all-trusting trust manager
        SSLContext sslContext=null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        mMemoryCookieStore = new MemoryCookieStore();
        CookieJarImpl cookieJar1 = new CookieJarImpl(mMemoryCookieStore);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslSocketFactory)
                .build();
        OkHttpUtils.initClient(mOkHttpClient);
    }

    private void initCrashSetting() {// 初始化Bugly
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        String processName = getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, "19219b44af", true, strategy);
    }

    /**
     * 获取进程号对应的进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 往应用task map加入activity
     */
    public final void addActivity(Activity atv) {
        this.taskMap.put(atv.toString(), new SoftReference<Activity>(atv));
    }

    /**
     * 往应用task map加入activity
     *
     * @param atv
     */
    public final void removeActivity(Activity atv) {
        this.taskMap.remove(atv.toString());
        return;
    }

    /**
     * 清除应用的task栈，如果程序正常运行这会导致应用退回到桌面
     */
    public final void exit() {
        //如果开发者调用Process.kill或者System.exit之类的方法杀死进程
        //请务必在此之前调用此方法,用来保存统计数据。


        /** 进程会被kill掉 http://blog.csdn.net/chonbj/article/details/10182369 */
        int currentVersion = Build.VERSION.SDK_INT;
        if (currentVersion > Build.VERSION_CODES.ECLAIR_MR1) {
           /* Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);*/
            System.exit(0);
        } else {// android2.1 
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            am.restartPackage(getPackageName());
        }
        //进程没被kill掉 使用finlish activity不一定会被gc回收掉
        /*for (Iterator<Map.Entry<String, SoftReference<Activity>>> iterator = this.taskMap.entrySet().iterator(); iterator.hasNext(); ) {
            SoftReference<Activity> activityReference = iterator.next().getValue();
            Activity activity = activityReference.get();
            if (activity != null) {
                activity.finish();
            }
        }
        this.taskMap.clear();*/
        return;
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
