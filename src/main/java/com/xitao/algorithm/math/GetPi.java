package com.xitao.algorithm.math;

import org.codehaus.groovy.util.AbstractConcurrentDoubleKeyMap;

import java.util.Random;

public class GetPi {

    public static void main(String[] args) {

        Long count =0L;
        Long max = 1000000000L;
        for(int i=0;i<max;i++) {
            double xposition = Math.random();
            double ypositon = Math.random();
            if(Math.pow(xposition,2)+Math.pow(ypositon,2) <=1) {
                count++;
            }
        }
        System.out.println(4*(double) count/(double)max);


    }
}
