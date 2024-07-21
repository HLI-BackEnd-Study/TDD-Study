package com.hanwha.domain;

import java.util.List;

public class UserBaseballs {

    private final Baseballs baseballs;

    private UserBaseballs(Baseballs baseballs) {
        this.baseballs = baseballs;
    }

    public static UserBaseballs generateUserBaseballs(List<Integer> inputNumbers) {
        List<Baseball> generateBaseballs = inputNumbers.stream()
                .map(number -> Baseball.create(number, inputNumbers.indexOf(number)))
                .toList();

        Baseballs baseballs = Baseballs.from(generateBaseballs);
        return new UserBaseballs(baseballs);
    }

    public Baseballs getBaseballs() {
        return baseballs;
    }
}
