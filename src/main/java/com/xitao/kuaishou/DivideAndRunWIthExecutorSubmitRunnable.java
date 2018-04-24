package com.xitao.kuaishou;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

public class DivideAndRunWIthExecutorSubmitRunnable {
    private List<Integer> target;
    private Integer threadNum;
    //private CountDownLatch latch;

    DivideAndRunWIthExecutorSubmitRunnable(List<Integer> target, Integer threadNum) {
        this.target=target;
        this.threadNum = threadNum;
        //latch = new CountDownLatch(threadNum);
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

        List<Future<Boolean>> futures = new ArrayList<>();
        List<DivideClass> divideClasses = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<result.size();i++) {
            DivideClass divideClass = new DivideClass(result.get(i));
            Future<Boolean> future = executorService.submit(divideClass,true);
            futures.add(future);
            divideClasses.add(divideClass);

        }
        Integer resultSum =0;
        for (int i=0;i<futures.size();i++) {
            if(futures.get(i).get()) {
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
        for(int i=0;i<13;i++) {
            ls.add(i);
        }
        DivideAndRunWIthExecutorSubmitRunnable divideAndRunByMultiThread = new DivideAndRunWIthExecutorSubmitRunnable(ls,10);

        Integer sum = divideAndRunByMultiThread.sumValue();
        System.out.println(sum);
    }
}
