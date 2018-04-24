package com.xitao.common;

import java.util.Arrays;

public class ManacherAlgorithm {

    public static void main (String args[]) {
        String src = "asdwdwd";
        char[] charArray =new  char[2*(src.length())+1];
        for(int i=0;i<charArray.length;i++) {
            if(i%2 ==1) {
                charArray[i] = src.charAt(i/2);
            } else {
                charArray[i] = '#';
            }
        }

        System.out.println(charArray);
        int[] indexWidth = new int[charArray.length];
        indexWidth[0] = 1;


        int maxIndex =0;
        int lastIndex = indexWidth.length -1;
        for(int i=1;i<indexWidth.length;i++) {
            //int maxIndex = getMaxIndex(indexWidth,start,i);

            int rightIndex = indexWidth[maxIndex]+maxIndex-1;
            if(i<rightIndex) {
                int leftIndex = 2*maxIndex-i;
                if(leftIndex >=0 ) {
                    if(indexWidth[leftIndex] <=rightIndex-i) {
                        indexWidth[i] = indexWidth[leftIndex];
                        continue;
                    } else {
                        int tempIndex = rightIndex+1;
                        int leftTempIndex = 2*i -tempIndex;
                        while( tempIndex<=lastIndex && leftTempIndex>=0 &&  (charArray[tempIndex] == charArray[leftTempIndex])) {
                            tempIndex++;
                            leftTempIndex--;
                        }
                        indexWidth[i] = tempIndex -i;
                        maxIndex=i;
                    }
                }
            } else {
                int start = i+1;
                int leftStart = i-1;
                while(start <lastIndex && leftStart >=0 && charArray[start] == charArray[leftStart]) {
                    start++;
                    leftStart--;
                }
                indexWidth[i] = start -i;
                maxIndex=i;
            }
        }

        System.out.println(Arrays.asList(indexWidth));

    }

    private static int getMaxIndex(int[] arr, int start,int end) {
        int maxvalue =arr[start];
        int index= start;
        for(int i=start+1;i<end;i++) {
            int temp = i+arr[i]-1;
            if(maxvalue<temp) {
                maxvalue = temp;
                index = i;
            }
        }
        return index;
    }

}
