package com.example.tflitedemo.tool;


import com.example.tflitedemo.tflite.HetClassifier;
import com.example.tflitedemo.tflite.Recognition;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author walker
 * @date 2023/2/10
 * @description *  Description:中值滤波  * @param imagesPos  * @param tmp 模板矩阵 2*2  * @return
 * https://blog.csdn.net/weixin_35803802/article/details/114062621
 */

public class BitmapUtil {
    private static int mLie = 16;

    /**
     * @param pixels 一维数组
     */
    public static void histogram(int[] pixels) {
        pixels = new int[]{255, 128, 200, 50, 50, 200, 255, 50, 255, 200, 128, 128, 200, 200, 255, 50};
        // 记录灰度值出现个数
        double[] count = new double[256];
        StringBuffer buffer = new StringBuffer();
        for (int k = 0; k < count.length; k++) {
            for (int i = 0; i < pixels.length; i++) {
                if (k == 0) {
                    buffer.append(" " + pixels[i]);
                    if (i % 4 == 3) buffer.append("\n");
                }

                if (pixels[i] == k) {
                    // 累计灰度值个数
                    count[k] += 1;
                }

            }
            if (count[k] != 0) MyLog.i("灰度值： " + k + ", 个数 " + count[k]);
            if (k == count.length - 1) MyLog.i("遍历原数组： \n" + buffer.toString());
        }
        // 计算各灰度值出现概率
        double[] Pr = new double[count.length];
        // 对应灰度值概率累计; 第一个灰度值的是0.03 = 0 + 0.03; 第二个灰度值的 0.23 = 0.2 + 0.3; ...
        double[] sk = new double[Pr.length];
        for (int i = 0; i < Pr.length; i++) {
            Pr[i] = count[i] / (double) pixels.length;
            for (int j = 0; j <= i; j++) {
                sk[i] += Pr[j];
            }
            if (count[i] != 0) {
                MyLog.i("计算各灰度值出现概率, 灰度值 i " + i + ", 次数 " + count[i] + ", 概率 " + Pr[i] + ", 累计概率 " + sk[i]);
            }
        }
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (int) ((count.length - 1) * sk[pixels[i]] + 0.5);
            buffer2.append(" " + pixels[i]);
            if (i % 4 == 3) buffer2.append("\n");
        }
        MyLog.i("遍历处理后的数组： \n" + buffer2.toString());
        MyLog.i("-----------------------------------");
    }

    /**
     * //blog.csdn.net/qq_41037012/article/details/104900944
     * https://www.freesion.com/article/1932342426/
     *
     * @return
     */
    public static void histogram2(int[][] pixels, int x, int y) {
        int width = 4;
        int height = 4;
        pixels = new int[][]{
                {255, 128, 200, 50},
                {50, 200, 255, 50},
                {255, 200, 128, 128},
                {200, 200, 255, 50}};
        // 记录灰度值出现个数
        double[] count = new double[256];
        for (int k = 0; k < count.length; k++) {
            for (int i = 0; i < width; i++) {
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < height; j++) {
                    buffer.append(" " + pixels[i][j]);
                    if (pixels[i][j] == k) {
                        // 累计灰度值个数
                        if (count[k] == 0) {
                            count[k] = 1;
                        } else {
                            count[k] += 1;
                        }
                    }
                }
                if (k == 0) MyLog.i("遍历原数组： " + buffer.toString());
            }
            if (count[k] != 0) MyLog.i("灰度值： " + k + ", 个数 " + count[k]);
        }
        // 计算各灰度值出现概率
        double[] Pr = new double[count.length];
        // 对应灰度值概率累计; 第一个灰度值的是0.03 = 0 + 0.03; 第二个灰度值的 0.23 = 0.2 + 0.3; ...
        double[] sk = new double[Pr.length];
        for (int i = 0; i < Pr.length; i++) {
            Pr[i] = count[i] / (double) (width * height);
            for (int j = 0; j <= i; j++) {
                sk[i] += Pr[j];
            }
            if (count[i] != 0) {
                MyLog.i("计算各灰度值出现概率, 灰度值 i " + i + ", 次数 " + count[i] + ", 概率 " + Pr[i] + ", 累计概率 " + sk[i]);
            }
        }
        int[][] newPixels = new int[width][height]; // 统计新图的灰度值
        for (int i = 0; i < width; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < height; j++) {
                int h = pixels[i][j];
                newPixels[i][j] = (int) ((count.length - 1) * sk[h] + 0.5);
                buffer.append(" " + newPixels[i][j]);
            }
            MyLog.i("遍历处理后的数组： " + buffer.toString());
        }
        MyLog.i("-----------------------------------");
    }


    /**
     * @param pixels 图像像素点，二维数组
     * @param w      二维数组的列
     * @param h      二维数组的行
     */
    public static void medianFilter(int[][] pixels, int w, int h) {
        w = h = 4;
        pixels = new int[][]{
                {150, 145, 151, 50},
                {146, 250, 144, 50},
                {151, 148, 150, 50},
                {50, 50, 50, 50}};

        StringBuffer buffer = new StringBuffer();
        for (int y = 1; y < h - 1; y++) {
            buffer.append("\n");
            for (int x = 1; x < w - 1; x++) {
                int value = pixels[y][x];
                buffer.append(" " + value);
                int[] filterArray = new int[9]; // 3*3过滤
                for (int i = 0; i < filterArray.length; i++) {
                    int ux = i % 3 + x;
                    int uy = y + i / 3;
//                    MyLog.i("遍历原数组00： " + uy + ",  ux " + ux + ", filterArray =" + Arrays.toString(filterArray));
                    filterArray[i] = pixels[uy - 1][ux - 1];
                }
                Arrays.sort(filterArray);
                pixels[y][x] = filterArray[4]; // 中值替换
                MyLog.i("遍历原数组中值下标y,x (" + y + "," + x + "), 变化" + value + "->" + filterArray[4] + ", filterArray =" + Arrays.toString(filterArray));
            }
            MyLog.i("遍历原数组中值： " + buffer.toString());
        }
    }

    /**
     * http://events.jianshu.io/p/964f68851abc
     *
     * @param pixels
     * @param w
     * @param h
     */
    public static void medianFilter2(int[][] pixels, int w, int h) {
        w = h = 4;
        pixels = new int[][]{
                {150, 145, 151, 50},
                {146, 250, 144, 50},
                {151, 148, 150, 50},
                {50, 50, 50, 50}};
        w = h = 3;
        pixels = new int[][]{{150, 145, 151}, {146, 250, 144}, {151, 148, 150}};
        int[] temp = new int[w * h];
        for (int y = 0; y < h; y++) {
            StringBuffer buffer = new StringBuffer();
            for (int x = 0; x < w; x++) {
                int value = pixels[y][x];
                buffer.append(" " + value);
                int index = y * w + x;
                temp[index] = pixels[y][x];
//                MyLog.i("遍历原数组00： " + buffer.toString() + ",        i " + index + ", " + temp[index]);
            }
            MyLog.i("遍历原数组11： " + buffer.toString());
        }
        MyLog.i("遍历原数组22： " + Arrays.toString(temp));
        int[] ints = medianFiltering(temp, w, h);
        int[][] newPixels = new int[w][h];
        for (int y = 0; y < h; y++) {
            StringBuffer buffer = new StringBuffer();
            for (int x = 0; x < w; x++) {
                newPixels[y][x] = ints[y * w + x];
                buffer.append(" " + newPixels[y][x]);
            }
            MyLog.i("遍历处理后数组： " + buffer.toString());
        }
        MyLog.i("遍历处理后数组： " + Arrays.toString(ints));
    }

    /**
     * http://events.jianshu.io/p/964f68851abc
     *
     * @param pixel 一维数组
     * @param w     一维数组转二维数组的列
     * @param h     一维数组转二维数组的行
     */
    public static int[] medianFiltering(int[] pixel, int w, int h) {
        int[] newPixel = new int[w * h];
        int[] tempR = new int[9];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                    // 首行/列、尾行/列
                    newPixel[y * w + x] = pixel[y * w + x];
                    continue;
                }
                tempR[0] = pixel[x - 1 + (y - 1) * w];
                tempR[1] = pixel[x + (y - 1) * w];
                tempR[2] = pixel[x + 1 + (y - 1) * w];
                tempR[3] = pixel[x - 1 + y * w];
                tempR[4] = pixel[x + y * w];
                tempR[5] = pixel[x + 1 + y * w];
                tempR[6] = pixel[x - 1 + (y + 1) * w];
                tempR[7] = pixel[x + (y + 1) * w];
                tempR[8] = pixel[x + 1 + (y + 1) * w];
                // median value
                Arrays.sort(tempR);
                newPixel[y * w + x] = tempR[4];
            }
        }
        return newPixel;
    }

    /**
     * http://events.jianshu.io/p/964f68851abc
     *
     * @param pixel 一维数组
     * @param lie   一维数组转二维数组的列
     * @param hang  一维数组转二维数组的行
     */
    public static int[] medianFilteringForHet(int[] pixel, int lie, int hang) {
        mLie = lie;
        int[] newPixel = new int[pixel.length];
        StringBuffer bufferOld = new StringBuffer();
        StringBuffer bufferNew = new StringBuffer();
        for (int y = 0; y < hang; y++) {
            boolean logMark = y == 0 || y == 10 || y == 20 | y == 30;
            if (logMark) {
                bufferOld.append("\n 行 " + (y + 1));
                bufferNew.append("\n 行 " + (y + 1));
            }
            for (int x = 0; x < lie; x++) {
//                MyLog.e("遍历处理后数组：y " + y + ", x " + x);
                int[] filter = new int[9];
                int item = pixel[x + y * lie]; // 当前item
                if (logMark) bufferOld.append("      " + item);
                for (int i = 0; i < filter.length; i++) {
                    int ux = i % 3 + x - 1; // i % 3 ; 列，已当前(x,y)中心坐标，画一个3*3。
                    int uy = y + i / 3 - 1; // i / 3 ; 行，已当前(x,y)中心坐标，画一个3*3。
                    if (ux < 0 || ux >= lie || uy < 0 || uy >= hang) {
                        // 首尾列(行)，过滤数组下标越界，值默认0；
                        continue;
                    }
                    filter[i] = pixel[ux + uy * lie];
//                    if (y ==8 || y == 9|| y == 10) {
//                        MyLog.w("" + item + ", filter： " + filter[i]);
//                    }
                }
                // 过滤3*3数组排序
                Arrays.sort(filter);
                // 中值赋值
                newPixel[x + y * lie] = filter[4]; // 要用一个新数组接值。否则影响下一个循环的的过滤
                if (logMark) bufferNew.append("     " + newPixel[x + y * lie]);
            }
        }
        MyLog.e("原数组： " + bufferOld.toString());
        MyLog.e("中值滤波后数组： " + bufferNew.toString());
        return newPixel;
    }

    /**
     * 将小于目标值的去掉(置0)
     *
     * @param pixel  一维数组
     * @param minTag 最小目标值
     */
    public static boolean minFilteringForHet(int[] pixel, int minTag) {
        int max = 0; // 中值滤波中记录最大值，但最大值是0时表示无人
        for (int i = 0; i < pixel.length; i++) {
            int item = pixel[i];
            if (item < minTag && item != 0) {
                pixel[i] = 0;
            }
            if (item > max) max = item;
        }
        if (max == 0) {
            return false;
        }
//        MyLog.e("遍历处理后数组： " + Arrays.toString(pixel));
        return true;
    }

    /**
     * @param pixels 一维数组
     */
    public static float[] histogramForHet(int[] pixels) {
        // 记录灰度值出现个数
        double[] count = new double[256];
        StringBuffer buffer = new StringBuffer();
        for (int k = 0; k < count.length; k++) {
            for (int i = 0; i < pixels.length; i++) {
                if (k == 0) {
                    if (i % mLie == 0) buffer.append("\n 行 " + (i / mLie + 1));
                    buffer.append("   " + pixels[i]);
                }
                if (pixels[i] == k) {
                    // 累计灰度值个数
                    count[k] += 1;
                }
            }
//            if (count[k] != 0) MyLog.i("灰度值： " + k + ", 个数 " + count[k]);
            if (k == count.length - 1) MyLog.e("遍历原数组(滤掉最小指线15)：" + buffer.toString());
        }
        // 计算各灰度值出现概率
        float[] Pr = new float[count.length];
        // 对应灰度值概率累计; 第一个灰度值的是0.03 = 0 + 0.03; 第二个灰度值的 0.23 = 0.2 + 0.3; ...
        float[] sk = new float[Pr.length];
        for (int i = 0; i < Pr.length; i++) {
            Pr[i] = (float) (count[i] / (float) pixels.length);
            for (int j = 0; j <= i; j++) {
                sk[i] += Pr[j];
            }
            if (count[i] != 0) {
//                MyLog.i("计算各灰度值出现概率, 灰度值 i " + i + ", 次数 " + count[i] + ", 概率 " + Pr[i] + ", 累计概率 " + sk[i]);
            }
        }
        StringBuffer buffer2 = new StringBuffer();
        float[] newPixels = new float[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            if (i % mLie == 0) {
                buffer2.append("\n 行 " + (i / mLie + 1));
            }
            newPixels[i] = sk[pixels[i]];
            buffer2.append("  " + String.format("%.3f", newPixels[i]));
        }
        MyLog.e("直方图均衡化的数组： " + buffer2.toString());
        return newPixels;
    }

    /**
     * 标准化输出值 = (每个输出值 - 该帧所有输出值的最小值) / (该帧所有输出值的最大值 - 该帧所有输出值的最小值)
     *
     * @param pixel 一维数组, 如果是返回空数组则表示最大值是0，即是无人。
     */
    public static float[] standardization(float[] pixel) {
        float min = 1.0f;
        float max = 0.0f;
        for (int i = 0; i < pixel.length; i++) {
            float item = pixel[i];
            if (item > max) max = item;
            if (item < min && item > 0) min = item;
        }
        if (max - min == 0) {
            return new float[0];
        }
        float[] newPixel = new float[pixel.length];
        StringBuffer buffer2 = new StringBuffer();
        for (int i = 0; i < pixel.length; i++) {
            if (i % 16 == 0) {
                buffer2.append("\n 行 " + (i / mLie + 1));
            }
            newPixel[i] = (pixel[i] - min) / (max - min);
            buffer2.append("  " + String.format("%.3f", newPixel[i]));
        }
        MyLog.e("标准化的数组： " + buffer2.toString());
        return newPixel;
    }

    public static Recognition recognizePosition(HetClassifier hetClassifier, @NotNull int[] ps, int lie, int hang) {
        int[] medianFilter = BitmapUtil.medianFilteringForHet(ps, lie, hang);
        boolean isValid = BitmapUtil.minFilteringForHet(medianFilter, 15);
        if (!isValid) {
            return new Recognition(0, "无人", 1.0f);
        }
        float[] histogramForHet = BitmapUtil.histogramForHet(medianFilter);
        float[] standardization = BitmapUtil.standardization(histogramForHet);
        if (hetClassifier == null) {
            MyLog.e("onClick: hetClassifier == null");
            return new Recognition(-1, "未知", -1.0f);
        }
        return hetClassifier.recognizePosition(standardization);
    }
}

