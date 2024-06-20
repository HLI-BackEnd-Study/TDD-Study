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


    public boolean isStrike(Baseball other) {
        return isSameNumber(other.getBaseBallNumber()) && isSamePosition(other.position);
    }
    public boolean isBall(Baseball other) {
        return isSameNumber(other.getBaseBallNumber()) && !isSamePosition(other.position);
    }

    public boolean isSameNumber(BaseballNumber baseballNumber) {
        return this.baseBallNumber.equals(baseballNumber);
    }

    public boolean isSamePosition(int position) {
        return this.position == position;
    }

    @Override
    public String toString() {
        return "Baseball{" +
                "baseBallNumber=" + baseBallNumber.getNumber() +
                ", position=" + position +
                '}';
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
