package com.hanwha.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.hanwha.constant.MessageConstant.INVALID_COMMAND_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class GameCommandTest {

    @Test
    @DisplayName("1 을 입력하는 경우 재시작 명령을 반환한다")
    void commandRestart() {
        assertThat(GameCommand.from(1)).isEqualTo(GameCommand.RESTART);
    }

    @Test
    @DisplayName("2 을 입력하는 경우 게임종료 명령을 반환한다")
    void commandQuit() {
        assertThat(GameCommand.from(2)).isEqualTo(GameCommand.QUIT);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 3"})
    @DisplayName("1, 2 외의 명령어를 입력하는 경우 예외를 반환한다.")
    void commandException(int input) {
        assertThatThrownBy(() -> GameCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(INVALID_COMMAND_EXCEPTION, input));
    }

}