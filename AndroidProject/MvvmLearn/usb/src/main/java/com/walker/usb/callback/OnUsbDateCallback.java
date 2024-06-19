package com.walker.usb.callback;

public interface OnUsbDateCallback {

    void onWrite(String bytes);

    void onReceive(String bytes);
}
