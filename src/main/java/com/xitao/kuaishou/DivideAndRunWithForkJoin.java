package com.xitao.kuaishou;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 *  从1到100   100个数
 *  用十个线程计算出来 然后再进行汇总
 *  这个问题有很多的实现方式
 *  打算写出所有的实现方式来
 *
 *  第一步写出 用java线程  线程池 Future Task Callable Fork Join CountDownLatch 等方式实现的
 *  第二步  用Hadoop的话怎么实现   当学习大数据处理相关知识的时候再来实现
 *  此类用 join 方法来等待
 */

public class DivideAndRunWithForkJoin extends RecursiveTask<Integer> {
    private List<Integer> target;
    private Integer threshold;
    private Integer threadNum =2;
    //private CountDownLatch latch;

    DivideAndRunWithForkJoin(List<Integer> target, Integer threshold) {
        this.target=target;
        this.threshold = threshold;
        //latch = new CountDownLatch(threadNum);
    }

    @Override
    public Integer compute() {
        if(target.size() <= threshold) {
            Integer sum =0;
            for(int i=0;i<target.size();i++) {
                sum+=target.get(i);
            }
            return sum;
        } else {
            int mid = (target.size())/2;
            List<Integer> ls1= target.subList(0,mid);
            List<Integer> ls2= target.subList(mid,target.size());
            DivideAndRunWithForkJoin divideAndRunWithForkJoin1 = new DivideAndRunWithForkJoin(ls1,threshold);
            DivideAndRunWithForkJoin divideAndRunWithForkJoin2 = new DivideAndRunWithForkJoin(ls2,threshold);
            invokeAll(divideAndRunWithForkJoin1,divideAndRunWithForkJoin2);
            Integer sum1 = divideAndRunWithForkJoin1.join();
            Integer sum2 = divideAndRunWithForkJoin2.join();
            return sum1+sum2;
        }
    }

    public Integer sumValue() throws Exception {
        int partNum = target.size()/threadNum;

        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<threadNum;i++) {
            List<Integer> partLs = new ArrayList<>();
            if(i != threadNum-1) {
                partLs = target.subList(i*partNum,(i+1)*partNum);
            } else {
                partLs = target.subList(i*partNum,target.size());
            }
            result.add(partLs);
        }

        List<Future> futures = new ArrayList<>();
        List<DivideClass> divideClasses = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<result.size();i++) {
            DivideClass divideClass = new DivideClass(result.get(i));
            Future future = executorService.submit(divideClass);
            futures.add(future);
            divideClasses.add(divideClass);

        }
        Integer resultSum =0;
        for (int i=0;i<futures.size();i++) {
            if(futures.get(i).get() ==null) {
                resultSum +=divideClasses.get(i).getSum();
            }
        }
        return resultSum;
    }


    class DivideClass implements Runnable {
        private Integer sum = 0;
        private List<Integer> targetList = null;
        //private CountDownLatch latch = null;
        DivideClass(List<Integer> targetList) {
            this.targetList = targetList;
            //this.latch=latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                for (int i = 0; i < targetList.size(); i++) {
                    sum = sum + targetList.get(i);
                }
            } catch (Exception e) {

            }
        }

        public Integer getSum() {
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> ls = new ArrayList<>();
        for(int i=0;i<101;i++) {
            ls.add(i);
        }
//        DivideAndRunWithForkJoin divideAndRunByMultiThread = new DivideAndRunWithForkJoin(ls,10);
//
//        Integer sum = divideAndRunByMultiThread.sumValue();
//        System.out.println(sum);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        DivideAndRunWithForkJoin divideAndRunWithForkJoin = new DivideAndRunWithForkJoin(ls,2);
        Future<Integer> future = forkJoinPool.submit(divideAndRunWithForkJoin);
        System.out.println(future.get());
    }
}
