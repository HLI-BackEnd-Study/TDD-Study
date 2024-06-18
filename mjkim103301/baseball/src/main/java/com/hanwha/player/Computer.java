package com.hanwha.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Computer {

    private final int number;
    private final List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public Computer() {
        this.number = getRandomNumber();
    }

    private int getRandomNumber() {
        StringBuilder randomNumber = new StringBuilder();
        Collections.shuffle(numbers);
        for (int i = 0; i < 3; i++) {
            randomNumber.append(numbers.get(i));
        }
        return Integer.parseInt(randomNumber.toString());
    }

    public int getNumber() {
        return number;
    }
}
