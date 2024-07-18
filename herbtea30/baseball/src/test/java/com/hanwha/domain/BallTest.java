package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hanwha.consts.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("볼 테스트")
class BallTest {
    Ball ball;
    @BeforeEach
    public void setUp() {
        ball = new Ball(123);
    }

    @Test
    @DisplayName("Ball은 3자리 수를 가짐")
    void ballTest1() {
        assertThat(ball.getBall()).isLessThan(1000);
        assertThat(ball.getBall()).isGreaterThan(99);

        assertThatThrownBy(() -> {
            ball = new Ball(1234);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(Message.BALL_HAS_THREE_DIGITS);

        assertThatThrownBy(() -> {
            ball = new Ball(12);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Message.BALL_HAS_THREE_DIGITS);
    }

    @Test
    @DisplayName("Ball은 각 자리수의 범위 체크(1 ~ 9)")
    void ballTest2() {
        assertThat(ball.getFirstBall()).isEqualTo(1);
        assertThat(ball.getSecondBall()).isEqualTo(2);
        assertThat(ball.getThirdBall()).isEqualTo(3);

        assertThatThrownBy(() -> {
            ball = new Ball(102);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(Message.BALL_RANGE_1_TO_9);

        assertThatThrownBy(() -> {
            ball = new Ball(112);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Message.DUPLICATION_NOT_ALLOWED);
    }
}
