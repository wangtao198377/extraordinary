package com.xitao.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) throws Exception,BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            cyclicBarrier.await();

                        } catch (InterruptedException | BrokenBarrierException e) {
                            System.out.println(e.getClass());

                        }

                    }
                }
        );
        thread.start();;
        thread.interrupt();
        try {
            cyclicBarrier.await();
        } catch(InterruptedException | BrokenBarrierException e) {
            System.out.println("main thread:"+e.getClass());
        }


    }
}
