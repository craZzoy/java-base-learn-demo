package com.pattern.observer;

import java.util.Random;

/**
 * 实际
 */
public class RandomNumberGenerator extends NumberGenerator{

    private Random random = new Random();
    private int number;


    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void execute() {
        for(int i = 0; i < 10; i++){
            number = random.nextInt(100);
            this.notifyObservers();
        }
    }
}
