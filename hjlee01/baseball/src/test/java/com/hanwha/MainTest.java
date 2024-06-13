package com.hanwha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
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
    void firstInning() {
        Game game = new Target(1, 7, 6).locate(new Direction(4, 8, 5));
        assertTrue(new Ball(0, 0).equals(game));
    }

    /**
     * 아웃된 숫자는 제거
     */
    @Test
    void secondInning() {
        Game game = new Target(1, 7, 6).locate(new Direction(9, 3, 1));
        assertTrue(new Ball(0, 1).equals(game));
    }

    @Test
    void thirdInning() {
        Game game = new Target(1, 7, 6).locate(new Direction(2, 1, 6));
        assertTrue(new Ball(1, 1).equals(game));
    }

    @Test
    void fourthInning() {
        Game game = new Target(1, 7, 6).locate(new Direction(1, 6, 7));
        assertTrue(new Ball(1, 2).equals(game));
    }

    @Test
    void fifthInning() {
        Game game = new Target(1, 7, 6).locate(new Direction(1, 7, 6));
        assertTrue(new Ball(3, 0).equals(game));
    }

    @Test
    void innings() {
        Target target = new Target(1, 7, 6);

        Game game = new Direction(4, 8, 5).locate(target);
        assertTrue(new Ball(0, 0).equals(game));

        game = new Direction(9, 3, 1).locate(target);
        assertTrue(new Ball(0, 1).equals(game));

        game = new Direction(2, 1, 6).locate(target);
        assertTrue(new Ball(1, 1).equals(game));

        game = new Direction(1, 6, 7).locate(target);
        assertTrue(new Ball(1, 2).equals(game));

        game = new Direction(1, 7, 6).locate(target);
        assertTrue(new Ball(3, 0).equals(game));
    }

    @Test
    void startTest() {
        Main main = new Main();
        main.start();
        assertTrue(main.innings == 0);
        assertFalse(main.innings == 1);
    }
}
