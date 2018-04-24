package com.xitao.sort;

import java.util.Arrays;

public class MergeSort implements Sort {
    public void sort(int[] arr, int begin, int end) {

        int mid = (begin + end) / 2;

        if (begin + 1 == mid) {
            if(arr[begin] >arr[mid])
            TestUtils.swapInt(arr, begin, mid);

        }
        if (mid + 2 == end) {
            if(arr[mid+1] >arr[end])
                TestUtils.swapInt(arr, mid + 1, end);
        }
        if(mid>begin+1) {
            sort(arr, begin, mid);
        }
        if(end>mid+2) {
            sort(arr, mid + 1, end);
        }
        merge(arr, begin, mid, mid + 1, end);

    }

    public static void merge(int[] arr, int from1, int to1, int from2, int to2) {
        int[] tempArr = new int[to2-from1+1];
        int firstIndex = from1;
        int secondIndex = from2;
        int currentIndex = 0;

        while (firstIndex <=to1 && secondIndex <= to2) {
            if(arr[firstIndex] <= arr[secondIndex]) {
                tempArr[currentIndex] = arr[firstIndex];
                if(firstIndex==to1)  {
                    firstIndex++;
                    break;
                } else {
                    firstIndex++;
                    currentIndex++;

                }
            } else {
                tempArr[currentIndex] = arr[secondIndex];
                if(secondIndex==to2)  {
                    secondIndex++;
                    break;
                } else {
                    secondIndex++;
                    currentIndex++;
                }
            }
        }

        if(firstIndex> to1) {
            currentIndex++;
            while(currentIndex<=to2-from1) {
                tempArr[currentIndex] = arr[secondIndex];
                secondIndex++;
                currentIndex++;
            }

        } else if(secondIndex > to2) {
            currentIndex++;
            while(currentIndex<=to2-from1) {
                tempArr[currentIndex] = arr[firstIndex];
                firstIndex++;
                currentIndex++;
            }

        }
        System.arraycopy(tempArr,0,arr,from1,tempArr.length);
    }

    public void sort(int[] arr) {
        sort(arr,0,arr.length-1);

    }

    public static void main(String[] args) {
        TestUtils.test(new MergeSort());
    }


}
