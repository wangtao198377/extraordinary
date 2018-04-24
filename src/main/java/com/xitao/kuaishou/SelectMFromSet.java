package com.xitao.kuaishou;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class SelectMFromSet {

    public static HashSet<HashSet<Integer>> select(HashSet<Integer> originalSet, int m) {
        int n = originalSet.size();
        if(m>n) return null;
        if(m==1 ) {
            HashSet<HashSet<Integer>> resultSet = new HashSet<>();

            Iterator<Integer> iterator  = originalSet.iterator();
            while(iterator.hasNext()) {
                Integer temp = iterator.next();
                HashSet subset =new HashSet();
                subset.add(temp);
                resultSet.add(subset);
            }
            return resultSet;
        } else {
            HashSet<HashSet<Integer>> preSet = select(originalSet,m-1);
            Iterator<HashSet<Integer>> hashSetIterator = preSet.iterator();
            HashSet<HashSet<Integer>> resultSet = new HashSet<>();

            while(hashSetIterator.hasNext()) {
                HashSet<Integer> item = hashSetIterator.next();
                HashSet<Integer> others = new HashSet<>();
                others =  (HashSet<Integer>)(originalSet.clone());
                others.removeAll(item);
                for(Iterator<Integer> iterator = others.iterator(); iterator.hasNext();) {
                    Integer temp = iterator.next();
                    HashSet<Integer> newItem = (HashSet)item.clone();
                    newItem.add(temp);
                    resultSet.add(newItem);
                }
            }
            return resultSet;
        }

    }

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i=0;i<10;i++) {
            hashSet.add(i);
        }
        HashSet<HashSet<Integer>> result = select(hashSet,9);
        System.out.println(result);

    }

}
