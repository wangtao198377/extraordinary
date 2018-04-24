package com.xitao.common.reference;

import com.xitao.mybatis.Test1DO;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

public class StrongReferenceDemo {

    public static  void main(String args[]) {
        Test1DO test1DO = new Test1DO();
        WeakHashMap map = new WeakHashMap();
        map.put(test1DO,"w1");
        test1DO = null;
        System.gc();
        try {
            Thread.sleep(5000L);
        } catch (Exception e) {

        }
        if(map.containsKey(test1DO)) {
            System.out.println("haven't gc::"+map.get("aa"));
        } else {
            System.out.println("has been gc");
        }

    }
}
