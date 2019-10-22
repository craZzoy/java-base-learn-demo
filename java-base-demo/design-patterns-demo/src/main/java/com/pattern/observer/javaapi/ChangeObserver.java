package com.pattern.observer.javaapi;

import java.util.Observable;
import java.util.Observer;


public class ChangeObserver implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.toString()+"has change,the arg is" + arg.getClass());
    }
}
