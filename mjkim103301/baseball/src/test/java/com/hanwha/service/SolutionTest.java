package com.hanwha.service;

import static org.junit.jupiter.api.Assertions.*;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void countingStrikeValidTest() {
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
    void countingBallValidTest() {
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
