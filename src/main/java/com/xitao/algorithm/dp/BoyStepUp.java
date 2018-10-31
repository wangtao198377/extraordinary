package com.xitao.algorithm.dp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BoyStepUp {

    /**
     * 小孩上台阶问题，小孩上台阶，一次可以上1个 2个  3个台阶，要上n个台阶，有多少种方式？
     * 有两种解法
     * 1 递归 时间效率很差
     * 2. 动态规划，动态规划简单的说就是递归每次的结果缓存起来，避免重复计算。
     */

    //普通的递归实现
    public static int recursive(int n) {
        if(n<=0) {
            return 0;
        }
        if(n==1) {
            return 1;
        } else if(n==2) {
            return 2;
        } else if(n==3) {
            return 4;
        }

        return recursive(n-3) + recursive(n-2) +recursive(n-1);

    }

    //循环非递归的实现
    public static int loop(int n) {
        if(n<=0) {
            return 0;
        }
        if(n==1) {
            return 1;
        } else if(n==2) {
            return 2;
        } else if(n==3) {
            return 4;
        }

        int first = 1;
        int second = 2;
        int third =4;
        int result = 0;

        for(int i=4;i<=n;i++) {
            result  = first+second+third;
            first = second;
            second = third;
            third = result;
        }
        return result;
    }

    //动态规划递归的实现 保存之前计算的结果

    public static int dp(int n,Map<Integer,Integer> cache) {
        if(n<=0) {
            return 0;
        }
        if(n==1) {
            return 1;
        } else if(n==2) {
            return 2;
        } else if(n==3) {
            return 4;
        }
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,4);
        Integer value= null;
        if((value = cache.get(Integer.valueOf(n))) != null) {
            return value;
        } else {
             value = dp(n-3,cache)+dp(n-2,cache)+dp(n-1,cache);
             cache.put(n,value);
             return value;
        }
    }



    @Test
    public void testResult() {
        System.out.println(BoyStepUp.recursive(36));
    }

    @Test
    public void testResult1() {
        System.out.println(BoyStepUp.loop(36));

    }

    @Test
    public void testResult2() {

        Map<Integer,Integer> cache = new HashMap<>();
        System.out.println(BoyStepUp.dp(36,cache));
    }

    @Test
    public void testResult3() {

        System.out.println(3+476);
    }


}
