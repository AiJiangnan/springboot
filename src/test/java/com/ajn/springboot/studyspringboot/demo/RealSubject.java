package com.ajn.springboot.studyspringboot.demo;

public class RealSubject implements Subject {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public String sayGoodBye() {
        return "good bye";
    }
}
