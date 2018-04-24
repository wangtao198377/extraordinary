package com.xitao.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements  Runnable {

    private ReentrantLock lock = new ReentrantLock();

    public ReentrantLock getLock() {

        return lock;
    }

//    public void run () {
//        try {
//            lock.lock();
//            System.out.println("Thread-" + Thread.currentThread().getName() + " hold lock");
//            for(;;) {
//                try {
//                    Thread.sleep(5000);
//                } catch (Exception e) {
//
//                }
//            }
//        } finally {
//             lock.unlock();
//            System.out.println("Thread-" + Thread.currentThread().getName() + " release lock");
//        }
//
//    }

    public void run () {
        try {
            lock.lock();
            lock.lock();
            System.out.println("Thread-" + Thread.currentThread().getName()+"-"+lock.getHoldCount());

            System.out.println("Thread-" + Thread.currentThread().getName() + " hold lock");
            for(;;) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {

                }
            }
        } finally {
            lock.unlock();
            System.out.println("Thread-" + Thread.currentThread().getName() + " release lock");
        }

    }

    public static void  main(String args[]) throws Exception {

        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();

        new Thread(reentrantLockTest,"thread-111").start();
        Thread.sleep(100L);
        new Thread(reentrantLockTest,"thread-222").start();

        new Thread(reentrantLockTest,"thread-333").start();
        Thread.sleep(5000L);

        ReentrantLock lock = reentrantLockTest.getLock();
        System.out.println(lock.getHoldCount());
        System.out.println(lock.getQueueLength());


    }

}
