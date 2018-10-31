package com.xitao.common;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal bignum1 = new BigDecimal("10");
        BigDecimal bignum2 = new BigDecimal("5");
        BigDecimal bignum3 = null;

//加法
        bignum3 =  bignum1.add(bignum2);
        System.out.println("和 是：" + bignum3);

//减法
        bignum3 = bignum1.subtract(bignum2);
        System.out.println("差  是：" + bignum3);

//乘法
        bignum3 = bignum1.multiply(bignum2);
        System.out.println("积  是：" + bignum3);

//除法
        bignum3 = bignum1.divide(bignum2);
        System.out.println("商  是：" + bignum3);

    }
}
