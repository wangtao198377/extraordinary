package com.xitao.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java 多线程中。两个线程交替执行，一个输出偶数，一个输出奇数
 */

public class OddEvenTest {
    private static volatile int n = 1;

    private static Lock lock = new ReentrantLock();

    static class TestThread implements Runnable {
        private  int start = 0;
        private  int current = 0;
        private int step = 0;

        public TestThread(int start, int current, int step) {
            this.start = start;
            this.current = current;
            this.step = step;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                if (n == start) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+":"+current);
                    current = current + step;
                    if (n == 1) {
                        n = 2;
                    } else {
                        n = 1;
                    }
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        OddEvenTest.TestThread testThread1= new OddEvenTest.TestThread(1,1,2);
        OddEvenTest.TestThread testThread2= new OddEvenTest.TestThread(2,2,2);
        new Thread(testThread1,"test1").start();
        new Thread(testThread2,"test2").start();
    }


}
