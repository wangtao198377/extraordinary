package com.xitao.common;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws Exception
    {
        Thread thread = Thread.currentThread();

        LockSupport.unpark(new Thread());

        System.out.println("a");
        LockSupport.park();
        System.out.println("b");
        LockSupport.park();
        System.out.println("c");
    }

}
