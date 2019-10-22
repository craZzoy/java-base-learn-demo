package com.pattern.observer.javaapi;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        ChangeObservable observable = new ChangeObservable();
        observable.addObserver(new ChangeObserver());
        observable.setChanged();
        observable.notifyObservers("publsh message");
    }
}
