package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GOAL - 랜덤생성 난수 검증 테스트")
class GoalTest {
    private Goal goal;

    @BeforeEach
    public void setUp() {
        goal = new Goal();
    }

    @Test
    @DisplayName("숫자 자리수를 체크")
    void checkNumberLength() {
        for(int i = 0 ; i < 1000 ; i++) {
            Ball ball = goal.getBall();
            assertThat(ball.getRandomBall()).isLessThan(1000);
            assertThat(ball.getRandomBall()).isGreaterThan(99);
        }
    }

    @Test
    @DisplayName("숫자 자리수를 체크 - 넘어가는 케이스")
    void checkGreaterNumber() {
        for(int i = 0 ; i < 1000 ; i++) {
            Ball ball = goal.getBall();
            assertThat(ball.getRandomBall()).isLessThan(1000);
        }

    }

    @Test
    @DisplayName("숫자 자리수를 체크 - 작은 케이스")
    void checkLessNumber() {
        for(int i = 0 ; i < 1000 ; i++) {
            Ball ball = goal.getBall();
            assertThat(ball.getRandomBall()).isGreaterThan(99);
        }
    }

    @Test
    @DisplayName("숫자 각 자리수의 1~9까지 여부와 중복을 체크한다.")
    void checkDuplication() {
        for(int i = 0 ; i < 1000 ; i++) {
            Ball ball = goal.getBall();
            //양수 여부 체크(0보다 큰지)
            assertThat(ball.getFirstBall()).isPositive();
            assertThat(ball.getSecondBall()).isPositive();
            assertThat(ball.getThirdBall()).isPositive();

            //중복여부 체크
            assertThat(ball.getFirstBall()).isNotEqualTo(ball.getSecondBall());
            assertThat(ball.getSecondBall()).isNotEqualTo(ball.getThirdBall());
            assertThat(ball.getFirstBall()).isNotEqualTo(ball.getThirdBall());

        }
    }

    @Test
    @DisplayName("다시 시작 - 임의의 난수가 직전 난수와 다른지 여부 체크")
    void checkLastNumber() {
        //직전 난수 리스트(10개)와 현재 난수가 같은지 여부 체크
        for(int i = 0 ; i < 1000 ; i++) {
            Ball ball = goal.getBall();
            assertThat(goal.getDuplicationList()).doesNotContain(ball.getRandomBall());
            goal.addDuplicationNumberList();
        }
    }

    @Test
    @DisplayName("서로 다른 숫자인지 체크")
    void checkLastNumber2() {
        Ball ball = goal.getBall();
        assertThat(ball.getFirstBall()).isNotEqualTo(ball.getSecondBall());
        assertThat(ball.getSecondBall()).isNotEqualTo(ball.getThirdBall());
        assertThat(ball.getFirstBall()).isNotEqualTo(ball.getThirdBall());
    }

    @Test
    @DisplayName("0이 없는지 체크")
    void checkLastNumber3() {
        Ball ball = goal.getBall();
        assertThat(ball.getFirstBall()).isPositive();
        assertThat(ball.getSecondBall()).isPositive();
        assertThat(ball.getThirdBall()).isPositive();
    }
}

