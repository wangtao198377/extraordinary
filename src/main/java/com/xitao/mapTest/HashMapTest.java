package com.xitao.mapTest;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String args[]) {
        System.out.println("1111111");
        HashMap<String, String> map = new HashMap<>();
        map.put("key11", "value1");
        map.get("key11");

        map.put("key11", "valueMods");

        for (int i=0;i<13;i++) {
            map.put("key"+i,"value"+i);
        }
    }
}