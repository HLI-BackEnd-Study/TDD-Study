package com.hanwha.utils;

import java.util.List;
import java.util.stream.Stream;

public class ParseNumbers {

    public static List<Integer> parseNumbers(String numbers) {
        return Stream.of(numbers.split(""))
                .map(Integer::parseInt)
                .toList();
    }
}
