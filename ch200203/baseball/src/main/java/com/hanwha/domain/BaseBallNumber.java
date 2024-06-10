package com.hanwha.domain;

public class BaseBallNumber {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 9;

    private final int number;

    public static BaseBallNumber of(final int number) {
        return new BaseBallNumber(number);
    }

    private BaseBallNumber(int number) {
        validNumber(number);
        this.number = number;
    }

    private void validNumber(final int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("숫자는 1~9까지 입니다.");
        }
    }

    public int getNumber() {
        return number;
    }
}
