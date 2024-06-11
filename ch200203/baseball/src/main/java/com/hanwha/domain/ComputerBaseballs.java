package com.hanwha.domain;

import com.hanwha.generator.BaseballNumberGenerator;

import java.util.List;

public class ComputerBaseballs {

    private final Baseballs baseballs;

    private ComputerBaseballs(Baseballs baseballs) {
        this.baseballs = baseballs;
    }

    public static ComputerBaseballs fromGenerate(BaseballNumberGenerator baseballNumberGenerator) {
        List<Integer> generatedNumbers = baseballNumberGenerator.generate().stream().toList();

        List<Baseball> generateBaseballs = generatedNumbers.stream()
                .map(number -> {
                    System.out.println("number = " + number);
                    System.out.println("index = " + generatedNumbers.indexOf(number));
                    return Baseball.create(number, generatedNumbers.indexOf(number));
                })
                .toList();

        return new ComputerBaseballs(Baseballs.from(generateBaseballs));
    }

    public Baseballs getBaseballs() {
        return baseballs;
    }
}
