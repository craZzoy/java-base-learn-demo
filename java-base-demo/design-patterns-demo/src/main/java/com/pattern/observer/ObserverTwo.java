package com.pattern.observer;

public class ObserverTwo implements Observer{
    @Override
    public void update(NumberGenerator generator) {
        System.out.println("i am observerTwo,i see number is change to:"+generator.getNumber());
    }
}
