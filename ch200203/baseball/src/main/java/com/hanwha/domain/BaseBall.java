package com.hanwha.domain;

public class BaseBall {
    private final BaseBallNumber baseBallNumber;
    private final int position;

    private BaseBall(BaseBallNumber baseBallNumber, int position) {
        this.baseBallNumber = baseBallNumber;
        this.position = position;
    }

    public static BaseBall create(int number, int position) {
        BaseBallNumber baseBallNumber = BaseBallNumber.from(number);
        return new BaseBall(baseBallNumber, position);
    }

    public int getPosition() {
        return position;
    }
}
