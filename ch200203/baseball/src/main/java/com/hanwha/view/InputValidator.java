package com.hanwha.view;

import java.util.List;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;

public class InputValidator {
    public static void validInput(String input) {
        if (input.length() != GAME_COUNT) {
            throw new IllegalArgumentException("입력은 " + GAME_COUNT + " 자리 숫자여야 합니다.");
        }
    }
}
