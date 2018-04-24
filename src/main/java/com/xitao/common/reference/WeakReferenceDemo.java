package com.xitao.common.reference;

import com.xitao.mybatis.Test1DO;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static  void main(String args[]) {
        Test1DO test1DO = new Test1DO();


        WeakReference weakReference = new WeakReference(test1DO);
        test1DO = null;
        System.gc();
        try {
            Thread.sleep(5000L);
        } catch (Exception e) {

        }
        if(weakReference.get() != null) {
            System.out.println("haven't gc::"+weakReference.get());
        } else {
            System.out.println("has been gc");
        }

    }


}
