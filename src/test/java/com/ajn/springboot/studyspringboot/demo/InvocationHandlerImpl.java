package com.ajn.springboot.studyspringboot.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {

    private Object subject;

    public InvocationHandlerImpl(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before:method: " + method);
        Object returnValue = method.invoke(subject, args);
        System.out.println("after: " + returnValue);
        return returnValue;
    }
}
