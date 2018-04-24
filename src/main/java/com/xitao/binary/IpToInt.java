package com.xitao.binary;

import org.springframework.util.StringUtils;

public class IpToInt {
    public static void main(String[] args) {
        String aa="10.12.25.12";
        String[] strarr = aa.split("\\.");
        int[] intarr= new int[]{0x1000,0x0100,0x0010,0x0001};
        int result=0;
        //最简单的实现方式
        for(int i=0;i<strarr.length;i++) {
            int temp = Integer.valueOf(strarr[i]);
            result += temp<<(3-i)*8;
        }
        //1010 00001100 00011001 00001100
        System.out.println(Integer.toBinaryString(result));
        System.out.println(Integer.toHexString(result));
        System.out.println(Integer.toOctalString(result));

    }
}
