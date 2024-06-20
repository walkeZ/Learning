package com.walker.usb;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.widget.Toast;

import com.walker.usb.USBSerial.driver.UsbSerialDriver;
import com.walker.usb.USBSerial.driver.UsbSerialPort;
import com.walker.usb.USBSerial.driver.UsbSerialProber;
import com.walker.usb.USBSerial.util.SerialInputOutputManager;
import com.walker.usb.USBSerial.util.LogUtil;
import com.walker.usb.USBSerial.util.ThreadUtil;
import com.walker.usb.callback.OnUsbConnectedListener;
import com.walker.usb.callback.OnUsbDateCallback;
import com.walker.usb.callback.OnUsbWriteCallback;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author walker
 * @date 2024/5/17
 * @description usb 数据传输工具,
 * USBSerial包是usb-serial-for-android源码，便于后期调试
 */
public class USBTransferUtil {
    public static final String MAC_START = "6200A1"; // 设备MAC的头部;
    private static final String MARK = "0d0a"; // 换行"\r\n";
    public static boolean isConnectUSB = false;  // 连接标识
    private Context my_context;
    private UsbManager manager;  // usb管理器

    private BroadcastReceiver usbReceiver;  // 广播监听：判断usb设备授权操作
    private static final String INTENT_ACTION_GRANT_USB = "com.walker.usb.INTENT_ACTION_GRANT_USB";  // usb权限请求标识
    private final String IDENTIFICATION = "GD32";  // 目标设备标识

    // 顺序： manager - availableDrivers（所有可用设备） - UsbSerialDriver（目标设备对象） - UsbDeviceConnection（设备连接对象） - UsbSerialPort（设备的端口，一般只有1个）
    private List<UsbSerialDriver> availableDrivers = new ArrayList<>();  // 所有可用设备
    private UsbSerialDriver usbSerialDriver;  // 当前连接的设备
    private UsbDeviceConnection usbDeviceConnection;  // 连接对象
    private UsbSerialPort usbSerialPort;  // 设备端口对象，通过这个读写数据
    private SerialInputOutputManager inputOutputManager;  // 数据输入输出流管理器

    // 连接参数，按需求自行修改，一般情况下改变的参数只有波特率，数据位、停止位、奇偶校验都是固定的8/1/none ---------------------
    private int baudRate = 115200;  // 波特率
    private int dataBits = 8;  // 数据位
    private int stopBits = UsbSerialPort.STOPBITS_1;  // 停止位
    private int parity = UsbSerialPort.PARITY_NONE;// 奇偶校验

    private OnUsbDateCallback mOnUsbDateCallback;
    private OnUsbConnectedListener mOnUsbConnectedListener;
    private String mSerialNumber;

    private long count = 0;

    public void setOnUsbDateCallback(OnUsbDateCallback mOnUsbDateCallback) {
        this.mOnUsbDateCallback = mOnUsbDateCallback;
    }

    public void setOnUsbConnectedListener(OnUsbConnectedListener mOnUsbConnectedListener) {
        this.mOnUsbConnectedListener = mOnUsbConnectedListener;
    }

    // 单例 -------------------------
    private static USBTransferUtil usbTransferUtil;

    public static USBTransferUtil getInstance() {
        if (usbTransferUtil == null) {
            usbTransferUtil = new USBTransferUtil();
        }
        return usbTransferUtil;
    }

    public void init(Context context) {
        my_context = context;
        manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        count = 0;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public void connect() {
        if (!isConnectUSB) {
            registerReceiver();  // 注册广播监听
            refreshDevice();  // 拿到已连接的usb设备列表
            connectDevice();  // 建立连接
        }
    }

    // 注册usb授权监听广播
    public void registerReceiver() {
        usbReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                LogUtil.e("onReceive: " + intent.getAction());
                if (INTENT_ACTION_GRANT_USB.equals(intent.getAction())) {
                    // 授权操作完成，连接
//                    boolean granted = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false);  // 不知为何获取到的永远都是 false 因此无法判断授权还是拒绝
                    connectDevice();
                }
            }
        };
        my_context.registerReceiver(usbReceiver, new IntentFilter(INTENT_ACTION_GRANT_USB));
    }

    // 刷新当前可用 usb设备
    public void refreshDevice() {
        availableDrivers.clear();
        availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        LogUtil.e("当前可用 usb 设备数量: " + availableDrivers.size());
        // 有设备可以连接
        if (availableDrivers.size() != 0) {
            // 当时开发用的是定制平板电脑有 2 个usb口，所以搜索到两个
            if (availableDrivers.size() > 1) {
                for (int i = 0; i < availableDrivers.size(); i++) {
                    UsbSerialDriver availableDriver = availableDrivers.get(i);
                    UsbDevice device = availableDriver.getDevice();
                    String productName = device.getProductName();
                    LogUtil.e("productName: " + productName + ", ManufacturerName = " + device.getManufacturerName());
                    // 我是通过 ProductName 这个参数来识别我要连接的设备
                    if (productName.startsWith(IDENTIFICATION)) {
                        usbSerialDriver = availableDriver;
                    }
                }
            } else {
                // 通常手机只有充电口 1 个
                usbSerialDriver = availableDrivers.get(0);
            }
            UsbDevice device = usbSerialDriver.getDevice();
            try {
                mSerialNumber = device.getSerialNumber();
                // 华为nova9，Android 12 可以获取。以 productName 来来识别体感衣。GD32就连接
                // mSerialNumber: 60645CE01633, {"mInterfaces":[{},{},{}]},{"mDevice":{"mInterfaces":[{},{},{}]},"mPorts":[{"mControlEndpoint":null,"mControlIndex":0,"mControlInterface":null,"mDataInterface":null,"mDtr":false,"mRts":false,"mConnection":null,"mDevice":{"mInterfaces":[{},{},{}]},"mPortNumber":0,"mReadEndpoint":null,"mUsbRequest":null,"mWriteBuffer":null,"mWriteBufferLock":{},"mWriteEndpoint":null}]}
                // mSerialNumber: 60645CE01633, productName: GD32-DUAL_CDC, ManufacturerName = GigaDevice, DeviceName /dev/bus/usb/002/004, DeviceId 2004, Version 1.00, {"mInterfaces":[{},{},{}]}, {"mDevice":{"mInterfaces":[{},{},{}]},"mPorts":[{"mControlEndpoint":null,"mControlIndex":0,"mControlInterface":null,"mDataInterface":null,"mDtr":false,"mRts":false,"mConnection":null,"mDevice":{"mInterfaces":[{},{},{}]},"mPortNumber":0,"mReadEndpoint":null,"mUsbRequest":null,"mWriteBuffer":null,"mWriteBufferLock":{},"mWriteEndpoint":null}]}
                LogUtil.w("mSerialNumber: " + mSerialNumber + ", productName: " + device.getProductName()
                        + ", ManufacturerName = " + device.getManufacturerName()
                        + ", DeviceName " + device.getDeviceName()
                        + ", DeviceId " + device.getDeviceId()
                        + ", Version " + device.getVersion());
            } catch (Exception e) {
                LogUtil.e(" usb getSerialNumber error " + e.getMessage());
            }
            usbSerialPort = usbSerialDriver.getPorts().get(0);  // 一般设备的端口都只有一个，具体要参考设备的说明文档
            // 同时申请设备权限
            if (!manager.hasPermission(device)) {
                int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_IMMUTABLE : 0;
                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(my_context, 0, new Intent(INTENT_ACTION_GRANT_USB), flags);
                manager.requestPermission(device, usbPermissionIntent);
            }
        }
        // 没有设备
        else {
            Toast.makeText(my_context, "请先接入设备", Toast.LENGTH_SHORT).show();
        }
    }

    // 连接设备
    public void connectDevice() {
        // (USBTransferUtil.java:146)  usb getSerialNumber error User has not given 10304/com.walker.mvvmlearn permission to access device /dev/bus/usb/002/004
        //2024-06-19 15:07:48.808  9153-9153  het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:190) 请先授予权限再连接
        //2024-06-19 15:07:50.748  9153-9153  het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:100) onReceive: com.walker.usb.INTENT_ACTION_GRANT_USB
        //2024-06-19 15:07:50.751  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:112) interfaces:
        //2024-06-19 15:07:50.752  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:114) UsbInterface[mId=2,mAlternateSetting=0,mName=null,mClass=8,mSubclass=6,mProtocol=80,mEndpoints=[
        //                                                                                                    UsbEndpoint[mAddress=129,mAttributes=2,mMaxPacketSize=64,mInterval=0]
        //                                                                                                    UsbEndpoint[mAddress=2,mAttributes=2,mMaxPacketSize=64,mInterval=0]]
        //2024-06-19 15:07:50.753  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:114) UsbInterface[mId=0,mAlternateSetting=0,mName=null,mClass=2,mSubclass=2,mProtocol=1,mEndpoints=[
        //                                                                                                    UsbEndpoint[mAddress=131,mAttributes=3,mMaxPacketSize=8,mInterval=10]]
        //2024-06-19 15:07:50.754  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:114) UsbInterface[mId=1,mAlternateSetting=0,mName=null,mClass=10,mSubclass=0,mProtocol=0,mEndpoints=[
        //                                                                                                    UsbEndpoint[mAddress=1,mAttributes=2,mMaxPacketSize=64,mInterval=0]
        //                                                                                                    UsbEndpoint[mAddress=130,mAttributes=2,mMaxPacketSize=64,mInterval=0]]
        //2024-06-19 15:07:50.755  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:120) trying default interface logic
        //2024-06-19 15:07:50.756  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:225) USB descriptor:
        //2024-06-19 15:07:50.758  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 12 01 00 02 00 00 00 40 E9 28 8B 02 00 01 01 02 03 01
        //2024-06-19 15:07:50.759  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 09 02 6A 00 03 01 00 80 32
        //2024-06-19 15:07:50.760  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 08 0B 02 01 08 06 50 00
        //2024-06-19 15:07:50.761  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 09 04 02 00 02 08 06 50 00
        //2024-06-19 15:07:50.761  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 07 05 81 02 40 00 00
        //2024-06-19 15:07:50.762  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 07 05 02 02 40 00 00
        //2024-06-19 15:07:50.763  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 08 0B 00 02 02 02 01 00
        //2024-06-19 15:07:50.764  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 09 04 00 00 01 02 02 01 00
        //2024-06-19 15:07:50.764  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 05 24 00 10 01
        //2024-06-19 15:07:50.765  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 05 24 01 00 03
        //2024-06-19 15:07:50.766  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 04 24 02 02
        //2024-06-19 15:07:50.767  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 05 24 06 02 03
        //2024-06-19 15:07:50.767  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 07 05 83 03 08 00 0A
        //2024-06-19 15:07:50.768  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 09 04 01 00 02 0A 00 00 00
        //2024-06-19 15:07:50.768  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 07 05 01 02 40 00 00
        //2024-06-19 15:07:50.769  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:227) 07 05 82 02 40 00 00
        //2024-06-19 15:07:50.770  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:155) interface count=3, IAD=-1
        //2024-06-19 15:07:50.770  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:172) no IAD fallback
        //2024-06-19 15:07:50.771  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:197) Control interface id 0
        //2024-06-19 15:07:50.772  9153-9153  het_1.0.0               com.walker.mvvmlearn                 D  (CdcAcmSerialDriver.java:210) data interface id 1
        //2024-06-19 15:07:50.774  9153-9153  het_1.0.0               com.walker.mvvmlearn                 W  (USBTransferUtil.java:225) mSerialNumber: 60645CE01633, productName: GD32-DUAL_CDC, ManufacturerName = GigaDevice, DeviceName /dev/bus/usb/002/004, DeviceId 2004, Version 1.00
        //2024-06-19 15:07:50.775  9153-9153  het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:233) 60645CE01633 连接成功 com.walker.mvvmlearn.usbSdk.UsbSdkActivity$1@b8e0518
        //2024-06-19 15:07:50.777  9153-10608 het_1.0.0               com.walker.mvvmlearn                 I  (SerialInputOutputManager.java:193) Running ...
        //2024-06-19 15:07:50.779  9153-10608 het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:208) 收到 usb 数据 ------------------< a502820000040009000006000011010103eb， ��??????	????????�， com.walker.mvvmlearn.usbSdk.UsbSdkActivity$2@995ab71
        //2024-06-19 15:07:50.782  9153-9153  het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:100) onReceive: com.walker.usb.INTENT_ACTION_GRANT_USB
        //2024-06-19 15:07:50.783  9153-9153  het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:168) usbSerialDriver == null || inputOutputManager != null
        //2024-06-19 15:07:50.920  9153-10608 het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:208) 收到 usb 数据 ------------------< a5028300000400090000060000110101036b， ��??????	????????k， com.walker.mvvmlearn.usbSdk.UsbSdkActivity$2@995ab71
        //2024-06-19 15:07:51.477  9153-10608 het_1.0.0               com.walker.mvvmlearn                 E  (USBTransferUtil.java:208) 收到 usb 数据 ------------------< a502840000040009000006000011010103c0， ��??????	????????�， com.walker.mvvmlearn.usbSdk.UsbSdkActivity$2@995ab71
        if (isConnectUSB) return; //  通过日志可知。授权后继续走startReceiveData();  // 开启数据监听。不一定需要在广播中再次调用。
        if (mOnUsbConnectedListener != null) {
            mOnUsbConnectedListener.onStartConnect();
        }
        if (usbSerialDriver == null || inputOutputManager != null) {
            LogUtil.e("usbSerialDriver == null || inputOutputManager != null");
            if (mOnUsbConnectedListener != null) {
                mOnUsbConnectedListener.onConnectFail("usbSerialDriver == null || inputOutputManager != null");
            }
            return;
        }
        // 判断是否拥有权限
        boolean hasPermission = manager.hasPermission(usbSerialDriver.getDevice());
        if (hasPermission) {
            usbDeviceConnection = manager.openDevice(usbSerialDriver.getDevice());  // 拿到连接对象
            if (usbSerialPort == null) {
                return;
            }
            try {
                usbSerialPort.open(usbDeviceConnection);  // 打开串口
                usbSerialPort.setParameters(baudRate, dataBits, stopBits, parity);  // 设置串口参数：波特率 - 115200 ， 数据位 - 8 ， 停止位 - 1 ， 奇偶校验 - 无
                startReceiveData();  // 开启数据监听
            } catch (IOException e) {
                e.printStackTrace();
                LogUtil.e("usbSerialPort.open IOException");
            }
        } else {
            LogUtil.e("请先授予权限再连接");
            if (mOnUsbConnectedListener != null) {
                mOnUsbConnectedListener.onConnectFail("请先授予权限再连接");
            }
            Toast.makeText(my_context, "请先授予权限再连接", Toast.LENGTH_SHORT).show();
        }
    }

    // 开启数据接收监听
    public void startReceiveData() {
        if (usbSerialPort == null || !usbSerialPort.isOpen()) {
            return;
        }
        inputOutputManager = new SerialInputOutputManager(usbSerialPort, new SerialInputOutputManager.Listener() {
            @Override
            public void onNewData(byte[] data) {
                // 在这里处理接收到的 usb 数据 -------------------------------
                String data_str = bytes2Hex(data);
                LogUtil.e("收到 usb 数据 ------------------< " + data_str + "， " + new String(data) + "， " + mOnUsbDateCallback);
                count ++;
                if (count < 30 && isATData(data_str)) {
                    data_str = new String(data);
                }
                String finalData_str = data_str;
                ThreadUtil.runOnMain(() -> {
                    if (mOnUsbDateCallback != null) {
                        mOnUsbDateCallback.onReceive(finalData_str);
                        mOnUsbDateCallback.onDeviceBack(data);
                    }
                });
            }

            @Override
            public void onRunError(Exception e) {
                LogUtil.e("usb 被断开了");
                disconnect();
                e.printStackTrace();
            }
        });

        UsbDevice device = usbSerialDriver.getDevice();
        try {
            mSerialNumber = device.getSerialNumber();
        } catch (Exception e) {
            LogUtil.e(" 连接成功 usb getSerialNumber error " + e.getMessage());
        }
        LogUtil.e(mSerialNumber + " 连接成功 " + mOnUsbConnectedListener);
        ThreadUtil.runOnMain(() -> {
            if (mOnUsbConnectedListener != null) mOnUsbConnectedListener.onConnected(mSerialNumber);
        });
        inputOutputManager.start();
        isConnectUSB = true;  // 修改连接标识
        count = 0;
        Toast.makeText(my_context, "连接成功", Toast.LENGTH_SHORT).show();
    }

    private boolean isATData(String hex) {
        if (hex == null) return false;
        return hex.startsWith(MARK) || hex.endsWith(MARK);
    }

    // 下发数据：建议使用线程池
    public void writeHex(String data_hex, OnUsbWriteCallback onUsbWriteCallback) {
        if (usbSerialPort != null) {
            // 当串口打开时再下发
            if (usbSerialPort.isOpen()) {
                byte[] data_bytes = hex2bytes(data_hex);  // 将字符数据转化为 byte[]
                if (data_bytes == null || data_bytes.length == 0) return;
                try {
                    LogUtil.e("writeHex----------> " + data_hex);
                    usbSerialPort.write(data_bytes, 0);  // 写入数据，延迟设置太大的话如果下发间隔太小可能报错
                    ThreadUtil.runOnMain(() -> {
                        if (onUsbWriteCallback != null) onUsbWriteCallback.onWriteSuccess(data_hex);
                    });
                } catch (IOException e) {
                    LogUtil.e("writeHex----------> error " + data_hex);
                    ThreadUtil.runOnMain(() -> {
                        if (onUsbWriteCallback != null)
                            onUsbWriteCallback.onFail(data_hex, "usb 发送失败，" + e.getMessage());
                    });
                    e.printStackTrace();
                }
            } else {
                ThreadUtil.runOnMain(() -> {
                    if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(data_hex, "usb 未连接");
                });
                LogUtil.e("writeHex: usb 未连接");
            }
        } else {
            ThreadUtil.runOnMain(() -> {
                if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(data_hex, "usb串口未找到");
            });
        }
    }

    public void writeBytes(byte[] data_bytes, OnUsbWriteCallback onUsbWriteCallback) {
        if (usbSerialPort != null) {
            // 当串口打开时再下发
            if (usbSerialPort.isOpen()) {
                if (data_bytes == null || data_bytes.length == 0) return;
                try {
                    LogUtil.e("writeBytes----------> " + bytes2Hex(data_bytes));
                    usbSerialPort.write(data_bytes, 0);  // 写入数据，延迟设置太大的话如果下发间隔太小可能报错
                    ThreadUtil.runOnMain(() -> {
                        if (onUsbWriteCallback != null) onUsbWriteCallback.onWriteByteSuccess(data_bytes);
                    });
                } catch (IOException e) {
                    LogUtil.e("writeBytes----------> error " + Arrays.toString(data_bytes));
                    ThreadUtil.runOnMain(() -> {
                        if (onUsbWriteCallback != null)
                            onUsbWriteCallback.onFail(bytes2Hex(data_bytes), "usb 发送失败，" + e.getMessage());
                    });
                    e.printStackTrace();
                }
            } else {
                ThreadUtil.runOnMain(() -> {
                    if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(bytes2Hex(data_bytes), "usb 未连接");
                });
                LogUtil.e("writeBytes: usb 未连接");
            }
        } else {
            ThreadUtil.runOnMain(() -> {
                if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(bytes2Hex(data_bytes), "usb串口未找到");
            });
        }
    }

    /**
     * @param str
     * @param charType UTF-8 GBK 等
     */
    public void writeStr(String str, String charType, OnUsbWriteCallback onUsbWriteCallback) {
        if (usbSerialPort != null) {
            // 当串口打开时再下发
            if (usbSerialPort.isOpen()) {
                byte[] data_bytes = string2bytes(str, charType);  // 将字符数据转化为 byte[]
                if (data_bytes == null || data_bytes.length == 0) return;
                try {
                    LogUtil.e("writeStr----------> " + str + ", " + charType);
                    usbSerialPort.write(data_bytes, 0);  // 写入数据，延迟设置太大的话如果下发间隔太小可能报错
                    ThreadUtil.runOnMain(() -> {
                        if (onUsbWriteCallback != null) onUsbWriteCallback.onWriteSuccess(str);
                    });
                } catch (IOException e) {
                    LogUtil.e("writeStr----------> error " + str + ", " + charType);
                    ThreadUtil.runOnMain(() -> {
                        if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(str, "usb 发送失败，" + e.getMessage());
                    });
                    e.printStackTrace();
                }
            } else {
                LogUtil.e("writeStr: usb 未连接");
                ThreadUtil.runOnMain(() -> {
                    if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(str, "usb 未连接");
                });
            }
        } else {
            ThreadUtil.runOnMain(() -> {
                if (onUsbWriteCallback != null) onUsbWriteCallback.onFail(str, "usb串口未找到");
            });
        }
    }

    // 断开连接
    public void disconnect() {
        try {
            // 停止数据接收监听
            if (inputOutputManager != null) {
                inputOutputManager.stop();
                inputOutputManager = null;
            }
            // 关闭端口
            if (usbSerialPort != null) {
                usbSerialPort.close();
                usbSerialPort = null;
            }
            // 关闭连接
            if (usbDeviceConnection != null) {
                usbDeviceConnection.close();
                usbDeviceConnection = null;
            }
            // 清除设备
            if (usbSerialDriver != null) {
                usbSerialDriver = null;
            }
            // 清空设备列表
            availableDrivers.clear();
            // 注销广播监听
            if (usbReceiver != null) {
                my_context.unregisterReceiver(usbReceiver);
            }
            if (isConnectUSB) {
                isConnectUSB = false;  // 修改标识
            }
            ThreadUtil.runOnMain(() -> {
                if (mOnUsbConnectedListener != null) mOnUsbConnectedListener.onDisconnect();
            });
            LogUtil.e("断开连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String bytes2string(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        String newStr = null;
        try {
            newStr = new String(bytes, "GB18030").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newStr;
    }

    public static byte[] hex2bytes(String hex) {
        if (hex == null || hex.length() < 1) {
            return null;
        }
        // 如果长度不是偶数，则前面补0
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        byte[] bytes = new byte[(hex.length() + 1) / 2];
        try {
            for (int i = 0, j = 0; i < hex.length(); i += 2) {
                byte hight = (byte) (Character.digit(hex.charAt(i), 16) & 0xff);
                byte low = (byte) (Character.digit(hex.charAt(i + 1), 16) & 0xff);
                bytes[j++] = (byte) (hight << 4 | low);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bytes;
    }

    public static String string2Hex(String str) {
        byte[] bytes = string2bytes(str, "GB18030");
        if (bytes == null) return null;
        return bytes2Hex(bytes);
    }

    public static byte[] string2bytes(String str, String charset) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
            LogUtil.e("string2bytes UnsupportedEncodingException " + str + "， " + charset);
            return null;
        }
    }

    public static String bytes2Hex(byte[] bytes) {
        try {
            String hex = "";
            for (int i = 0; i < bytes.length; i++) {
                int value = bytes[i] & 0xff;
                String hexVaule = Integer.toHexString(value);
                if (hexVaule.length() < 2) {
                    hexVaule = "0" + hexVaule;
                }
                hex += hexVaule;
            }
            return hex;
        } catch (Exception e) {
            return "bytes2Hex";
        }
    }
}
