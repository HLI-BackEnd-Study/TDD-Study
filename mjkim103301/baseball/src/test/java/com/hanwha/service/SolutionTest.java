package com.hanwha.service;

import static org.junit.jupiter.api.Assertions.*;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @DisplayName("스트라이크 개수는 0 이상 3 이하다.")
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

    @DisplayName("컴퓨터 숫자와 사용자 입력 숫자가 같으면 3스트라이크다.")
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

    @DisplayName("0 스트라이크 테스트")
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

    @DisplayName("볼의 개수는 0 이상 3 이하여야 한다.")
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

    @DisplayName("사용자의 숫자 중 1개라도 정답에 있으면 nothing false test")
    @Test
    void nothingTest() {
        // given
        Solution solution = new Solution();
        Computer computer = new Computer();
        User user = new User(computer.getNumber());

        // when
        String answer = String.valueOf(computer.getNumber());
        String input = String.valueOf(user.number());

        int strike = solution.getStrikeCount(answer, input);
        int ball = solution.getBallCount(answer, input);
        boolean nothing = solution.isNothing(strike, ball);

        // then
        System.out.println("strike: " + strike);
        System.out.println("ball: " + ball);
        System.out.println("nothing: " + nothing);
        assertFalse(nothing);

    }

    @DisplayName("사용자의 숫자가 정답과 일치하면 성공 테스트")
    @Test
    void successTest() {
        Solution solution = new Solution();
        Computer computer = new Computer();
        User user = new User(computer.getNumber());
        String answer = String.valueOf(computer.getNumber());
        String input = String.valueOf(user.number());

        boolean success = solution.isSuccess(answer, input);
        assertTrue(success);
    }


}
