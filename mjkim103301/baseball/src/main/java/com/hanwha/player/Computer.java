package com.hanwha.player;

import java.util.Random;

public class Computer {

    private final int number;
    private final Random random = new Random();

    public Computer() {
        this.number = getRandomNumber();
    }

    private int getRandomNumber() {
        int randomNumber = 0;
        for (int i = 0; i < 3; i++) {
            randomNumber += (int) ((random.nextInt(9) + 1) * Math.pow(10, i));
        }
        return randomNumber;
    }

    public int getNumber() {
        return number;
    }
}
