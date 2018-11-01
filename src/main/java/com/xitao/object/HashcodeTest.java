package com.xitao.object;

import java.math.BigDecimal;

public class HashcodeTest {

    public static void main(String[] args) {
        Object ob = new Object();
        System.out.println(ob.hashCode());
        System.out.println(ob);

        BigDecimal aa= new BigDecimal(10);
        aa.subtract(new BigDecimal(5));
        System.out.println(aa);
    }
}
