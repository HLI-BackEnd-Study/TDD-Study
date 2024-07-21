package com.hanwha.player;

import java.util.HashSet;
import java.util.Set;


public record User(int number) {

    public void checkValidInput() {
        String input = String.valueOf(number);
        if (!isThreeLength(input) || hasDuplicateCharacter(input)) {
            throw new IllegalArgumentException("알맞지 않은 입력입니다.");
        }
    }

    private boolean hasDuplicateCharacter(String input) {
        char[] chars = input.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char ch : chars) {
            set.add(ch);
        }
        return set.size() < 3;
    }

    private boolean isThreeLength(String input) {
        return input.length() == 3;
    }
}
