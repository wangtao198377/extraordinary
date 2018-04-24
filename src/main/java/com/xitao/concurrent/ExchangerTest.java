package com.xitao.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();
       ExecutorService executorService =  Executors.newFixedThreadPool(2);

       executorService.submit(new Runnable() {
           @Override
           public void run() {
               try {
               String test1 = "test1";
                String test2 = exchanger.exchange(test1);
                   System.out.println("thread1:"+test2);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });
       executorService.submit(new Runnable() {
           @Override
           public void run() {
               try {
                   String test2 ="test2";
                   String test1 = exchanger.exchange(test2);
                   System.out.println("thread2:"+test1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });

        executorService.shutdown();

    }
}
