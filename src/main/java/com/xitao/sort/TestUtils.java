package com.xitao.sort;

import java.util.Arrays;
import java.util.Random;

public class TestUtils {

    public static int[] createRandomArray(int length) {
        int[] result = new int[length];
        for(int i=0;i<length;i++) {
            result[i] = new Random().nextInt(200);
        }
        return result;
    }

    public static int[] createRandomArray(int length,int max) {
        int[] result = new int[length];
        for(int i=0;i<length;i++) {
            result[i] = new Random().nextInt(max);
        }
        return result;
    }

    public static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++) {
            sb.append(arr[i]);
            sb.append(",");
        }

        System.out.println(sb.toString());
    }

    public static void test (Sort sort) {
        int[] arr= createRandomArray(10);
        printArray(arr);
        sort.sort(arr);
        printArray(arr);
    }
    public static void test (Sort sort, int num) {
        int[] arr= createRandomArray(num);
        int[] arr2 =
                new int[num];
        System.arraycopy(arr,0,arr2, 0, num);
        //int[] arr=new int[]{4,7,5,29,3,20,4,3,7,7};
        //printArray(arr);
        //System.out.println();
//        long start2 = System.currentTimeMillis();
//        Arrays.sort(arr);
//        long end2 = System.currentTimeMillis();
//        long result2 = end2 - start2;

        long start = System.currentTimeMillis();
        sort.sort(arr2);
        long end = System.currentTimeMillis();
        long result1 = end - start;

        System.out.println("mine rsult:"+result1);
  //      System.out.println("jdk rsult:"+result2);


        //printArray(arr);
    }

    public static void test (Sort sort, int num,int max) {
        int[] arr= createRandomArray(num,max);
        int[] arr2 =
                new int[num];
        System.arraycopy(arr,0,arr2, 0, num);
        //int[] arr=new int[]{4,7,5,29,3,20,4,3,7,7};
        //printArray(arr);
        //System.out.println();
        long start2 = System.currentTimeMillis();
        Arrays.sort(arr);
        long end2 = System.currentTimeMillis();
        long result2 = end2 - start2;

        long start = System.currentTimeMillis();
        sort.sort(arr2);
        long end = System.currentTimeMillis();
        long result1 = end - start;

        System.out.println("mine rsult:"+result1);
        System.out.println("jdk rsult:"+result2);


        //printArray(arr);
    }

    public static void  swapInt(int[] arr, int from, int to) {
        int temp = arr[to];
        arr[to] = arr[from];
        arr[from] = temp;
    }
}
