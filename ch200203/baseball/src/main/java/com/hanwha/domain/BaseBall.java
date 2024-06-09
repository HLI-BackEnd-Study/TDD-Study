package com.hanwha.domain;

public class BaseBall {
    private final BaseBallNumber baseBallNumber;
    private final int position;

    public BaseBall(BaseBallNumber baseBallNumber, int position) {
        this.baseBallNumber = baseBallNumber;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }


}
