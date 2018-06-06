package com.walke.unittest.junit;

/**
 * Created by walke.Z on 2018/6/6.
 * 计算器
 */

public class Calculator {

    /**
     * 和
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 乘积
     *
     * @param a
     * @param b
     * @return
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * @param divident
     * @param dividor
     * @return
     */
    public double divide(double divident, double dividor) {
        if (dividor == 0) throw new IllegalArgumentException("Dividor cannot be 0");
        return divident / dividor;
    }


}
