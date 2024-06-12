package com.hanwha.player;

import java.util.Random;

public class Computer {

    private final int number;
    private final Random random = new Random();

    public Computer() {
        this.number = getRandomNumber();
    }

    private int getRandomNumber() {
        StringBuilder randomBuilder = new StringBuilder();
        boolean[] used = new boolean[10];
        for (int i = 0; i < 3; i++) {
            int temp = 0;
            while (true) {
                temp = random.nextInt(9) + 1;
                if (!used[temp]) {
                    used[temp] = true;
                    break;
                }
            }
            randomBuilder.append(temp);
        }
        return Integer.parseInt(randomBuilder.toString());
    }

    public int getNumber() {
        return number;
    }
}
