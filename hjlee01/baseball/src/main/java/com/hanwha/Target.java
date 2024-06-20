package com.hanwha;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Target extends Game {

    public Target(int ball1, int ball2, int ball3) {
        super(ball1, ball2, ball3);
    }

    public Target() {
        super(0, 0, 0);
    }

    public void randomTarget() {
        Random random = new Random();
        Set<Integer> numSet = new HashSet<>();

        do {
            int ranNum = random.nextInt(9) + 1;
            numSet.add(ranNum);
        } while (numSet.size() != 3);

        Integer[] num = numSet.toArray(new Integer[0]);

        this.ball1 = num[0];
        this.ball2 = num[1];
        this.ball3 = num[2];
    }
}
