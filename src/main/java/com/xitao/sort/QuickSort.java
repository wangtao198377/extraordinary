package com.xitao.sort;


//好吧    1000万个整数的情况下，哥的排序算法比jdk提供的快   哥也不知道为啥
//然后1000万个就比不过了
public class QuickSort implements Sort {

    public void sort(int[] arr, int from, int end) {
        if (from >= end) return;

        int beginIndex = from;
        int endIndex = end;
        int compare = arr[from];
        int currentIndex = from;
        boolean isLeft = true;
        while (endIndex > beginIndex) {
            if (isLeft) {
                if(arr[endIndex] <compare) {
                    TestUtils.swapInt(arr,currentIndex,endIndex);
                    isLeft=false;
                    beginIndex++;
                    currentIndex = endIndex;
                } else {
                    endIndex--;
                }
            } else {
                if(arr[beginIndex] >compare) {
                    TestUtils.swapInt(arr,beginIndex,currentIndex);
                    currentIndex = beginIndex;
                    isLeft=true;
                    endIndex--;
                } else {
                    beginIndex++;
                }
            }
        }
        sort(arr,from,currentIndex-1);
        sort(arr,currentIndex+1,end);

    }

    public void sort(int[] arr) {
        sort(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        TestUtils.test(new QuickSort(),10000);
    }
}
