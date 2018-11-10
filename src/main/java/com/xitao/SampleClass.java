package com.xitao;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SampleClass {
    public void test() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        SampleClass sampleClass = (SampleClass) SampleClass.getProxyByInvocationHandler(new SampleClass());
        sampleClass.test();

        SampleClass sampleClass2 = (SampleClass) SampleClass.getProxy(new SampleClass());
        sampleClass2.test();
    }

    public static Object getProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
               // Object result = proxy.invokeSuper(obj, args);
                Object result = method.invoke(target,args);
                System.out.println("after method run...");
                return result;
            }
        });
        return enhancer.create();
    }

    public static Object getProxyByInvocationHandler (Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println(o.getClass().getName());
                System.out.println(String.format("begin invoke method %s",method.getName()));
                Object obj = method.invoke(target,objects);
                System.out.println(String.format("end invoke method %s",method.getName()));
                return  obj;
            }
        });
        return enhancer.create();
    }
}
