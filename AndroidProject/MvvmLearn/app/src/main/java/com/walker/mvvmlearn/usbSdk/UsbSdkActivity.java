package com.walker.mvvmlearn.usbSdk;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;


import com.walker.mvvmlearn.BaseActivity;
import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityUsbSdkBinding;
import com.walker.usb.USBSerial.util.LogUtil;
import com.walker.usb.USBTransferUtil;
import com.walker.usb.callback.OnUsbConnectedListener;
import com.walker.usb.callback.OnUsbDateCallback;
import com.walker.usb.callback.OnUsbWriteCallback;

public class UsbSdkActivity extends BaseActivity {

    ActivityUsbSdkBinding viewBinding;
//    USBTransferUtil USB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("----->" + SystemUtil.getPhoneInfo(this));
        USBTransferUtil.getInstance().init(this);

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_usb_sdk);

        // 数据接收
//        USB.setOnUSBDateReceive(data_str -> runOnUiThread(() -> viewBinding.receive.append("receive: "+data_str+"\r\n")));
        // 连接
        viewBinding.ausTvConnect.setOnClickListener(view -> USBTransferUtil.getInstance().connect());
        // 断开
        viewBinding.ausBtnDisconnect.setOnClickListener(view -> USBTransferUtil.getInstance().disconnect());
        // 下发数据
        viewBinding.ausBtnSend.setOnClickListener(view -> {
            // AT+RESTORE
            // AT+UUID=FF12,FF03,FF01
            // AT+SCAN=1
            // AT+CONN=xx xx xx xx xx xx0
            String cmd = viewBinding.ausEt0.getText().toString();
            if (cmd.contains(USBTransferUtil.MAC_START)) cmd = "AT+CONN=" + cmd;
            USBTransferUtil.getInstance().writeStr(cmd + "\r\n", "UTF-8", new OnUsbWriteCallback() {
                @Override
                public void onWriteSuccess(String content) {
                    viewBinding.ausTvWr.append("\n" + ">>: " + content);
                }

                @Override
                public void onFail(String write, String msg) {
                    viewBinding.ausTvWr.append("\n" + ">>:(fail) " + write);
                }
            });
        });
        // 下发数据
        viewBinding.ausSendHex.setOnClickListener(view -> {
            String cmd = viewBinding.ausEt1.getText().toString();
            USBTransferUtil.getInstance().writeHex(cmd, new OnUsbWriteCallback() {
                @Override
                public void onWriteSuccess(String content) {
                    viewBinding.ausTvWr.append("\n" + ">>: " + content);
                }

                @Override
                public void onFail(String write, String msg) {
                    viewBinding.ausTvWr.append("\n" + ">>:(fail) " + write);
                }
            });
        });
        USBTransferUtil.getInstance().setOnUsbConnectedListener(new OnUsbConnectedListener() {
            @Override
            public void onStartConnect() {
                viewBinding.ausTvMac.setText("开始连接");
            }

            @Override
            public void onConnected(String mac) {
                viewBinding.ausTvMac.setText(mac + " 连接成功");
            }

            @Override
            public void onConnectFail(String msg) {
                viewBinding.ausTvMac.setText("连接失败 " + msg);
            }

            @Override
            public void onDisconnect() {
                viewBinding.ausTvMac.setText("连接断开");
            }
        });
        USBTransferUtil.getInstance().setOnUsbDateCallback(new OnUsbDateCallback() {

            @Override
            public void onReceive(String hex) {
                viewBinding.ausTvNotify.append("\n" + "<<: " + hex);
                viewBinding.ausTvWr.append("\n" + "<<: " + hex);
                if (hex.contains(USBTransferUtil.MAC_START)) {
                    viewBinding.ausEt0.append("-" + hex);
                }
            }
        });

//        viewBinding.ausBtnDisconnect.setOnClickListener(view -> viewBinding.ausTvReceive.setText(""));
        viewBinding.ausSv1.getViewTreeObserver().addOnGlobalLayoutListener(() -> viewBinding.ausSv1.post(() -> viewBinding.ausSv1.fullScroll(View.FOCUS_DOWN)));
//        viewBinding.ausBtnDisconnect.setOnClickListener(view -> viewBinding.ausTvReceive.setText(""));
        viewBinding.ausSv2.getViewTreeObserver().addOnGlobalLayoutListener(() -> viewBinding.ausSv2.post(() -> viewBinding.ausSv2.fullScroll(View.FOCUS_DOWN)));
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("----- onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
//        if (USBTransferUtil.isConnectUSB) {
////            USB.disconnect();
//        }
        super.onDestroy();
        LogUtil.e("----- onDestroy");
        // 出现报错，说当前的横屏处理会异常的调用onDestroy。
        // java.lang.RuntimeException: Unable to destroy activity {com.het.ib.runningdevice/com.het.ib.runningdevice.usb_demo.UsbDemoActivity}:
        //  java.lang.IllegalArgumentException: Receiver not registered: com.het.ib.runningdevice.usb.AppUsbManager$3@d59067b
        //  at android.app.ActivityThread.performDestroyActivity(ActivityThread.java:5590)
//        McuCmdHelper.release(); 这是全局连接的一般都在mainActivity中初始化与回收
    }

    public void clearWR(View view) {
        viewBinding.ausTvWr.setText("write/read");
    }


    public void clearNotify(View view) {
        viewBinding.ausTvNotify.setText("device>app");
    }

    //AT+RESTORE
    //AT+UUID=FF12,FF03,FF01
    //AT+SCAN=1
    //AT+CONN=xx xx xx xx xx xx0
    public void atRestore(View view) {
        viewBinding.ausEt0.setText("AT+RESTORE");
    }

    public void atUuId(View view) {
        viewBinding.ausEt0.setText("AT+UUID=FF12,FF03,FF01");
    }

    public void atScan(View view) {
        viewBinding.ausEt0.setText("AT+SCAN=1");
    }

    public void atConn(View view) {
        viewBinding.ausEt0.setText("AT+UUID=FF12,FF03,FF01");
    }
}