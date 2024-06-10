package com.hanwha.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomBaseballNumberGenerator implements BaseballNumberGenerator {

    private List<Integer> numbers = new ArrayList<>();

    public RandomBaseballNumberGenerator() {
        this.numbers = IntStream.range(1, 9)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(numbers);
        return this.numbers.subList(0, 3);
    }
}
