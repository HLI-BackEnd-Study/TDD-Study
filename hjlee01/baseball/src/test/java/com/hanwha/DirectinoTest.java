package com.hanwha;

import jdk.jfr.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DirectinoTest {
    @ParameterizedTest
    @CsvSource(value = {"123:true", "12:false", "444:false"}, delimiter = ':')
    @Description("게임 입력 숫자 검증")
    void inputDirectionTest(int balls, boolean isThreeBalls) {
        // 각각 다른 수의 3자리 수 인지
        Direction direction = new Direction(balls);
        assertThat(direction.isValidThreeBalls() && direction.isValidDiffrentBalls()).isEqualTo(isThreeBalls);
    }
}