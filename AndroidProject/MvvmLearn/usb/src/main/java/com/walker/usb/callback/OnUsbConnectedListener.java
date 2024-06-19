package com.walker.usb.callback;

public interface OnUsbConnectedListener {

    void onStartConnect();

    void onConnected(String mac);

    void onConnectFail(String msg);

    void onDisconnect();

}
