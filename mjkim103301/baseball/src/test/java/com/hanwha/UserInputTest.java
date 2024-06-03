package com.hanwha;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserInputTest {

    @ParameterizedTest
    @ValueSource(ints = {542, 254})
    void threeBallTest(int user) {
        int answer = 425;
        boolean nothing = false;

        int strike = getStrike(String.valueOf(answer), String.valueOf(user));
        int ball = getBall(String.valueOf(answer), String.valueOf(user));

        if (strike == 0 && ball == 0) {
            nothing = true;
        }

        if (nothing) {
            System.out.println("일치하는 숫자가 하나도 없습니다.");
        } else {
            System.out.println("strike : " + strike);
            System.out.println("ball : " + ball);
        }

        assertEquals(3, ball);
        assertEquals(0, strike);
        assertFalse(nothing);
    }

    @Test
    void threeStrikeTest() {
        int answer = 425;
        int user = 425;
        boolean nothing = false;

        int strike = getStrike(String.valueOf(user), String.valueOf(user));
        int ball = getBall(String.valueOf(user), String.valueOf(user));

        assertEquals(3, strike);
        assertEquals(0, ball);
        assertFalse(nothing);
    }

    @ParameterizedTest
    @ValueSource(ints = {111, 333, 666, 777, 888, 999})
    void nothingTest(int user) {
        int answer = 425;
        boolean nothing = false;

        int strike = getStrike(String.valueOf(answer), String.valueOf(user));
        int ball = getBall(String.valueOf(answer), String.valueOf(user));
        if (strike == 0 && ball == 0) {
            nothing = true;
        }

        assertTrue(nothing);
        assertEquals(0, strike);
        assertEquals(0, ball);

    }

    int getStrike(String answer, String user) {
        int strike = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == user.charAt(i)) {
                strike++;
            }
        }
        return strike;
    }

    int getBall(String answer, String user) {
        int ball = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) != user.charAt(i) && user.indexOf(answer.charAt(i)) >= 0) {
                ball++;
            }
        }
        return ball;
    }

    @ParameterizedTest
    @ValueSource(ints = {111, 999, 123, 234, 345, 987})
    void validInputTest(int user) {
        boolean result = isValidInput(user);
        System.out.println("정상적인 입력값입니다.");
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 800, 709, 909})
    void unvalidInputTest(int user) {
        boolean result = isValidInput(user);
        System.out.println("111 이상 999 이하의 숫자만 입력해 주세요. 각 자리는 1부터 9까지의 숫자만 올 수 있습니다.");
        assertFalse(result);
    }

    boolean isValidInput(int user) {
        if (user < 111 || user > 999) {
            return false;
        }
        String input = String.valueOf(user);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch < '1' || ch > '9') {
                return false;
            }
        }
        return true;
    }

}
