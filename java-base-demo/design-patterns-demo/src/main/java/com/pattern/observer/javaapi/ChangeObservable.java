package com.pattern.observer.javaapi;

import java.util.Observable;

public class ChangeObservable extends Observable{
    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
