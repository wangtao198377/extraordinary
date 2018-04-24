package com.xitao.nio;

public class ReactorTest {

    public static void main(String[] args) throws Exception {
        new Thread(new Reactor(7777)).start();
    }
}
