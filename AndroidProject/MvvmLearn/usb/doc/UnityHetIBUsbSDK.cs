using System;
using System.Collections;
using UnityEngine;
public class HetIbUsbSDK : MonoBehaviour
{
    private AndroidJavaObject _mAdplatormSDK;
    private AndroidJavaObject _unityPlayer;

    private bool bBleConnected = false;

    static HetIbUsbSDK instance;

    private void Awake()
    {
        instance = this;
#if UNITY_ANDROID
        Init();
#endif
        DontDestroyOnLoad(gameObject);
    }

    private void OnDestroy()
    {
        ReleaseBle();
    }

    public static HetIbUsbSDK GetInstance()
    {
        return instance;
    }

    public void Init()
    {
        var unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        _unityPlayer = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        var jc = new AndroidJavaClass("com.walker.usb.USBTransferUtil");
        //这里的getInstance代表上面java代码中HetIbUsbSDK.getInstance()中的方法名字
        _mAdplatormSDK = jc.CallStatic<AndroidJavaObject>("getInstance");
        // 对应.init(this)方法, _unityPlayer 这个代表是参数Context --this
        _mAdplatormSDK.Call("init", _unityPlayer);
    }

    // 释放蓝牙,按场景回收资源
    public void ReleaseBle()
    {
        _mAdplatormSDK.Call("release");
    }

    // 设置波特率
    public void setBaudRate(int baudRate)
    {
        //参数一：Android方法名，参数二+：Android方法的参数
        _mAdplatormSDK.Call("setBaudRate", baudRate);
    }
    
    // 设置Usb监听
    public void setListener()
    {
        //参数一：Android方法名，参数二+：Android方法的参数
        _mAdplatormSDK.Call("setOnUsbConnectedListener", new UsbConnectListener());
        _mAdplatormSDK.Call("setOnUsbDateCallback", new UsbDateCallback());
    }

    // 连接usb。直接连接。一般来说，建议连接前先设置监听
    public void ConnectUsb()
    {
        _mAdplatormSDK.Call("connect");
    }

    // 发送16进制
    public void SendHex(string hex)
    {
       //参数一：Android方法名，参数二+：Android方法的参数
        _mAdplatormSDK.Call("writeHex", hex, new UsbWriteCallback());
    }

     // 发送字符串
    public void SendStr(string str)
    {
       //参数一：Android方法名，参数二+：Android方法的参数
        _mAdplatormSDK.Call("writeStr", str, new UsbWriteCallback());
    }

    // 发送byte[]
    public void SendBytes(byte[] bytes)
    {
       //参数一：Android方法名，参数二+：Android方法的参数
        _mAdplatormSDK.Call("writeBytes", bytes, new UsbWriteCallback());
    }
}


//定义usb连接回调C#接口， 和java对应 也就是重写
public class UsbConnectListener : AndroidJavaProxy
{
    public UsbConnectListener() : base("com.walker.usb.callback.OnUsbConnectedListener") { }

    // 连接开始连接。
    public void onStartConnect()
    {
    }

    // 连接成功
    public void onConnected(string mac)
    {
    }

    // 连接失败
    public void onConnectFail(string msg)
    {
    }

    // 连接断开,连接后的断开
    public void onDisconnect()
    {
    }
}


//定义设备互动回调C#接口， 和java对应 也就是重写
public class UsbDateCallback : AndroidJavaProxy
{
    public UsbDateCallback() : base("com.walker.usb.callback.OnUsbDateCallback") { }
    /**
     * 设备device>app的数据。
     */
    public void onReceive(string content)
    {

    }

    /**
     * 设备device>app的数据。
     */
    public void onDeviceBack(byte[] bytes)
    {

    }
}


//定义设备互动回调C#接口， 和java对应 也就是重写
public class UsbWriteCallback : AndroidJavaProxy
{
    public UsbWriteCallback() : base("com.walker.usb.callback.OnUsbWriteCallback") { }
    /**
     * 设备写入成功。
     */
    public void onWriteSuccess(string content)
    {

    }

    /**
     * 设备写入成功。
     */
    public void onWriteByteSuccess(byte[] content)
    {

    }

     /**
     * 设备写入失败，write：写入的内容，reason：失败原因
     */
    public void onFail(string write, string reason)
    {

    }
}
