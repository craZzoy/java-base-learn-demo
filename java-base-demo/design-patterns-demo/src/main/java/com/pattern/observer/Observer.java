package com.pattern.observer;

/**
 * 观察者接口，订阅
 */
public interface Observer {
    void update(NumberGenerator generator);
}
