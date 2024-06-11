package com.hanwha.view;

import com.hanwha.domain.GameCommand;

import java.util.Scanner;

import static com.hanwha.constant.BaseballConstant.GAME_COUNT;
import static com.hanwha.constant.MessageConstant.*;

public class InputView {

    private static final Scanner scan = new Scanner(System.in);

    public String inputNumbers() {
        printMessage(INPUT_MESSAGE);
        String input = scan.nextLine();
        validInputNumbers(input);
        return scan.nextLine();
    }

    public GameCommand inputCommand() {
        printMessage("게임을 다시 시작하시겠습니까? (1: 재시작, 2: 종료): ");
        String input = scan.nextLine();
        return GameCommand.from(Integer.parseInt(input));
    }

    private void validInputNumbers(String numbers) {
        if (numbers.length() != GAME_COUNT) {
            throw new IllegalArgumentException(String.format(INPUT_NUMBER_COUNT_EXCEPTION, GAME_COUNT));
        }

        if (!numbers.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION);
        }
    }
}
