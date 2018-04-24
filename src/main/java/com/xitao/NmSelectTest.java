package com.xitao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从n个整数中选出m个整数，打印出所有的组合。
 */
public class NmSelectTest {

    public  void selectM(List<Integer> integers, List<Integer> prefixes, int m,List<List<Integer>> resultList,int n) {
        int size = integers.size();
        if (m == 1) {
            for(Integer integer: integers) {
                List<Integer> tempList = new ArrayList();
                tempList.addAll(prefixes);
                tempList.add(integer);
                resultList.add(tempList);
            }
            prefixes= null;

        } else {

            for (int i = 0; i < integers.size(); i++) {
                if(integers.size() == n) {
                    prefixes = new ArrayList<>();
                }
                prefixes.add(integers.get(i));
                List<Integer> nextList = new ArrayList<>();
                nextList.addAll(integers.subList(0, i));
                nextList.addAll(integers.subList(i + 1, size));
                selectM(nextList, prefixes, m - 1,resultList,n);
            }
        }

    }

    public static void main(String[] args) {

    }



}
