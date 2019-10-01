package com.pattern.observer;

public class ObserverOne implements Observer{
    @Override
    public void update(NumberGenerator generator) {
        System.out.println("i am observerOne,i see number is change to:"+generator.getNumber());
    }
}
