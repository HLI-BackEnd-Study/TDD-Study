package com.hanwha.domain;

import java.util.Objects;

public class Baseball {

    private final BaseballNumber baseBallNumber;
    private final int position;

    private Baseball(BaseballNumber baseBallNumber, int position) {
        this.baseBallNumber = baseBallNumber;
        this.position = position;
    }

    public static Baseball create(int number, int position) {
        BaseballNumber baseBallNumber = BaseballNumber.from(number);
        return new Baseball(baseBallNumber, position);
    }

    public int getPosition() {
        return position;
    }

    public BaseballNumber getBaseBallNumber() {
        return baseBallNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baseball baseball = (Baseball) o;
        return position == baseball.position && Objects.equals(baseBallNumber, baseball.baseBallNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseBallNumber, position);
    }

}
