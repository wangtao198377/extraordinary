package com.xitao.mapTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String args[]) {
        System.out.println("1111111");
        Map<String, String> map = new ConcurrentHashMap<>(18);
        map.put("key11", "value1");
        map.get("key11");

        map.put("key11", "valueMods");

        for (int i=0;i<13;i++) {
            map.put("key"+i,"value"+i);
        }
    }
}