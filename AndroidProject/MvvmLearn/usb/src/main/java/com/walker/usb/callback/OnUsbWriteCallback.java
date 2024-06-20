package com.walker.usb.callback;

public interface OnUsbWriteCallback {

    void onWriteSuccess(String content);

    void onWriteByteSuccess(byte[] content);

    void onFail(String write, String reason);
}
