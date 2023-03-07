package com.example.tflitedemo.tool;

import android.util.Log;

import java.util.Arrays;

/**
 * @author walker
 * @date 2023/2/12
 * @description 图像处理
 */
public class BitmapHandle {
    public static final String NO_ONE = "004200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    public static final String OVER = "00AA100440000000342000040084764400063010010000F077A7F4EF070000800705D0EE03001ED80706E0404E200E000000000000000000001100051000100020054A97DF06010E01160F054B160354020F14175EDB4D0D2C702ED437D88A7E2E27FFBDFFE3060A116FEAF3490FE5B5EE84CA81DDB40BD90E41201C22FF86E113304B8AFFFEE25B1107180280FFD848D3A7DCBDEB29F1856017252C82BC67237A11100D8D0281036C09";
    public static final String LEFT = "009C10004000003C142000042A04007C20001000850048D041BF7E4801020080271FD0EF030402C03F404050400000000000000028000000000000040000100000044DE8C8885D1F190A02481D222B01FFD0393FDDB4B847058370AF07C02543990BFFFFFFF3DE332EFFFFF9FFFD5D334308BB05120904091B305733F2E8F5D3DBDD9DFFFFFFF11C0C0414BEFCD2FF79E29607620FF3820225947B07";
    public static final String RIGHT = "00750084000000000000000000000000004000000000000000208002150000B8E00080CF3D00000C3100000000208E0000002000E00C200100120000000300002000EC3B4377339B8D4B3DFFFFFF35CF939EDAFFFFFFFF548151EDFFFFFFFFFF3DFFFF6EA1FF61384A9AF7F3EEFF4FF0D78E9944FF";
    public static final String UNDER = "0084008400000000000430000000000000000000000000000020000C1100F0933C0010E8690B00F83F0C000000208E0000000000A00F2201001200000004000020047261513452D255EED35FFF8AA4E74796FF9EA3696F3CFFD7FFFFFFFFFF38FCF2CFFFF38C82FF6CFFA1FFF1FFE2EDFF6EA1FF613890E34BDF6D453CFFD491638AFF46";
    public static final String SIT = "009E10004000000414200014020400040010000000000000402000040002000000000068020402C01F4000F84F0000F03F0000FC2F0000703F00003E1E0010783E0772F10D3A070227053C140C8E2401173DAB65D3010917D494E5C4FF92BA0EDDF3EFFFFFF2EB6474806EB0FFDBFCFCFFF66E4D8EF3FBFBA3FCC4F5C4532D36C89781CFBCF14740D1EBC2FFFC59FFB48D76BCE5F7F3E7E7FBCF1B631C03";
    private static int mLie = 16;

    /**
     * 格式化数组
     *
     * @param hex 16进制的压力数据
     * @return
     */
    public static int[] formatPressureHex(String hex, String position) {
        byte[] bytes = StringUtil.hexStringToBytes(hex);
        int[] psOld2 = newConvertToOld(bytes, 512);
        StringBuffer buffer = new StringBuffer();
        long start = System.currentTimeMillis();
        // （4）
        int indexMax = psOld2.length - 1;
        int[] ps = new int[psOld2.length];
        for (int i = indexMax; i >= 0; i--) {
            int h = (indexMax - i) / mLie; // 对列除，即所在行
            int lie = (indexMax - i) % mLie; // 对列求余，即所在列
//            ints[h][lie] = psOld2[i] = h + 1; // ①
            if (lie % 2 == 0) { // ④
                int t1 = 31 - h + lie * 32;
//                psOld2[i] = t1;
                ps[i] = psOld2[t1];
            } else {
                int t2 = h + lie * 32;
//                psOld2[i] = t2;
                ps[i] = psOld2[t2];
            }
        }
        MyLog.w("useTime : " + (System.currentTimeMillis() - start));
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < ps.length; i++) {
            int h = i / mLie; // 对列除，即所在行
            int lie = i % mLie; // 对列求余，即所在列
            if (lie == 0) buffer2.append("\n 行 " + (h + 1));
            buffer2.append("  " + ps[i]);
        }
        MyLog.i("竖排2: " + position + " ," + buffer2.toString());
        return ps;
    }

    /**
     * 格式化数组
     *
     * @param hex 16进制的压力数据
     * @return
     */
    public static int[] formatPressureHex1(String hex) {
        byte[] bytes = StringUtil.hexStringToBytes(hex);
        int[] psOld2 = newConvertToOld(bytes, 512);
        StringBuffer buffer = new StringBuffer();
        int[][] ints = new int[16][32];
        // 使用多次循环适配点位分布
        for (int i = 0; i < psOld2.length; i++) {
            int h = i / 32; // 对列除，即所在行
            int lie = i % 32; // 对列求余，即所在列
            if (h % 2 == 0) {
                ints[h][lie] = psOld2[i];
            } else {
                ints[h][lie] = psOld2[h * 32 + Math.abs(lie - 31)];
            }
            if (i % 32 == 0) buffer.append("\n 行 " + (i / 32 + 1));
            buffer.append("   " + i + "-" + ints[h][lie]);
        }
        MyLog.i("横排: " + buffer.toString());


        int[][] newInts = new int[ints[0].length][ints.length];
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
//                newInts[j][i] = ints[i][j]; //转置核心 
//                newInts[ints[i].length - j - 1][ints.length - i - 1] = ints[i][j]; //转置核心 
                newInts[j][ints.length - i - 1] = ints[i][j]; //转置核心 
            }
        }
        for (int i = 0; i < newInts.length; i++) {
            buffer2.append("\n 行 " + (i + 1));
            for (int j = 0; j < newInts[i].length; j++) {
                buffer2.append("  " + newInts[i][j]);
            }
        }
        MyLog.i("竖排 " + buffer2.toString());
        return psOld2;
    }

    /**
     * 新协议转旧协议
     *
     * @param pressureBytes 新协议byte[]，序号+有值内容
     * @return
     */
    public static int[] newConvertToOld(byte[] pressureBytes, int pmNumber) {
        int[] pressures = new int[pmNumber];
        try {
            int cnts = 0;
            for (int i = 0; i < pressures.length; i++) {
                // i索引对应所在的byte； i >> 3， 除以2的3次方
                byte b = pressureBytes[2 + (i >> 3)];
                // (i & 0x07) = (0,7) ： i 对 8 取余。
                int t = 1 << (i & 0x07); // 将1左移 x 位
                int result = b & t; // ?
                if (result != 0) {
                    // 标记序号
                    byte aByte = pressureBytes[2 + (pmNumber >> 3) + ((pmNumber & 0x07) == 1 ? 1 : 0) + cnts];
                    pressures[i] = StringUtil.getIntFromByte(aByte);
                    cnts++;
                } else {
                    pressures[i] = 0;
                }
            }
//            notifyMotorPressure(pressures);
        } catch (Exception e) {
            e.printStackTrace();
            if (pressureBytes == null) {
                MyLog.e("新旧协议转换失败，新数组是空");
            } else {
                MyLog.e("新旧协议转换失败，新hex：" + StringUtil.byteArrayToHexString(pressureBytes));
            }
        }
        return pressures;
    }


    /**
     * 格式化数组
     *
     * @param psOld  一维数组
     * @param with   一维数组转二维数组的列
     * @param height 一维数组转二维数组的行
     * @return
     */
    public static int[] formatPressureOfMat512(int[] psOld, int with, int height) {
        int[] psFormat = new int[psOld.length];
        for (int i = 0; i < psOld.length; i++) {
            int h = i / with; // 对列除，即所在行
            int l = i % with; // 对列求余，即所在列
            if (h % 2 == 0) {
                psFormat[i] = psOld[i];
            } else {
                psFormat[i] = psOld[h * with + Math.abs(l - (with - 1))];
            }
        }
        return psFormat;
    }

    /**
     * 还原数组
     *
     * @param psFormat 一维数组
     * @param with     一维数组转二维数组的列
     * @param height   一维数组转二维数组的行
     * @return
     */
    public static int[] resetPressureOfMat512(int[] psFormat, int with, int height) {
        int[] psOld = new int[psFormat.length];
        for (int i = 0; i < psFormat.length; i++) {
            int h = i / with; // 对列除，即所在行
            int l = i % with; // 对列求余，即所在列
            if (h % 2 == 0) {
                psOld[i] = psFormat[i];
            } else {
                psOld[i] = psFormat[h * with + Math.abs(l - (with - 1))];
            }
        }
        return psOld;
    }

    /**
     * 二维数组行列转换练习1
     *
     * @return
     */
    public static int[] changeHangLieTest2() {
        int[] psOld2 = new int[512];
        int tLie = 16;
        long start = System.currentTimeMillis();
        // （4）
        int indexMax = psOld2.length - 1;
        for (int i = indexMax; i >= 0; i--) {
            int h = (indexMax - i) / tLie; // 对列除，即所在行
            int lie = (indexMax - i) % tLie; // 对列求余，即所在列
//            ints[h][lie] = psOld2[i] = h + 1; // ①
            if (lie % 2 == 0) { // ④
                psOld2[i] = 31 - h + lie * 32;
            } else {
                psOld2[i] = h + lie * 32;
            }
        }
        MyLog.w("useTime : " + (System.currentTimeMillis() - start));
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < psOld2.length; i++) {
            int h = i / tLie; // 对列除，即所在行
            int lie = i % tLie; // 对列求余，即所在列
            if (lie == 0) buffer2.append("\n 行 " + (h + 1));
            buffer2.append("   " + psOld2[i]);
        }
        MyLog.i("竖排2: " + buffer2.toString());
        return psOld2;
    }

    /**
     * 二维数组行列转换练习1
     *
     * @return
     */
    public static int[] changeHangLieTest2_old() {
        int[] psOld2 = new int[512];
        int tLie = 16;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < psOld2.length; i++) {
            int h = i / tLie; // 对列除，即所在行
            int lie = i % tLie; // 对列求余，即所在列
//           psOld2[i] = h + 1; // （1）
           /* if (lie % 2 == 0) { // （2）
                psOld2[i] = h + 1;
            } else {
                psOld2[i] = 32 - h;
            } */
            if (lie % 2 == 0) { // （3）
                psOld2[i] = h + lie * 32;
            } else {
                psOld2[i] = 31 - h + lie * 32;
            }
            if (lie == 0) buffer.append("\n 行 " + (h + 1));
            buffer.append("   " + psOld2[i]);
        }
        MyLog.i("竖排1: " + buffer.toString());

        // （4）
        int indexMax = psOld2.length - 1;
        for (int i = indexMax; i >= 0; i--) {
            int h = (indexMax - i) / tLie; // 对列除，即所在行
            int lie = (indexMax - i) % tLie; // 对列求余，即所在列
//            ints[h][lie] = psOld2[i] = h + 1; // ①
            if (lie % 2 == 0) { // ④
                psOld2[i] = 31 - h + lie * 32;
            } else {
                psOld2[i] = h + lie * 32;
            }
        }

        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < psOld2.length; i++) {
            int h = i / tLie; // 对列除，即所在行
            int lie = i % tLie; // 对列求余，即所在列
            if (lie == 0) buffer2.append("\n 行 " + (h + 1));
            buffer2.append("   " + psOld2[i]);
        }
        MyLog.i("竖排2: " + buffer2.toString());
        return psOld2;
    }

    /**
     * 二维数组行列转换练习1
     *
     * @return
     */
    public static int[][] changeHangLieTest1() {
        int[] psOld2 = new int[512];
//        StringBuffer buffer = new StringBuffer();
        long start = System.currentTimeMillis();
        int[][] ints = new int[16][32];
        for (int i = 0; i < psOld2.length; i++) {
            int h = i / 32; // 对列除，即所在行
            int lie = i % 32; // 对列求余，即所在列
            if (h % 2 == 0) {
                ints[h][lie] = psOld2[i] = i + 1;
            } else {
                ints[h][lie] = psOld2[i] = h * 32 + Math.abs(lie - 32);
            }
//            if (i % 32 == 0) buffer.append("\n 行 " + (i / 32 + 1));
//            buffer.append("   " + psOld2[i] + "-" + ints[h][lie]);
        }
//        MyLog.i("横排: " + buffer.toString());

        int[][] newInts = new int[ints[0].length][ints.length];
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                newInts[j][ints.length - i - 1] = ints[i][j]; //转置核心 
            }
        }
        MyLog.w("useTime : " + (System.currentTimeMillis() - start));
        for (int i = 0; i < newInts.length; i++) {
            buffer2.append("\n 行 " + (i + 1));
            for (int j = 0; j < newInts[i].length; j++) {
                buffer2.append("  " + newInts[i][j]);
            }
        }
        MyLog.i("竖排 " + buffer2.toString());
        return newInts;
    }

    /**
     * 二维数组行列转换练习1
     *
     * @return
     */
    public static int[][] changeHangLieTest1_old() {
        int[] psOld2 = new int[512];
        StringBuffer buffer = new StringBuffer();
        int[][] ints = new int[16][32];
        for (int i = 0; i < psOld2.length; i++) {
            int h = i / 32; // 对列除，即所在行
            int lie = i % 32; // 对列求余，即所在列
            if (h % 2 == 0) {
                ints[h][lie] = psOld2[i] = i + 1;
            } else {
                ints[h][lie] = psOld2[i] = h * 32 + Math.abs(lie - 32);
            }
            if (i % 32 == 0) buffer.append("\n 行 " + (i / 32 + 1));
            buffer.append("   " + psOld2[i] + "-" + ints[h][lie]);
        }
        MyLog.i("横排: " + buffer.toString());

        int[][] newInts = new int[ints[0].length][ints.length];
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
//                newInts[j][i] = ints[i][j]; //转置核心 
//                newInts[ints[i].length - j - 1][ints.length - i - 1] = ints[i][j]; //转置核心 
                newInts[j][ints.length - i - 1] = ints[i][j]; //转置核心 
            }
        }
        for (int i = 0; i < newInts.length; i++) {
            buffer2.append("\n 行 " + (i + 1));
            for (int j = 0; j < newInts[i].length; j++) {
                buffer2.append("  " + newInts[i][j]);
            }
        }
        MyLog.i("竖排 " + buffer2.toString());
        return newInts;
    }

    /**
     * 二维数组行列转换
     *
     * @param psFormat 一维数组
     * @return
     */
    public static int[][] changeHangLie(int[][] psFormat) {
        int[][] newArray = new int[psFormat[0].length][psFormat.length];
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < psFormat.length; i++) {
            for (int j = 0; j < psFormat[i].length; j++) {
                newArray[j][i] = psFormat[i][j]; //转置核心 
            }
        }
        System.out.println("This is old Array");
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i].length; j++) {
                System.out.print(newArray[i][j] + " ");
            }
            System.out.println("");
        }
        return newArray;
    }
}
