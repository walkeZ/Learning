package com.walke.unittest;

import com.walke.unittest.junit.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by walke.Z on 2018/6/6.
 *
 * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass; 每一个测试方法的调用顺序为： @Before -> @Test -> @After;
 * 调用顺序：@Before -> @Test ->XX->@Before-@Test【next】
 *          walke: CalculatorTest.setup:------>
 *          walke: CalculatorTest.testAdd:------> sum = 7
 *          walke: CalculatorTest.setup:------>
 *          java.lang.AssertionError:
 *          Expected :9
 *          Actual   :10
 *          <Click to see difference>
 *          at org.junit.Assert.fail(Assert.java:88)
 *          walke: CalculatorTest.setup:------>
 *          walke: CalculatorTest.setup:------>
 *          walke: CalculatorTest.testMultiply:------> multiply = 10
 *
 *          Process finished with exit code -1
 *
 *   参考：https://www.jianshu.com/p/62dfbd6ea077
 */

public class CalculateTest {
    private Calculator mCalculator;


    /**
     *  1. @Test : 测试方法，测试程序会运行的方法，后边可以跟参数代表不同的测试，如(expected=XXException.class) 异常测试，(timeout=xxx)超时测试
     *
     *   2. @Ignore : 被忽略的测试方法
     *
     *   3. @Before: 每一个测试方法之前运行
     *
     *   4. @After : 每一个测试方法之后运行
     *
     *   5. @BeforeClass: 所有测试开始之前运行
     *
     *   6. @AfterClass: 所有测试结束之后运行
     *
            fail方法是指测试失败

            assertEquals测试2个参数是否相等
     */
    @Before
    public void setup() {
        if (mCalculator == null)
            mCalculator = new Calculator();
        System.out.println("walke: CalculatorTest.setup:------> ");
    }

    @Test
    public void testAdd() {
        int sum = mCalculator.add(2, 5);
        Assert.assertEquals(7, sum);// JUnit 的工具类，测试、校验目标(方法)
        System.out.println("walke: CalculatorTest.testAdd:------> sum = " + sum);
    }

    @Test
    public void testMultiply() {
        int multiply = mCalculator.multiply(2, 5);
        Assert.assertEquals(10, multiply);
        System.out.println("walke: CalculatorTest.testMultiply:------> multiply = " + multiply);
    }


    @Test
    public void testMultiply2() {
        int multiply = mCalculator.multiply(2, 5);
        Assert.assertEquals(9, multiply);// AssertionError: org.junit.Assert.failNotEquals
        System.out.println("walke: CalculatorTest.testMultiply2:------> multiply = " + multiply);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivide() {
        double divide = mCalculator.divide(2, 0);// 测试抛出异常
        System.out.println("walke: CalculatorTest.testDivide:------> divide = " + divide);
    }

}
