package com.hanwha.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hanwha.constant.BaseballConstant.*;

public class RandomBaseballNumberGenerator implements BaseballNumberGenerator {

    private List<Integer> numbers = new ArrayList<>();

    public RandomBaseballNumberGenerator() {
        this.numbers = IntStream.range(MIN_NUMBER, MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(numbers);
        return this.numbers.subList(ZERO, GAME_COUNT);
    }
}
