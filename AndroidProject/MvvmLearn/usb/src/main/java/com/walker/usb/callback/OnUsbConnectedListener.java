package com.walker.usb.callback;

public interface OnUsbConnectedListener {

    void onStartConnect(String msg);

    void onConnected(String mac);

    void onConnectFail(String msg);

    void onDisconnect();

}
