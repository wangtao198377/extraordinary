package com.xitao.mapTest;

import java.util.HashMap;


//重现resize的时候死循环的问题

// 抛出  java.util.ConcurrentModificationException
//{3=3, 22=22, 7=7, 15=15}  数据丢失
//HashMap 改为ConcurrentHashMap可以完美的规避上述问题
public class TestHashMap {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(4, 0.75f);
        map.put(3, 3);
        map.put(7, 7);
        map.put(15, 15);
        new Thread(new myThread(map, 22)).start();
        new Thread(new myThread(map, 18)).start();
        System.out.println(map);
    }

}

class myThread implements Runnable {

    private HashMap<Integer, Integer> map;

    private Integer key;

    public myThread(HashMap<Integer, Integer> map, Integer key) {
        this.map = map;
        this.key = key;
    }

    @Override
    public void run() {
        map.put(key, key);
    }
}