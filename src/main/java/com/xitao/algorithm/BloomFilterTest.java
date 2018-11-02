package com.xitao.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BloomFilterTest {
    public static void main (String args[]) throws Exception{
        int size = 100000000;
        BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.01);

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        Thread.sleep(1000);
        List<Integer> list = new ArrayList<Integer>(1000);

        long start =System.nanoTime();
        bloomFilter.mightContain(111111111);
        System.out.println("time::"+(System.nanoTime() -start));
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {

            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());

    }


}
