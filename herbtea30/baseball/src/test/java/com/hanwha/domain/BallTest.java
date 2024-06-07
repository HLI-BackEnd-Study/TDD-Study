package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(ball.getTotalBall()).isLessThan(1000);
        assertThat(ball.getTotalBall()).isGreaterThan(99);

        assertThatThrownBy(() -> {
            ball = new Ball(1234);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("볼은 3자리 숫자만 입력할 수 없습니다.");

        assertThatThrownBy(() -> {
            ball = new Ball(12);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("볼은 3자리 숫자만 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("Ball은 각 자리수의 범위 체크(1 ~ 9)")
    void ballTest2() {
        assertThat(ball.getFirstBall()).isEqualTo(1);
        assertThat(ball.getSecondBall()).isEqualTo(2);
        assertThat(ball.getThirdBall()).isEqualTo(3);
    }
}
