package com.hanwha.view;

import java.util.Scanner;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;
import static com.hanwha.constant.MessageConstant.INPUT_MESSAGE;
import static com.hanwha.constant.MessageConstant.INVALID_INPUT_EXCEPTION_MESSAGE;

public class InputView {

    private static final Scanner scan = new Scanner(System.in);

    public String inputNumbers() {
        System.out.println(INPUT_MESSAGE);
        String input = scan.nextLine();
        validInputNumbers(input);
        return scan.nextLine();
    }

    private void validInputNumbers(String numbers) {
        if (numbers.length() != GAME_COUNT) {
            throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
    }
}
