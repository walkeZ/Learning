package com.example.tflitedemo.tool;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author walker
 * @date 2023/2/16
 * @description
 */
public class StringUtil {
    public StringUtil() {
    }

    public static byte binaryToDecimal8(long n) {
        byte value = 0;

        for(int i = 7; i >= 0; --i) {
            long t = n >>> i & 1L;
            System.out.print(t);
            long tmp = t << i;
            value = (byte)((int)((long)value | tmp));
        }

        return value;
    }

    public static short binaryToDecimal16(long n) {
        short value = 0;

        for(int i = 15; i >= 0; --i) {
            long t = n >>> i & 1L;
            System.out.print(t);
            long tmp = t << i;
            value = (short)((int)((long)value | tmp));
        }

        return value;
    }

    public static int binaryToDecimal32(long n) {
        int value = 0;

        for(int i = 15; i >= 0; --i) {
            long t = n >>> i & 1L;
            System.out.print(t);
            long tmp = t << i;
            value = (int)((long)value | tmp);
        }

        return value;
    }

    public static long binaryToDecimal64(long n) {
        long value = 0L;

        for(int i = 15; i >= 0; --i) {
            long t = n >>> i & 1L;
            System.out.print(t);
            long tmp = t << i;
            value |= tmp;
        }

        return value;
    }

    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];

        for(int i = 0; i < l; ++i) {
            ret[i] = Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }

        return ret;
    }

    public static String byteToMac(byte[] resBytes) {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < resBytes.length; ++i) {
            String hex = Integer.toHexString(resBytes[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            buffer.append(hex.toUpperCase());
        }

        return buffer.toString();
    }

    public static boolean isNum(String strNum) {
        return strNum.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    private static byte charToByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for(int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    public static <T> String join(T[] objs, String splitString) {
        return join((Object[])objs, 0, objs.length, splitString);
    }

    public static <T> String join(T[] objs, int start, int end, String splitString) {
        StringBuilder s = new StringBuilder();

        for(int i = start; i < end; ++i) {
            if (i != start) {
                s.append(splitString);
            }

            s.append(objs[i]);
        }

        return s.toString();
    }

    public static String join(List<?> objList, String splitString) {
        return join((List)objList, 0, objList.size(), splitString);
    }

    public static String join(List<?> objList, int start, int end, String splitString) {
        StringBuilder s = new StringBuilder();

        for(int i = start; i < end; ++i) {
            if (i != start) {
                s.append(splitString);
            }

            s.append(objList.get(i));
        }

        return s.toString();
    }

    public static <T> String join(List<T[]> objList, int columnIndex, String splitString) {
        StringBuilder s = new StringBuilder();
        if (objList.size() > 0) {
            s.append(((Object[])objList.get(0))[columnIndex]);
            int i = 1;

            for(int ii = objList.size(); i < ii; ++i) {
                s.append(splitString).append(((Object[])objList.get(i))[columnIndex]);
            }
        }

        return s.toString();
    }

    public static String repeat(String str, int repeat) {
        if (str == null) {
            throw new NullPointerException("重复的字符串不能为null。");
        } else if (repeat < 0) {
            throw new IllegalArgumentException("重复的次数(" + repeat + ")小于底限0。");
        } else {
            StringBuilder s = new StringBuilder();

            for(int i = 0; i < repeat; ++i) {
                s.append(str);
            }

            return s.toString();
        }
    }

    public static boolean equals(String string, String another) {
        if (string != null) {
            return string.equals(another);
        } else {
            return another != null ? another.equals(string) : true;
        }
    }

    public static boolean equalsIgnoreCase(String string, String another) {
        if (string != null) {
            return string.equalsIgnoreCase(another);
        } else {
            return another != null ? another.equalsIgnoreCase(string) : true;
        }
    }

    public static boolean contains(String strToFind, String... strings) {
        String[] var2 = strings;
        int var3 = strings.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String s = var2[var4];
            if (equals(s, strToFind)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsIgnoreCase(String strToFind, String... strings) {
        String[] var2 = strings;
        int var3 = strings.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String s = var2[var4];
            if (equalsIgnoreCase(s, strToFind)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isEmptyAny(String... ss) {
        String[] var1 = ss;
        int var2 = ss.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (isEmpty(s)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmptyAll(String... ss) {
        String[] var1 = ss;
        int var2 = ss.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (isNotEmpty(s)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    public static boolean isNotEmptyAny(String... ss) {
        String[] var1 = ss;
        int var2 = ss.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (isNotEmpty(s)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotEmptyAll(String... ss) {
        String[] var1 = ss;
        int var2 = ss.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (isEmpty(s)) {
                return false;
            }
        }

        return true;
    }

    public static void checkEmpty(String s) {
        if (isEmpty(s)) {
            throw new IllegalStateException();
        }
    }

    public static void checkEmpty(String s, String msg) {
        if (isEmpty(s)) {
            throw new IllegalStateException(msg);
        }
    }

    public static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    public static void checkNull(Object o, String msg) {
        if (o == null) {
            throw new NullPointerException(msg);
        }
    }

    public static byte[] getBytes(String s, String charset) {
        try {
            return s.getBytes(charset);
        } catch (UnsupportedEncodingException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static byte[] getBytes(String s, Charset charset) {
        return s.getBytes(charset);
    }

    public static String getString(byte[] bytes, String charset) {
        try {
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static String getString(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }

    public static byte[] hexStringToByteArray(String hexString) {
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("16进制数据长度不为2的倍数：" + hexString);
        } else {
            StringReader stringReader = new StringReader(hexString);
            byte[] bytes = new byte[hexString.length() / 2];
            char[] chars = new char[2];

            try {
                for(int i = 0; stringReader.read(chars) != -1; ++i) {
                    bytes[i] = (byte)Integer.parseInt(String.valueOf(chars), 16);
                }

                return bytes;
            } catch (IOException var5) {
                throw new IllegalStateException(var5);
            }
        }
    }

    public static String stringToHexString(String string, String charset) {
        byte[] bytes = getBytes(string, charset);
        return byteArrayToHexString(bytes);
    }

    public static String stringToHexString(String string, Charset charset) {
        byte[] bytes = getBytes(string, charset);
        return byteArrayToHexString(bytes);
    }

    public static String byteArrayToHexString(byte[] bytes) {
        Formatter fmt = new Formatter(new StringBuilder(bytes.length * 2));
        byte[] var2 = bytes;
        int var3 = bytes.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            fmt.format("%02x", b);
        }

        return fmt.toString().toUpperCase();
    }

    public static boolean isDigit(String s) {
        return s.matches("^\\d+$");
    }

    public static boolean isAlpha(String s) {
        return s.matches("^[a-zA-Z]+$");
    }

    public static boolean isUpper(String s) {
        return s.matches("^[A-Z]+$");
    }

    public static boolean isLower(String s) {
        return s.matches("^[a-z]+$");
    }

    public static boolean isAlnum(String s) {
        return s.matches("^[a-zA-Z\\d]+$");
    }

    public static boolean isInt(String s) {
        return s.matches("^[+-]?\\d+$");
    }

    public static boolean isFloat(String s) {
        return s.matches("^[+-]?(0\\.\\d+|0|[1-9]\\d*(\\.\\d+)?)$");
    }

    static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isEmail(String s) {
        return s.matches("^[a-zA-Z0-9._-]+@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)+$");
    }

    public static boolean isIP(String s) {
        return s.matches("^(0?0?[1-9]|0?[1-9]\\d|1\\d\\d|2[01]\\d|22[0-3])(\\.([01]?\\d?\\d|2[0-4]\\d|25[0-5])){3}$");
    }

    public static String byteArrayToHexZeroString(byte[] bytes) {
        Formatter fmt = new Formatter(new StringBuilder(bytes.length * 2));
        byte[] var2 = bytes;
        int var3 = bytes.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            if (b != 0) {
                fmt.format("%02x", b);
            }
        }

        return fmt.toString();
    }

    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str != null && str.length() != 0) {
            StringBuilder sb = new StringBuilder(str.length());
            if (capitalize) {
                sb.append(Character.toUpperCase(str.charAt(0)));
            } else {
                sb.append(Character.toLowerCase(str.charAt(0)));
            }

            sb.append(str.substring(1));
            return sb.toString();
        } else {
            return str;
        }
    }

    public static String toLowerCaseFirstOne(String s) {
        return Character.isLowerCase(s.charAt(0)) ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public static String toUpperCaseFirstOne(String s) {
        return Character.isUpperCase(s.charAt(0)) ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String getFileName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length()) {
                return filename.substring(0, dot);
            }
        }

        return filename;
    }

    public static boolean isInteger1(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isInteger(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception var2) {
            if (var2 != null) {
                var2.printStackTrace();
                System.out.println(var2.getMessage());
            }

            return false;
        }
    }


    public static int getIntFromByte(byte bite) {
        return bite & 255;
    }

    public static void main(String[] args) {
        byte[] rr = hexStringToByteArray("0b");
    }
}
