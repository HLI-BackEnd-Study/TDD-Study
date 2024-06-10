package com.hanwha.service;

import static org.junit.jupiter.api.Assertions.*;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void countStrikeTest() {
        // given
        Solution solution = new Solution();
        User user = new User(123);
        Computer computer = new Computer();

        // when
        String answer = String.valueOf(computer.getNumber());
        String input = String.valueOf(user.number());

        int strike = solution.getStrikeCount(answer, input);

        // then
        assertTrue(strike >= 0);
        assertTrue(strike <= 3);
        System.out.println("answer: " + answer);
        System.out.println("input: " + input);
        System.out.println("strike: " + strike);
    }

    @Test
    void threeStrikeTest() {
        Solution solution = new Solution();
        Computer computer = new Computer();
        User user = new User(computer.getNumber());

        // when
        String answer = String.valueOf(computer.getNumber());
        String input = String.valueOf(user.number());

        int strike = solution.getStrikeCount(answer, input);

        // then
        assertEquals(3, strike);
    }

    @Test
    void zeroStrikeTest() {
        // given
        Solution solution = new Solution();
        Computer computer = new Computer();
        String answer = String.valueOf(computer.getNumber());
        String input = "";
        for (char ch : answer.toCharArray()) {
            for (char num = '1'; num <= '9'; num++) {
                if (ch != num) {
                    input += num;
                    break;
                }
            }
        }

        User user = new User(Integer.parseInt(input));

        // when
        String inputString = String.valueOf(user.number());

        int strike = solution.getStrikeCount(answer, inputString);

        // then
        assertEquals(0, strike);
    }

    @Test
    void countBallTest() {
        // given
        Solution solution = new Solution();
        User user = new User(123);
        Computer computer = new Computer();

        String answer = String.valueOf(computer.getNumber());
        String input = String.valueOf(user.number());

        // when
        int ball = solution.getBallCount(answer, input);

        // then
        assertTrue(ball >= 0);
        assertTrue(ball <= 3);
        System.out.println("answer: " + answer);
        System.out.println("input: " + input);
        System.out.println("ball: " + ball);
    }


}
