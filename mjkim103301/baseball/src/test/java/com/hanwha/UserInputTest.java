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

    @Test
    void nothingTest() {
        int answer = 425;
        int user = 178;
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
    @ValueSource(ints = {123, 987})
    void validInputTest(int user) {
        boolean result = isValidInput(user);
        System.out.println("정상적인 입력값입니다.");
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 709, 970, 111, 999})
    void invalidInputTest(int user) {
        boolean result = isValidInput(user);
        System.out.println("1~9까지의 서로 다른 3자리 수로 구성해주세요.");
        assertFalse(result);
    }

    boolean isValidInput(int user) {
        boolean[] used = new boolean[10];

        String input = String.valueOf(user);
        if (input.length() != 3) {
            return false;
        }
        for (char ch : input.toCharArray()) {
            int index = ch - '0';
            if (index == 0 || used[index]) {
                return false;
            }
            used[index] = true;
        }
        return true;
    }

}
