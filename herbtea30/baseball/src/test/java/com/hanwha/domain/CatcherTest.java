package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.hanwha.domain.Judge.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CatcherTest {

    @Test
    @DisplayName("투수가 던지는 공을 입력 받는다.")
    void test1() {
        Pitcher pitcher = new Pitcher();
        Ball ball = pitcher.pitch(123);
        Ball ball2 = pitcher.pitch(456);
        Ball ball3 = pitcher.pitch(789);

        Catcher catcher = new Catcher(new Judge());
        Result result = catcher.catchBall(ball);
        assertThat(result).isNotNull();

        result = catcher.catchBall(ball2);
        assertThat(result).isNotNull();

        result = catcher.catchBall(ball3);
        assertThat(result).isNotNull();
    }
}
