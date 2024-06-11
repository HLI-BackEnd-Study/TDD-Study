package com.hanwha.domain;

import java.util.Arrays;

import static com.hanwha.constant.MessageConstant.INVALID_COMMAND_EXCEPTION;

public enum GameCommand {

    RESTART(1),
    QUIT(2);

    private final int number;

    GameCommand(int number) {
        this.number = number;
    }

    public static GameCommand from(int input) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.number == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(INVALID_COMMAND_EXCEPTION, input)));
    }

    public int getNumber() {
        return number;
    }
}
