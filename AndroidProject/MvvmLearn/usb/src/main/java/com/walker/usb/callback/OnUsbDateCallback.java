package com.walker.usb.callback;

public interface OnUsbDateCallback {
    void onReceive(String content);
    void onDeviceBack(byte[] bytes);
}
