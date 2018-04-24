package com.xitao.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

//  int a = 0;                 //1
//  int b = 1;                 //2
    public static volatile AtomicInteger c = new AtomicInteger(0);        //3
//  int d = 3;                 //4
//  int e = 4;                 //5


    public static void increase(){
        c.incrementAndGet();
        try {
            Thread.sleep(100);
        } catch(Exception e) {

        }
        c.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    increase();
                }
            }
            ).start();
        }
        Thread.sleep(5000);
        System.out.println(c);
    }
}