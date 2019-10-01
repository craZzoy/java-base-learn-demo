package com.pattern.observer;


public class Main {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        Observer o1 = new ObserverOne();
        Observer o2 = new ObserverTwo();
        numberGenerator.addObserver(o1);
        numberGenerator.addObserver(o2);
        numberGenerator.execute();
    }
}
