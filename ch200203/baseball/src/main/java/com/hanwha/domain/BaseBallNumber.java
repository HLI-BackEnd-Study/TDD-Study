package com.hanwha.domain;

import static com.hanwha.constant.BaseballConstant.MAX_NUMBER;
import static com.hanwha.constant.BaseballConstant.MIN_NUMBER;
import static com.hanwha.constant.MessageConstant.NUMBER_BOUND_EXCEPTION;

public class BaseBallNumber {

    private final int number;

    public static BaseBallNumber from(final int number) {
        return new BaseBallNumber(number);
    }

    private BaseBallNumber(int number) {
        validNumber(number);
        this.number = number;
    }

    private void validNumber(final int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(NUMBER_BOUND_EXCEPTION);
        }
    }

    public int getNumber() {
        return number;
    }
}
