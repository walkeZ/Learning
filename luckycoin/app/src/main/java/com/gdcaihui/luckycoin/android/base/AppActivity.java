package com.gdcaihui.luckycoin.android.base;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.gdcaihui.luckycoin.android.LuckyCoinApplication;
import com.gdcaihui.luckycoin.android.business.web.WebViewActivity;
import com.gdcaihui.luckycoin.android.config.Api;
import com.gdcaihui.luckycoin.android.config.Contants;
import com.gdcaihui.luckycoin.android.entity.Enable;
import com.gdcaihui.luckycoin.android.entity.vo.MemberInfo;
import com.gdcaihui.luckycoin.android.entity.vo.Message;
import com.gdcaihui.luckycoin.android.entity.vo.VersionInfo;
import com.gdcaihui.luckycoin.android.entity.vo.VoBase;
import com.gdcaihui.luckycoin.android.event.LoginEvent;
import com.gdcaihui.luckycoin.android.event.RegisterEvent;
import com.gdcaihui.luckycoin.android.okhttp.HttpReuqest;
import com.gdcaihui.luckycoin.android.utils.AppUtil;
import com.gdcaihui.luckycoin.android.utils.LoadDialog;
import com.umeng.analytics.MobclickAgent;

import walke.baselibrary.ExitApplication;
import walke.baselibrary.activity.BaseActivity;
import walke.baselibrary.activity.SwipeBackActivity;
import walke.baselibrary.tools.NetWorkUtil;
import walke.baselibrary.tools.PermissionUtil;

/**
 * Created by Walke.Z on 2017/6/30.
 */
public abstract class AppActivity extends SwipeBackActivity {



    protected HttpReuqest httpReuqest;

    public LoginEvent onLoginEvent;
    public RegisterEvent onRegisterEvent;

    public void setOnLoginEvent(LoginEvent onLoginEvent) {
        this.onLoginEvent = onLoginEvent;
    }
    public void setOnRegisterEvent(RegisterEvent onRegisterEvent) {
        this.onRegisterEvent = onRegisterEvent;
    }

    public LuckyCoinApplication getLucyCoinApplication() {
        return (LuckyCoinApplication) getApplication();
    }

    public MemberInfo getMemberInfo() {
        return getLucyCoinApplication().getMemberInfo();
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        getLucyCoinApplication().setMemberInfo(memberInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        //禁止横屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        httpReuqest = new HttpReuqest(this);

        ExitApplication.getInstance().addActivity(this);

        setContentView(rootLayoutId());
        initialView();
        initialData();

    }


    protected abstract int rootLayoutId();

    protected abstract void initialView();

    protected abstract void initialData();


    public interface AysnLoadMemberInfoCallBack {
        void onSuccess(MemberInfo memberInfo);

        void onFail();
    }

    protected void loadUserInnfo(final AysnLoadMemberInfoCallBack aysnLoadMemberInfoCallBack) {
        MemberInfo memberInfo = getLucyCoinApplication().getMemberInfo();
        if (TextUtils.isEmpty(memberInfo.getToken()) || TextUtils.isEmpty(memberInfo.getAccount())) {
            return;
        }
        MemberInfo memberUserInfo = new MemberInfo();
        memberUserInfo.setOperateType(MemberInfo.OperateType.LOAD_INFO.getValue());
        memberUserInfo.setToken(memberInfo.getToken());
        memberUserInfo.setAccount(memberInfo.getAccount());
        httpReuqest.sendMessage( Api.MEMBER, memberUserInfo, true, new HttpReuqest.CallBack<MemberInfo>() {
            @Override
            public void onSuccess(Message message, MemberInfo memberInfo) {
                if (memberInfo != null) {
                    if (memberInfo.getCode() == Api.OK) {
                        getLucyCoinApplication().setMemberInfo(memberInfo);
                        if (aysnLoadMemberInfoCallBack != null) {
                            aysnLoadMemberInfoCallBack.onSuccess(memberInfo);
                        }
                    } else {
                        aysnLoadMemberInfoCallBack.onFail();
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                logE(e.getMessage());
                aysnLoadMemberInfoCallBack.onFail();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    protected void checkVersion(final BaseActivity activity) {
        VoBase voBase = new VoBase();
        httpReuqest.sendMessage(Api.VERSION_CHECK, voBase, false, new HttpReuqest.CallBack<VersionInfo>() {
            @Override
            public void onSuccess(Message message, final VersionInfo result) {
                if (Api.OK == result.getCode()) {
                    if (Enable.ENABLE.getValue().equals(result.getStatus())) {
                        if (result.getUpdateDownloadUrl() != null) {
                            LoadDialog.versionUpdateDialogTwoButton(activity, result.getText() + "", "继续使用", "下载更新", result.getUpdateVersionRemark(), new LoadDialog.DialogTwoButtonClickListener() {
                                @Override
                                public void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                                }

                                @Override
                                public void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog) {

                                    if (Build.VERSION.SDK_INT < 23) {
                                        dialogDismissResetWindow(lp, dialog);
                                        AppUtil.loadApk(activity, result);
                                    }else {
                                        //if (isAutoRequestCheck) {
                                        if (PermissionUtil.checkPermissionSetLack(AppActivity.this, Contants.PERMISSION_SDCARD)) {
                                            getLucyCoinApplication().setVersionInfo(result);
                                            getLucyCoinApplication().setDialog(dialog);
                                            requestPermissions(Contants.PERMISSION_SDCARD, Contants.PERMISSION_SDCARD_REQUEST_CODE); //去请求权限
                                        } else {
                                            dialogDismissResetWindow(lp, dialog);
                                            AppUtil.loadApk(activity, result);
                                        }
                                    }
                                }
                            });
                        }
                    } else {
                        //不可用
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                logE("Exception : " + e);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void dialogDismissResetWindow(WindowManager.LayoutParams lp, Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            lp.alpha = 1f;
            getWindow().setAttributes(lp);
        }
    }

    protected void toWebViewActivtiy(String url,boolean hideShare) {
        Intent forgetIntent = new Intent(this, WebViewActivity.class);
        forgetIntent.putExtra(Contants.INTENT_URL, url);
        forgetIntent.putExtra(Contants.WEBVIEW_SHARE_HIDE,hideShare );
        startActivity(forgetIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        try {

            if (!NetWorkUtil.isNetworkConnected(this)) {
                toast(Contants.ERROR_NO_NET);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Contants.LOGIN_SUCCESS_CODE) {
            if (this.onLoginEvent != null) {
                this.onLoginEvent.onLoginSuccess();
            }
        } else if (resultCode == Contants.REGISTER_SUCCESS_CODE) {
            if (this.onRegisterEvent != null) {
                this.onRegisterEvent.onRegisterSuccess();
            }
        }
        return;
    }



   /* private Dialog mRequestPermissionDialog;

    public void showRequestPermissionDialog() {
        runOnUiThread(new Runnable() {


            @Override
            public void run() {
                if (mRequestPermissionDialog==null||!mRequestPermissionDialog.isShowing()) {
                    mRequestPermissionDialog = DialogUtil.requestCameraPermissionDialog(
                            AppActivity.this,
                            "扫码使用帮助",
                            "需允许摄像头进行扫一扫:",
                            "取消",
                            "设置",
                            "",
                            new DialogUtil.DialogTwoButtonClickListener() {
                        @Override
                        public void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog) {

                        }

                        @Override
                        public void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                            dialog.dismiss();
                            openSystemSetting();
                        }
                    });
                }
            }
        });
    }*/



}
