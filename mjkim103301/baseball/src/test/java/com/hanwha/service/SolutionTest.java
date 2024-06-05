package com.hanwha.service;

import static org.junit.jupiter.api.Assertions.*;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void countingStrike() {
        Solution solution = new Solution();
        User user = new User(000);
        Computer computer = new Computer();
        int strike = solution.getStrikeCount();
        assertTrue(strike >= 0);
        assertTrue(strike <= 3);
    }

    @Test
    void countingBall() {
        Solution solution = new Solution();
        User user = new User(000);
        Computer computer = new Computer();
        int ball = solution.getBallCount();
        assertTrue(ball >= 0);
        assertTrue(ball <= 3);
    }

}
