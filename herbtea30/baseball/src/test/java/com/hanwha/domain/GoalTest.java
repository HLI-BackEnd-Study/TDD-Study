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
            int randomNum = goal.generateRandomNumber();
            assertThat(randomNum).isLessThan(1000);
            assertThat(randomNum).isGreaterThan(99);
        }
    }

    @Test
    @DisplayName("숫자 자리수를 체크 - 넘어가는 케이스")
    void checkGreaterNumber() {
        for(int i = 0 ; i < 1000 ; i++) {
            int randomNum = goal.generateRandomNumber();
            assertThat(randomNum).isLessThan(1000);
        }

    }

    @Test
    @DisplayName("숫자 자리수를 체크 - 작은 케이스")
    void checkLessNumber() {
        for(int i = 0 ; i < 1000 ; i++) {
            int randomNum = goal.generateRandomNumber();
            assertThat(randomNum).isGreaterThan(99);
        }
    }

    @Test
    @DisplayName("숫자 각 자리수의 1~9까지 여부와 중복을 체크한다.")
    void checkDuplication() {
        for(int i = 0 ; i < 1000 ; i++) {
            int randomNum = goal.generateRandomNumber();
            int firstNumber = randomNum / 100;
            int secondNumber = (randomNum - (100 * firstNumber)) / 10;
            int thirdNumber = randomNum - (100 * firstNumber) - (10 * secondNumber);

            //양수 여부 체크(0보다 큰지)
            assertThat(firstNumber).isPositive();
            assertThat(secondNumber).isPositive();
            assertThat(thirdNumber).isPositive();

            //중복여부 체크
            assertThat(firstNumber).isNotEqualTo(secondNumber);
            assertThat(secondNumber).isNotEqualTo(thirdNumber);
            assertThat(thirdNumber).isNotEqualTo(firstNumber);

        }
    }

    @Test
    @DisplayName("다시 시작 - 임의의 난수가 직전 난수와 다른지 여부 체크")
    void checkLastNumber() {
        //직전 난수 리스트(10개)와 현재 난수가 같은지 여부 체크
        for(int i = 0 ; i < 1000 ; i++) {
            int randomNum = goal.generateRandomNumber();
            assertThat(goal.getDuplicationList()).doesNotContain(randomNum);
            goal.addDuplicationNumberList();
        }
    }

    @Test
    @DisplayName("서로 다른 숫자인지 체크")
    void checkLastNumber2() {
        goal.generateRandomNumber();
        assertThat(goal.getFirstNumber()).isNotEqualTo(goal.getSecondNumber());
        assertThat(goal.getSecondNumber()).isNotEqualTo(goal.getThirdNumber());
        assertThat(goal.getFirstNumber()).isNotEqualTo(goal.getThirdNumber());
    }

    @Test
    @DisplayName("0이 없는지 체크")
    void checkLastNumber3() {
        goal.generateRandomNumber();
        assertThat(goal.getFirstNumber()).isPositive();
        assertThat(goal.getSecondNumber()).isPositive();
        assertThat(goal.getFirstNumber()).isPositive();
    }
}

