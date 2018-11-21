package com.xitao;

import org.apache.ibatis.cache.decorators.SynchronizedCache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStatus {
    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(new StateTest(lock));
        System.out.println(thread1.getState());
        thread1.start();
        Thread thread2 = new Thread(new StateTest(lock));
        System.out.println(thread2.getState());
        thread2.start();
        while(true) {
            Thread.sleep(1000);
            System.out.println("thread1:;"+thread1.getState());
            System.out.println("thread2:;"+thread2.getState());
        }
    }

     static class StateTest implements  Runnable {
        Lock lock;
        Long sum =0L;
         public StateTest(Lock lock) {
             this.lock =lock;
         }
         @Override
         public void run() {
             synchronized (lock) {
                 while (true) {
                     try {
                         Thread.sleep(2000);

                     } catch (Exception e) {

                     }
                     sum++;
                 }
             }
         }
     }
}
