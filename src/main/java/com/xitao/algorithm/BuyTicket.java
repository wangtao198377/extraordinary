package com.xitao.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一场球赛开始前,售票工作正在紧张的进行中.每张球票为50元,现有30个人排队等待购票,其中有20个人手持50元的钞票,另外10个人手持100元的钞票.假设开始售票时售票处没有零钱,求出这30个人排队购票,
 * 使售票处不至出现找不开钱的局面的不同排队种数
 */

public class BuyTicket {

    public static List<List<Integer>> solution(int n,Map<Integer,List<List<Integer>>> cache) {
        if(cache.get(Integer.valueOf(n)) != null) {
            return cache.get(Integer.valueOf(n));
        }
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            List<Integer> item = new ArrayList<>();
            item.add(1);
            result.add(item);
        } else {
            List<List<Integer>> lsall = solution(n - 1,cache);
            for (List<Integer> ls : lsall) {
                int sum = 0;
                int count = 0;
                for (int i = 0; i < ls.size(); i++) {
                    sum = sum + ls.get(i);
                    if (ls.get(i) == 1) {
                        count++;
                    }
                }

                List<Integer> temp1 = new ArrayList<>();
                temp1 = new ArrayList<>(ls);
                temp1.add(1);
                List<Integer> temp2 = new ArrayList<>();
                temp2 = new ArrayList<>(ls);
                temp2.add(-1);
                if (count < 20) {
                    if (sum > 0) {
                        result.add(temp1);
                        result.add(temp2);
                    } else {
                        result.add(temp1);
                    }
                } else {
                    result.add(temp2);
                }
            }
        }
        cache.put(Integer.valueOf(n),result);
        return result;
    }

    public static void main(String[] args) {
        Map<Integer,List<List<Integer>>> cache = new HashMap<>();
        List<List<Integer>> ls = solution(23,cache);
        System.out.println(ls);
        System.out.println(ls.size());

    }
}
