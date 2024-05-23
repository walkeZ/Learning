package com.walker.mvvmlearn.usb1;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;

import com.hoho.android.usbserial.driver.CdcAcmSerialDriver;
import com.hoho.android.usbserial.driver.ProbeTable;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;

/**
 * 仅做参考对比
 *
 * https://blog.csdn.net/x995630736/article/details/130772261
 * https://github.com/mik3y/usb-serial-for-android
 *
 * 另：https://blog.csdn.net/lxt1292352578/article/details/131976810
 */
public class USBSerialHelper {
    private UsbManager usbManager;
    private UsbSerialPort usbSerialPort;
    private UsbDeviceConnection connection;
    private OnDataReceivedListener onDataReceivedListener;
    private ReadThread readThread;

    public interface OnDataReceivedListener {
        void onDataReceived(byte[] data);
    }

    public USBSerialHelper(Context context, OnDataReceivedListener listener) {
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        onDataReceivedListener = listener;
    }

    public void openDevice() throws IOException {
        ProbeTable customTable = new ProbeTable();
        customTable.addProduct(0x2341, 0x0043, CdcAcmSerialDriver.class);

        UsbSerialProber prober = new UsbSerialProber(customTable);
        List<UsbSerialDriver> availableDrivers = prober.findAllDrivers(usbManager);

        if (!availableDrivers.isEmpty()) {
            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDevice device = driver.getDevice();

            connection = usbManager.openDevice(device);
            usbSerialPort = driver.getPorts().get(0);
            usbSerialPort.open(connection);
            usbSerialPort.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

            readThread = new ReadThread();
            readThread.start();
        } else {
            throw new IOException("No USB serial device found");
        }
    }

    public void closeDevice() {
        if (readThread != null) {
            readThread.interrupt();
            readThread = null;
        }

        if (usbSerialPort != null) {
            try {
                usbSerialPort.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            usbSerialPort = null;
        }

        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public void sendData(byte[] data) throws IOException {
        if (usbSerialPort != null) {
            usbSerialPort.write(data, 1000);
        }
    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int numBytes;

            while (!isInterrupted()) {
                try {
                    numBytes = usbSerialPort.read(buffer, 1000);
                    if (numBytes > 0) {
                        byte[] data = new byte[numBytes];
                        System.arraycopy(buffer, 0, data, 0, numBytes);
                        onDataReceivedListener.onDataReceived(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
