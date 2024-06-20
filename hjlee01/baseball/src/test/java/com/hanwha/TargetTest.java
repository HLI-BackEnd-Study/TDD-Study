package com.hanwha;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TargetTest {
    @Test
    @Description("게임 랜덤 타깃 테스트")
    void randomTargetTest() {
        Target t = new Target();
        t.randomTarget();
        assertTrue(1 <= t.ball1 && t.ball1 <= 9);
        assertTrue(1 <= t.ball2 && t.ball2 <= 9);
        assertTrue(1 <= t.ball3 && t.ball3 <= 9);
        assertTrue(t.ball1 != t.ball2 && t.ball2 != t.ball3 && t.ball1 != t.ball3);
    }
}
