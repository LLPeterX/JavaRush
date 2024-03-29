package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/*
Создание прокси-объекта
*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        //return null;
        SomeInterfaceWithMethods originalClass = new SomeInterfaceWithMethodsImpl();
        //SomeInterfaceWithMethods proxy = (SomeInterfaceWithMethods)Proxy.newProxyInstance(new CustomInvocationHandler(originalClass));
        ClassLoader loader = originalClass.getClass().getClassLoader();
        Class<?>[] interfaces = originalClass.getClass().getInterfaces();
        InvocationHandler handler = new CustomInvocationHandler(originalClass);
        SomeInterfaceWithMethods proxy = (SomeInterfaceWithMethods)Proxy.newProxyInstance(loader, interfaces,handler);
        return proxy;
    }
}