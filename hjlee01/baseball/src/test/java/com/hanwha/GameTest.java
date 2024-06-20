package com.hanwha;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    /*
      1. 테스트 코드 짜기
      2. 테스트 코드 실행 및 추가
      3. 코드 변경
      4. 테스트 전부 성공하기
      5. 리팩토링
     */

    /**
     * 숫자는 맞지만 위치가 틀렸을 때는 볼.
     * 숫자와 위치가 전부 맞으면 스트라이크.
     * 숫자와 위치가 전부 틀리면 아웃.
     */
    @Test
    @Description("숫자 야구 게임 테스트")
    void innings() {
        Target target = new Target(1, 7, 6);

        // 1회차 - 3아웃
        Game game = new Direction(4, 8, 5).locate(target);
        assertTrue(game.strike == 0 && game.ball == 0);
        // 2회차 - 1볼
        game = new Direction(9, 3, 1).locate(target);
        assertTrue(game.strike == 0 && game.ball == 1);
        // 3회차 - 1스트라이크 1볼
        game = new Direction(2, 1, 6).locate(target);
        assertTrue(game.strike == 1 && game.ball == 1);
        // 4회차 - 1스트라이크 2볼
        game = new Direction(1, 6, 7).locate(target);
        assertTrue(game.strike == 1 && game.ball == 2);
        // 5회차 - 3스트라이크
        game = new Direction(1, 7, 6).locate(target);
        assertTrue(game.strike == 3 && game.ball == 0);
    }

}
