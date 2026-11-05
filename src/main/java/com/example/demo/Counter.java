package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component
public class Counter {
    private int value;
    public Counter(){
        System.out.println("Counter создан: " + System.identityHashCode(this));
    }
}
