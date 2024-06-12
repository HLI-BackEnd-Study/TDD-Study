package com.hanwha.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("3 스트라이크인 경우 '3 스트라이크'를 반환한다")
    void testThreeStrikes() {
        GameResult result = new GameResult(3, 0);
        assertThat(result.toString()).isEqualTo("3 스트라이크");
    }

    @Test
    @DisplayName("2 볼인 경우 '2 볼'을 반환한다")
    void testTwoBalls() {
        GameResult result = new GameResult(0, 2);
        assertThat(result.toString()).isEqualTo("2 볼");
    }

    @Test
    @DisplayName("1 스트라이크 2 볼인 경우 '1 스트라이크 2 볼'을 반환한다")
    void testOneStrikeTwoBalls() {
        GameResult result = new GameResult(1, 2);
        assertThat(result.toString()).isEqualTo("1 스트라이크 2 볼");
    }

    @Test
    @DisplayName("스트라이크와 볼이 없는 경우 '낫싱'을 반환한다")
    void testNothing() {
        GameResult result = new GameResult(0, 0);
        assertThat(result.toString()).isEqualTo("낫싱");
    }

    @Test
    @DisplayName("숫자를 다 맞추지 못 한 경우 True 를 반환한다.")
    void gameLose() {
        GameResult result = new GameResult(1, 2);
        assertThat(result.isWrong()).isTrue();
    }

    @Test
    @DisplayName("숫자를 다 맞춘 경우 False 를 반환한다.")
    void gameWin() {
        GameResult result = new GameResult( 3, 0);
        assertThat(result.isWrong()).isFalse();
    }
}
