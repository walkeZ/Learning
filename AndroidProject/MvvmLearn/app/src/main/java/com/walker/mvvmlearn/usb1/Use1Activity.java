package com.walker.mvvmlearn.usb1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hoho.android.usbserial.driver.CommonUsbSerialPort;
import com.walker.mvvmlearn.databinding.ActivityUsb1Binding;

public class Use1Activity extends AppCompatActivity {

    ActivityUsb1Binding viewBinding;
    USBTransferUtil USB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityUsb1Binding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        USB = USBTransferUtil.getInstance();
        USB.init(this);
        CommonUsbSerialPort.DEBUG = true;
        // 数据接收
        USB.setOnUSBDateReceive(new USBTransferUtil.OnUSBDateReceive() {
            @Override
            public void onReceive(String data_str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewBinding.receive.append("receive: " + data_str + "--");
                    }
                });
            }
        });
        // 连接
        viewBinding.connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USB.connect();
            }
        });
        // 下发数据
        viewBinding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content_str = viewBinding.content.getText().toString();
                if (!content_str.equals("")) {
                    USB.write(USBTransferUtil.string2Hex(content_str));
                    viewBinding.receive.append("send: " + content_str + "--");
                }
            }
        });
        // 断开
        viewBinding.disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USB.disconnect();
            }
        });
    }


    @Override
    protected void onResume() {
        USB.connect();  // 当系统监测到usb插入动作后跳转到此页面时
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (USBTransferUtil3.isConnectUSB) {
            USB.disconnect();
        }
        super.onDestroy();
    }
}