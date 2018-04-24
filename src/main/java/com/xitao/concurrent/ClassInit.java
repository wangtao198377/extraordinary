package com.xitao.concurrent;

public class ClassInit  {


    public  ClassInit() {
        System.out.println(this);


        try {
            Thread.sleep(5000);
        } catch(Exception e) {

        }


    }


    public static  void main(String args[]) throws  Exception {

        ClassInit instance = null;

        instance = new ClassInit();

        new Demo(instance).start();

    }

}

class Demo extends Thread {
    ClassInit instance = null;
    public Demo(ClassInit classInit) {
        instance = classInit;
    }
    public void run() {
        for(;;) {
            try {
                Thread.sleep(1000);
            } catch(Exception e) {

            }
            System.out.println("thread-"+instance);
        }

    }
}



