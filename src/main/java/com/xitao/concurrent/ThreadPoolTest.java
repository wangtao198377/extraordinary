package com.xitao.concurrent;

import java.util.concurrent.*;

public class ThreadPoolTest {
    private static final int CAPACITY   = (1 << 29) - 1;
    public static  final int COUNT_BITS =29;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    // Packing and unpacking ctl
    private static int bb= ~CAPACITY;
    private static int aa= RUNNING & ~CAPACITY;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new
                ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,new SynchronousQueue<>());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.allowsCoreThreadTimeOut();
        System.out.println(-1 << 29);
    }
}
