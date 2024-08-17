package com.hanwha;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MainTest {
    /*
      1. 테스트 코드 짜기
      2. 테스트 코드 실행 및 추가
      3. 코드 변경
      4. 테스트 전부 성공하기
      5. 리팩토링
     */

    @Test
    @Description("게임 시작 시 0 이닝인지 테스트")
    void startTest() {
        Main.start();
        assertEquals(0, Main.innings);
        assertNotEquals(1, Main.innings);
    }
}
