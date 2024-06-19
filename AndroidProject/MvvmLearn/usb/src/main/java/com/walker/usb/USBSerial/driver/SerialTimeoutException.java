package com.walker.usb.USBSerial.driver;

import java.io.InterruptedIOException;

/**
 * Signals that a timeout has occurred on serial write.
 * Similar to SocketTimeoutException.
 * <p>
 * {@see InterruptedIOException#bytesTransferred} may contain bytes transferred
 */
public class SerialTimeoutException extends InterruptedIOException {
    public SerialTimeoutException(String s) {
        super(s);
    }
}
