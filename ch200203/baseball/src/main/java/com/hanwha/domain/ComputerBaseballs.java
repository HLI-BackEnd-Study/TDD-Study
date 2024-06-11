package com.hanwha.domain;

import com.hanwha.generator.BaseballNumberGenerator;

import java.util.List;

public class ComputerBaseballs {

    private final Baseballs baseballs;

    private ComputerBaseballs(Baseballs baseballs) {
        this.baseballs = baseballs;
    }

    public static ComputerBaseballs generateComputerBaseballs(BaseballNumberGenerator baseballNumberGenerator) {
        List<Integer> generatedNumbers = baseballNumberGenerator.generate().stream().toList();

        List<Baseball> generateBaseballs = generatedNumbers.stream()
                .map(number -> Baseball.create(number, generatedNumbers.indexOf(number)))
                .toList();

        return new ComputerBaseballs(Baseballs.from(generateBaseballs));
    }


    public Baseballs getBaseballs() {
        return baseballs;
    }
}
