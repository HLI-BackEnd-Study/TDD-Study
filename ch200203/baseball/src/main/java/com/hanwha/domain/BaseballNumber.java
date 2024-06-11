package com.hanwha.domain;

import java.util.Objects;

import static com.hanwha.constant.BaseballConstant.MAX_NUMBER;
import static com.hanwha.constant.BaseballConstant.MIN_NUMBER;
import static com.hanwha.constant.MessageConstant.NUMBER_BOUND_EXCEPTION;

public class BaseballNumber {

    private final int number;

    public static BaseballNumber from(final int number) {
        return new BaseballNumber(number);
    }

    private BaseballNumber(int number) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseballNumber baseballNumber = (BaseballNumber) o;
        return number == baseballNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
