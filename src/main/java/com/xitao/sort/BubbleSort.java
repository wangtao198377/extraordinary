package com.xitao.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * 形象的认为是气泡从水的底部往上走，越来越大，直到破裂。
 * 先把最大的数弄到数组最后面
 */

public class BubbleSort implements Sort {
    public void sort(int[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println((1<<4)-1);
        TestUtils.test(new BubbleSort());
    }
}
