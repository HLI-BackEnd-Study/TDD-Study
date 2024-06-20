package com.hanwha.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;
import static com.hanwha.constant.MessageConstant.DUPLICATE_NUMBER_EXCEPTION;
import static com.hanwha.constant.MessageConstant.NUMBER_COUNT_EXCEPTION;

public class Baseballs {

    private final List<Baseball> baseballs;

    Baseballs(List<Baseball> baseballs) {
        validCount(baseballs);
        validDuplicate(baseballs);
        this.baseballs = new ArrayList<>(baseballs);
    }

    public static Baseballs from(List<Baseball> baseballs) {
        return new Baseballs(baseballs);
    }

    public static Baseballs newFrom(List<Integer> numbers) {
        List<Baseball> generateBaseballs = generateBaseballs(numbers);
        return new Baseballs(generateBaseballs);
    }

    /**
     * 입력받은 숫자를 바탕으로 Baseball List 를 만들어 반환한다.
     */
    private static List<Baseball> generateBaseballs(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> Baseball.create(number, numbers.indexOf(number)))
                .toList();
    }

    public List<Baseball> getBaseballs() {
        return Collections.unmodifiableList(baseballs);
    }

    public int size() {
        return baseballs.size();
    }

    private void validCount(List<Baseball> baseballs) {
        if (baseballs.size() != GAME_COUNT) {
            throw new IllegalArgumentException(NUMBER_COUNT_EXCEPTION);
        }
    }

    private void validDuplicate(List<Baseball> baseballs) {
        long count = baseballs.stream()
                .map(Baseball::getBaseBallNumber)
                .distinct()
                .count();

        if (count != GAME_COUNT) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_EXCEPTION);
        }
    }

    public int getStrikeCount(Baseballs other) {
        return (int) baseballs.stream()
                .filter(baseball -> baseball.isStrike(other.baseballs.get(baseball.getPosition())))
                .count();
    }

    public int getBallCount(Baseballs other) {
        return (int) baseballs.stream()
                .filter(baseball -> other.baseballs.stream().anyMatch(baseball::isBall))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baseballs baseballs1 = (Baseballs) o;
        return Objects.equals(baseballs, baseballs1.baseballs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseballs);
    }
}
