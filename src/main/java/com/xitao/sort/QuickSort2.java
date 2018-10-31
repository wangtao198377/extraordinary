package com.xitao.sort;

import org.junit.Test;

public class QuickSort2 {

    public static void sort(int[] arr, int start,int end) {

        if (start >= end) return;

        int i=start;
        int j=end;
        int position =i;
        boolean inLeft = true;
        int compare=arr[i];

        while(i<j) {
            if(inLeft) {
                if(compare <= arr[j]) {
                    j--;
                } else {
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                    i++;
                    position =j;
                    inLeft =false;
                }
            } else {
                if(arr[i] <=compare) {
                    i++;
                } else  {
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                    j--;
                    position =i;
                    inLeft =true;
                }
            }
        }
        sort(arr,start,position-1);
        sort(arr,position+1,end);

    }
    @Test
    public void test() {
        int[] arr= TestUtils.createRandomArray(100);
        TestUtils.printArray(arr);

        QuickSort2.sort(arr,0,arr.length-1);
        TestUtils.printArray(arr);
    }
}
