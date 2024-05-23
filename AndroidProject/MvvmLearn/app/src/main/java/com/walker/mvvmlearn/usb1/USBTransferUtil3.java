package com.walker.mvvmlearn.usb1;

// usb 数据传输工具

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.util.Log;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import com.walker.mvvmlearn.BuildConfig;
import com.walker.mvvmlearn.MyApp;
import com.walker.mvvmlearn.utils.DataUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/lxt1292352578/article/details/131976810
 */
public class USBTransferUtil3 {

    private String TAG = "USBTransferUtil";

    public static boolean isConnectUSB = false;
    private MyApp APP = MyApp.getInstance();  // 主程序，替换为你自己的
    private UsbManager manager = (UsbManager) APP.getSystemService(Context.USB_SERVICE);  // usb管理器

    private BroadcastReceiver usbReceiver;  // 广播监听：判断usb设备授权操作
    private static final String INTENT_ACTION_GRANT_USB = BuildConfig.APPLICATION_ID + ".INTENT_ACTION_GRANT_USB";  // usb权限请求标识
    private final String IDENTIFICATION = " USB-Serial Controller D";  // 目标设备标识

    private List<UsbSerialDriver> availableDrivers = new ArrayList<>();  // 所有可用设备
    private UsbSerialDriver usbSerialDriver;  // 当前连接的设备
    private UsbDeviceConnection usbDeviceConnection;  // 连接对象
    private UsbSerialPort usbSerialPort;  // 设备端口对象，通过这个读写数据
    private SerialInputOutputManager inputOutputManager;  // 数据输入输出流管理器

    // 连接参数，按需求自行修改 ---------
    private int baudRate = 115200;  // 波特率
    private int dataBits = 8;  // 数据位
    private int stopBits = UsbSerialPort.STOPBITS_1;  // 停止位
    private int parity = UsbSerialPort.PARITY_NONE;// 奇偶校验

    // 单例 -------------------------
    private static USBTransferUtil3 usbTransferUtil;
    public static USBTransferUtil3 getInstance() {
        if(usbTransferUtil == null){
            usbTransferUtil = new USBTransferUtil3();
        }
        return usbTransferUtil;
    }


    public void connect(){
        if(!isConnectUSB){ // 我的变量标识，自行删除或修改
            registerReceiver();  // 注册广播监听
            refreshDevice();  // 拿到已连接的usb设备列表
            connectDevice();  // 建立连接
        }
    }


    // 注册usb授权监听广播
    public void registerReceiver(){
        usbReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(INTENT_ACTION_GRANT_USB.equals(intent.getAction())) {
                    // 授权操作完成，连接
//                    boolean granted = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false);  // 不知为何获取到的永远都是 false 因此无法判断授权还是拒绝
                    connectDevice();
                }
            }
        };
        APP.registerReceiver(usbReceiver,new IntentFilter(INTENT_ACTION_GRANT_USB));
    }

    // 刷新当前可用 usb设备
    public void refreshDevice(){
        availableDrivers.clear();
        availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        Log.e(TAG, "当前可用 usb 设备数量: " + availableDrivers.size() );


        // 有设备可以连接
        if(availableDrivers.size() != 0){

            // 当时开发用的是定制平板电脑有 2 个usb口，所以搜索到两个
            if(availableDrivers.size()>1){
                for (int i = 0; i < availableDrivers.size(); i++) {
                    UsbSerialDriver availableDriver = availableDrivers.get(i);
                    // 我是通过 ProductName 这个参数来识别我要连接的设备
                    if(availableDriver.getDevice().getProductName().equals(IDENTIFICATION)){
                        usbSerialDriver = availableDriver;
                    }
                }
            }
            // 通常手机只有充电口 1 个
            else {
                usbSerialDriver = availableDrivers.get(0);
            }
            usbSerialPort = usbSerialDriver.getPorts().get(0);  // 一般设备的端口都只有一个，具体要参考设备的说明文档
            // 同时申请设备权限
            if(!manager.hasPermission(usbSerialDriver.getDevice())){
                int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_IMMUTABLE : 0;
                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(APP, 0, new Intent(INTENT_ACTION_GRANT_USB), flags);
                manager.requestPermission(usbSerialDriver.getDevice(), usbPermissionIntent);
            }
        }
        // 没有设备
        else {
            APP.showToast("请先接入设备");
        }


    }

    // 连接设备
    public void connectDevice(){
        if(usbSerialDriver == null || inputOutputManager != null){return;}
        // 判断是否拥有权限
        boolean hasPermission = manager.hasPermission(usbSerialDriver.getDevice());
        if(hasPermission){
            usbDeviceConnection = manager.openDevice(usbSerialDriver.getDevice());  // 拿到连接对象
            if(usbSerialPort == null){return;}
            try {
                usbSerialPort.open(usbDeviceConnection);  // 打开串口
                usbSerialPort.setParameters(baudRate, dataBits, stopBits, parity);  // 设置串口参数：波特率 - 115200 ， 数据位 - 8 ， 停止位 - 1 ， 奇偶校验 - 无
                startReceiveData();  // 开启读数据线程
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            APP.showToast("请先授予权限再连接");
        }

    }

    // 开启数据接收监听
    public void startReceiveData(){
        if(usbSerialPort == null || !usbSerialPort.isOpen()){return;}
        inputOutputManager = new SerialInputOutputManager(usbSerialPort, new SerialInputOutputManager.Listener() {
            @Override
            public void onNewData(byte[] data) {
                // 在这里处理接收到的 usb 数据
                String data_str = DataUtil.bytesToHexString(data).toUpperCase();
                Log.e(TAG, "收到 usb 数据: " + data_str);
            }
            @Override
            public void onRunError(Exception e) {
                Log.e(TAG, "usb 断开了" );
                disconnect();
                e.printStackTrace();
            }
        });
        inputOutputManager.start();
//        Variable.isConnectUSB = true;  // 修改连接标识
//        NotificationCenter.standard().postNotification(Constant.CONNECT_USB);  // 发送全局广播
        APP.showToast("连接成功");
    }

    // 下发数据
    public void write(String data_hex){
        if(usbSerialPort != null){
            Log.e(TAG, "当前usb状态: isOpen-" + usbSerialPort.isOpen() );
            // 当串口打开时再下发
            if(usbSerialPort.isOpen()){
                byte[] data_bytes = DataUtil.hexStringToBytes(data_hex);  // 将字符数据转化为 byte[]
                if (data_bytes == null || data_bytes.length == 0) return;
                try {
                    usbSerialPort.write(data_bytes,100);  // 写入数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                APP.showToast(" usb 未连接");
            }
        }
    }


    // 断开连接
    public void disconnect(){
        try{
            // 停止数据接收监听
            if(inputOutputManager != null){
                inputOutputManager.stop();
                inputOutputManager = null;
            }
            // 关闭端口
            if(usbSerialPort != null){
                usbSerialPort.close();
                usbSerialPort = null;
            }
            // 关闭连接
            if(usbDeviceConnection != null){
                usbDeviceConnection.close();
                usbDeviceConnection = null;
            }
            // 清除设备
            if(usbSerialDriver != null){
                usbSerialDriver = null;
            }
            // 清空设备列表
            availableDrivers.clear();
            // 注销广播监听
            APP.unregisterReceiver(usbReceiver);
            isConnectUSB = false;  // 修改标识
//            NotificationCenter.standard().postNotification(Constant.DISCONNECT_USB);  // 发送广播
            APP.showToast("断开连接");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // 下发初始化指令
    public void init_device(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        write(ProtocolUtil.CCICR(0,"00"));  // 查询 IC 信息
        write("6861686168610D0A");  // 查询 IC 信息
    }
}
