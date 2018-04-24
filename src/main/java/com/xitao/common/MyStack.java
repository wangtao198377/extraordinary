package com.xitao.common;

import java.util.Arrays;

public class MyStack {
    private int capacity =4;
    private Object[] arr= new Object[capacity];
    private int size = 0;
    public MyStack() {

    }

    public synchronized void push(Object ob) {
        if(size <capacity) {
            arr[size++] = ob;
        } else {
            capacity = capacity*2;
            Object[] newArr = new Object[capacity];
            System.arraycopy(arr,0,newArr,0,size);
            arr=newArr;
            arr[size++]=ob;
        }
    }

    public synchronized Object pop()  throws Exception {
        if(size >0) {
           Object  ob = arr[--size];
           arr[size] = null;
           return ob;
        } else {
            throw new Exception("empty stack");
        }
    }

    public static void main(String[] args) throws Exception {
        MyStack stack = new MyStack();
        for(int i=0;i<9;i++) {
            stack.push(Integer.valueOf(i));
        }

        for(int i=0;i<10;i++) {
           Object ob = stack.pop();
            System.out.println(ob);

        }
    }
}
