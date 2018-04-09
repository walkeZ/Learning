package com.gdcaihui.luckycoin.android.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdcaihui.luckycoin.android.R;
import com.gdcaihui.luckycoin.android.base.AppActivity;
import com.gdcaihui.luckycoin.android.entity.Enable;
import com.gdcaihui.luckycoin.android.entity.vo.VersionInfo;
import com.gdcaihui.luckycoin.android.okhttp.loadapk.ExceptionBean;
import com.gdcaihui.luckycoin.android.okhttp.loadapk.Loader;
import com.gdcaihui.luckycoin.android.okhttp.loadapk.LoadingListenner;

import java.io.File;

import walke.baselibrary.activity.BaseActivity;
import walke.baselibrary.tools.ViewUtil;


/**
 *
 * 发现
 * 快速连续创建2个dialog，会产生指引异常，
 * 第二次创建时：确实会新建对象(dialog+内部控件)，但使用这些对象时会指向第一次创建的对象
 * 故需要设置为全局变量
 */
public class LoadDialog {

    public static Dialog createDialog(Context context, String message){
        Dialog progressDialog = new Dialog(context, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.load_dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText(message);
        return  progressDialog;
    }

    /*************************************************版本更新***********************************************************/
    private static Dialog dialog;
    private static TextView percentTest;
    private static TextView tipsTest;
    private static ProgressBar progressBarTest;
    private static LinearLayout buttonLayoutTest;
    private static ImageView ivOkTest;
    private static Button leftButtonTest;
    private static Button rightButtonTest;
    /**
     * @return 下载apk提示框
     */
    public static void loadingApkDialog(final Context context, final VersionInfo versionInfo, final String savePath) {
        // 设置背景颜色变暗
        final WindowManager.LayoutParams lp = ((BaseActivity) context).getWindow().getAttributes();
        lp.alpha = 0.6f;
        ((BaseActivity) context).getWindow().setAttributes(lp);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_loading_apk, null);
        WindowManager wm = ((BaseActivity) context).getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        int width = wm.getDefaultDisplay().getWidth();
        //final Dialog dialog = new Dialog(context, R.style.bind_dialog);
        dialog = new Dialog(context, R.style.bind_dialog);
        dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) (width * 0.85), (int) (height * 0.23)));
        dialog.setCancelable(false);//点击其他区域无响应
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        percentTest = (TextView) inflate.findViewById(R.id.dla_percent);
        tipsTest = (TextView) inflate.findViewById(R.id.dla_tips);
        progressBarTest = (ProgressBar) inflate.findViewById(R.id.dla_progress);
        buttonLayoutTest = (LinearLayout) inflate.findViewById(R.id.dla_buttonLayout);
        ivOkTest = (ImageView) inflate.findViewById(R.id.dla_ivOk);
        leftButtonTest = (Button) inflate.findViewById(R.id.dla_leftButton);
        rightButtonTest = (Button) inflate.findViewById(R.id.dla_rightButton);

        dialog.show();
        //还没开始下载/没下载完
        tipsTest.setVisibility(View.VISIBLE);
        ivOkTest.setVisibility(View.GONE);
        progressBarTest.setVisibility(View.VISIBLE);
        Loader.getInstance(context).loadFile(versionInfo.getUpdateDownloadUrl(), savePath, new LoadingListenner() {
            @Override
            public void loadStart(int startSize, int fileSize) {
                progressBarTest.setProgress(Loader.formatProgress(startSize, fileSize));
                percentTest.setText(Loader.formatProgress(startSize, fileSize) + "%");
            }
            @Override
            public void loading(int current, int total) {
                progressBarTest.setProgress(Loader.formatProgress(current, total));
                percentTest.setText(Loader.formatProgress(current, total) + "%");
            }
            @Override
            public void loadStop(ExceptionBean exceptionBean, int current, int total) {
                progressBarTest.setProgress(Loader.formatProgress(current, total));
                percentTest.setText(Loader.formatProgress(current, total) + "%");
                ((BaseActivity) context).toast("error: 下载出错");
                if (dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                    lp.alpha = 1f;
                    ((BaseActivity) context).getWindow().setAttributes(lp);
                    resetDialog(context, versionInfo);
                }

            }
            @Override
            public void loadFinish(String url, final String localPath) {
                percentTest.setText("下载已完成，立即安装？");
                tipsTest.setVisibility(View.GONE);
                progressBarTest.setVisibility(View.GONE);
                ivOkTest.setVisibility(View.VISIBLE);
                buttonLayoutTest.setVisibility(View.VISIBLE);
                percentTest=null;
                tipsTest=null;
                progressBarTest=null;
                ivOkTest=null;
                buttonLayoutTest=null;

                leftButtonTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialog=null;//快速连续创建2个dialog，会产生指引异常，第二次创建时：确实会新建对象，但使用这些对象时会指向第一次创建的对象
                        lp.alpha = 1f;
                        ((BaseActivity) context).getWindow().setAttributes(lp);
                        resetDialog(context,versionInfo);
                    }
                });
                rightButtonTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //安装
                        File file = new File(localPath);
                        AppUtil.installApk(context,file);
                       /* Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//++
                        context.startActivity(intent);*/
                    }
                });
            }
        });
    }

    private static void resetDialog(final Context context, final VersionInfo versionInfo) {
        if (versionInfo.getStatus().equals(Enable.ENABLE.getValue())) {
            versionUpdateDialogTwoButton(context, versionInfo.getText() + "", "继续使用", "下载更新", versionInfo.getUpdateVersionRemark(), new DialogTwoButtonClickListener() {
                @Override
                public void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                    dialog.dismiss();
                    lp.alpha = 1f;
                    ((BaseActivity) context).getWindow().setAttributes(lp);
                }
                @Override
                public void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                    AppUtil.loadApk(context, versionInfo);
                }
            });
        } else {
            //不可使用
            versionUpdateDialogTwoButton(context, versionInfo.getText() + "", "退出应用", "下载更新", versionInfo.getUpdateVersionRemark(), new DialogTwoButtonClickListener() {
                @Override
                public void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                    ((AppActivity) context).getLucyCoinApplication().exit();
                }
                @Override
                public void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog) {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                    AppUtil.loadApk(context, versionInfo);
                }
            });
        }
    }

    /**
     * @return 创建三行行提示框，有左右选项
     * （提示信息）
     * （按钮1）   （按钮2）
     */
    public static void versionUpdateDialogTwoButton(final Context context, String titleText, String leftText, String rightText, String describeText, final DialogTwoButtonClickListener listener) {
        // 设置背景颜色变暗
        final WindowManager.LayoutParams lp = ((BaseActivity) context).getWindow().getAttributes();
        lp.alpha = 0.6f;
        ((BaseActivity) context).getWindow().setAttributes(lp);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_update_version, null);
        WindowManager wm = ((BaseActivity) context).getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        int width = wm.getDefaultDisplay().getWidth();
        final Dialog dialog = new Dialog(context, R.style.bind_dialog);
        dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) (width * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCancelable(false);//点击其他区域无响应
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView title = (TextView) inflate.findViewById(R.id.duv_dialogTitle);
        TextView describe = (TextView) inflate.findViewById(R.id.duv_describe);
        title.setText(titleText);
        if (!TextUtils.isEmpty(describeText)) {
            if (describeText.contains("\\n")) {
                describeText = describeText.replace("\\n", "\n");//
            }
            describe.setText(describeText);
            //describe.setText("1.新增扫码支付功能; \n2.新增兑换码管理; \n3.修复多处bug; \n4.调整界面兼容问题");
        } else {
            describe.setVisibility(View.GONE);
        }
        Button leftButton = (Button) inflate.findViewById(R.id.duv_leftButton);
        Button rightButton = (Button) inflate.findViewById(R.id.duv_rightButton);
        if (!TextUtils.isEmpty(leftText)) {
            leftButton.setText(leftText);
        }
        if (!TextUtils.isEmpty(rightText)) {
            rightButton.setText(rightText);
        }
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.leftOnClick(lp, dialog);
                dialog.dismiss();
                lp.alpha = 1f;
                ((BaseActivity) context).getWindow().setAttributes(lp);
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.rightOnClick(lp, dialog);

            }
        });
        dialog.show();
    }

    /**
     * @return 创建两行提示框，
     * （提示信息）
     * （按钮）
     */
    public static void versionUpdateDialogOneButton(final Context context, String text, String buttonText, final OneButtonClickListener listener) {

        // 设置背景颜色变暗
        final WindowManager.LayoutParams lp = ((BaseActivity) context).getWindow().getAttributes();
        lp.alpha = 0.7f;
        ((BaseActivity) context).getWindow().setAttributes(lp);

        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_version_update, null);
        int windowWidth = ViewUtil.getWindowWidth(context);
        final Dialog dialog = new Dialog(context, R.style.bind_dialog);
        dialog.setContentView(inflate, new ViewGroup.LayoutParams((int) (windowWidth * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCancelable(false);//点击其他区域无响应
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView tvMessage = (TextView) inflate.findViewById(R.id.dvu_tvMessage);
        tvMessage.setText(text + "");//"当前版本已禁用\n感谢您的支持,请留意最新版本信息"
        TextView tvKnow = (TextView) inflate.findViewById(R.id.dvu_tvKnow);
        if (!TextUtils.isEmpty(buttonText))
            tvKnow.setText(buttonText + "");
        tvKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                lp.alpha = 1f;
                ((BaseActivity) context).getWindow().setAttributes(lp);
                if (listener != null)
                    listener.onClicked();
            }
        });
        dialog.show();
    }


    public interface DialogTwoButtonClickListener {
        void leftOnClick(WindowManager.LayoutParams lp, Dialog dialog);

        void rightOnClick(WindowManager.LayoutParams lp, Dialog dialog);
    }

    public interface OneButtonClickListener {
        void onClicked();
    }

}
