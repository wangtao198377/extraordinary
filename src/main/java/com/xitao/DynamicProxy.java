package com.xitao;

import com.xitao.sort.BubbleSort;
import com.xitao.sort.Sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class DynamicProxy {
    public static Object getProxy(Object target) {
        Objects.requireNonNull(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(proxy.getClass().getName());
                        Long start = System.currentTimeMillis();
                        Object result = method.invoke(target, args);
                        Long end = System.currentTimeMillis();
                        System.out.println("time:"+(end-start));
                        return result;
                    }
                }
        );
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        Sort proxy = (Sort)DynamicProxy.getProxy(bubbleSort);
        proxy.sort(new int[]{3,7,9});

    }
}
