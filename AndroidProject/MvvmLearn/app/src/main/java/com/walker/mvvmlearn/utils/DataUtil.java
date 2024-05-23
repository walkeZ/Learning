package com.walker.mvvmlearn.utils;


import java.util.Formatter;

/**
 * @author walker
 * @date 2023/4/17
 * @description 数据工具，有上标立方数字
 * 9⁰¹²³⁴⁵⁶⁷⁸⁹¹⁰
 */
public class DataUtil {
    /**
     * Byte转Bit
     */
    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) +
                (byte) ((b >> 6) & 0x1) +
                (byte) ((b >> 5) & 0x1) +
                (byte) ((b >> 4) & 0x1) +
                (byte) ((b >> 3) & 0x1) +
                (byte) ((b >> 2) & 0x1) +
                (byte) ((b >> 1) & 0x1) +
                (byte) ((b >> 0) & 0x1);
    }

    /**
     * Bit转Byte
     */
    public static byte bitToByte(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (byteStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(byteStr, 2);
            } else {// 负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {//4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }

    /**
     * Bit转Byte
     */
    public static byte[] bit8ToBytes(String bitStr) {
        if (bitStr.length() % 8 != 0) {
            return null;
        }
        int len = bitStr.length() / 8;
        byte[] bts = new byte[len];
        for (int i = 0; i < len; i++) {
            String substring = bitStr.substring(i * 8, (i + 1) * 8);
            bts[i] = bitToByte(substring);
        }
        return bts;
    }

    /**
     * 9⁰¹²³⁴⁵⁶⁷⁸⁹¹⁰
     * https://blog.csdn.net/bbj12345678/article/details/77914018
     *
     * @param b 要判断的数 ，可以是byte、int
     * @param n 位置为第n位（从低字节起.......0）
     */
    public static boolean bitN_As1(byte b, int n) {
        int leftOffset = (1 << n);
        // 1 << n ： 把1转换成二进制（8位），往左移 n 位。https://www.cnblogs.com/ljangle/p/13036992.html
        // 另一种计算转换：1*2ⁿ 。即 2⁷ 2⁶ 2⁵ 2⁴ 2³ 2² 2¹ 2⁰
        int bit = b & leftOffset; // b & leftOffset 位与运算。将b、leftOffset转换为2进制。然后位与运算(同位间：0 0 / 1 1 -> 0; 1 0 / 0 1 -> 1);
        if (bit > 0) { // 或者可以写16进制数Num &（hex（1<<N-1））
            //为1
//            Log.e("DataUtil", "bittest: " + bit + ", n = " + n + ", " + byteToBit(b));
            return true;
        } else {
            //为0
//            Log.w("DataUtil", "bittest: " + bit + ", n = " + n + ", " + byteToBit(b));
            return false;
        }
    }

    /**
     * Set the specified bit to 1
     *
     * @param originByte Raw byte value
     * @param bitIndex   bit index (From 0~7)
     * @return Final byte value
     */
    public static byte setSpecifiedBitTo1(byte originByte, int bitIndex) {
        byte b = originByte |= (1 << bitIndex);
        return b;
    }

    /**
     * Set the specified bit to 0
     *
     * @param originByte Raw byte value
     * @param bitIndex   bit index (From 0~7)
     * @return Final byte value
     */
    public static byte setSpecifiedBitTo0(byte originByte, int bitIndex) {
        byte b = (originByte &= ~(1 << bitIndex));
        return b;
    }

    /**
     * Invert the specified bit
     *
     * @param originByte Raw byte value
     * @param bitIndex   bit index (From 0~7)
     * @return Final byte value
     */
    public static byte setSpecifiedBitToReverse(byte originByte, int bitIndex) {
        return originByte ^= (1 << bitIndex);
    }

    /**
     * Get the value of the specified bit
     *
     * @param originByte Raw byte value
     * @param bitIndex   bit index (From 0~7)
     * @return Final byte value
     */
    public static byte getSpecifiedBitValue(byte originByte, int bitIndex) {
        return (byte) ((originByte) >> (bitIndex) & 1);
    }

    public static void bitCheck1(byte b) {
        Boolean[] bzw = new Boolean[8];
        for (int i = 0, n = 1; i < 8; i++) {
            bzw[i] = ((b & n) == 0 ? false : true);
            n = n << 1;
            bitN_As1(b, i);
        }
//        Log.i("DataUtil", "bittest: " + b + " , " + Arrays.toString(bzw) + ", " + byteToBit(b));
    }

    public static String bytesToHexString(byte[] bytes) {
        Formatter fmt = new Formatter(new StringBuilder(bytes.length * 2));
        byte[] var2 = bytes;
        int var3 = bytes.length;
        for (int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            fmt.format("%02x", b);
        }
        return fmt.toString().toUpperCase();
    }

    public static byte[] hexStringToBytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];

        for (int i = 0; i < l; ++i) {
            ret[i] = Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }

        return ret;
    }

    public static int bytesToIntHigh(byte[] bytes) {
        int value = 0;
        if (bytes != null && bytes.length != 0) {
            for (int i = 0; i < bytes.length; ++i) {
                value += (bytes[i] & 255) << 8 * (bytes.length - i - 1);
            }

        }
        return value;
    }

    public static byte[] intToBytesHigh(int value, int n) {
        byte[] src = new byte[n];
        for (int i = 0; i < n; ++i) {
            src[i] = (byte) (value >> 8 * (n - i - 1) & 255);
        }
        return src;
    }

    /**
     * @param value
     * @return
     */
    public static String intToHexString(int value, int byteLength) {
        String hexStr = bytesToHexString(intToBytesHigh(value, byteLength));
        return hexStr;
    }

    public static int getIntFromByte(byte b) {
        return b & 255;
    }

    public static String bytesLog(byte[] bytes) {
        if (bytes == null) {
            return "bytes is null";
        }
        String origin = bytesToHexString(bytes);
        return origin;
    }
}
