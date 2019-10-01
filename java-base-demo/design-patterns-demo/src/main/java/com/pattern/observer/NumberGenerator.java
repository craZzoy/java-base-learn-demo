package com.pattern.observer;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class NumberGenerator {

    private List<Observer> observers = new ArrayList<>();


    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    /**
     * 发布订阅
     */
    public void notifyObservers(){
        Iterator<Observer> it = observers.iterator();
        Observer observer = null;
        while(it.hasNext()){
            observer = it.next();
            observer.update(this);
        }
    }

    //获取数值
    abstract int getNumber();

    //生成数值
    abstract void execute();
}
