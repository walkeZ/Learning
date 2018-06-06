package com.walke.unittest.junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by walke.Z on 2018/6/6.
 * https://www.jianshu.com/p/67c2f4ae9a6e
 */
public class Calculator2Test {
    private Calculator2 mCalculator2;
    @Before
    public void setUp() throws Exception {
        if (mCalculator2==null)
            mCalculator2=new Calculator2();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(6d, mCalculator2.sum(1d, 5d), 0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(4d,mCalculator2.divide(8d,2d),0);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(10d, mCalculator2.multiply(2d, 5d), 0);
    }

}