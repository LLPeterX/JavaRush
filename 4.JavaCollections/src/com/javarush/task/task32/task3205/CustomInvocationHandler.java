package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods someClass;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //return null;
        System.out.println(method.getName()+" in");
        Object result = method.invoke(someClass,args);
        System.out.println(method.getName()+" out");
        return result;
    }
    // конструктор
    public CustomInvocationHandler(SomeInterfaceWithMethods someClass) {
        this.someClass = someClass;
    }
}
