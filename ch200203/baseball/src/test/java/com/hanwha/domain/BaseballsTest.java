package com.hanwha.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.hanwha.constant.MessageConstant.DUPLICATE_NUMBER_EXCEPTION;
import static com.hanwha.constant.MessageConstant.NUMBER_COUNT_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class BaseballsTest {

    @Test
    @DisplayName("Baseballs 객체를 생성하여 반환한다.")
    void validBaseballCountTest() {
        List<Baseball> baseballList = List.of(
                Baseball.create(1, 0),
                Baseball.create(2, 1),
                Baseball.create(3, 2)
        );

        Baseballs baseballs = Baseballs.from(baseballList);

        assertThat(baseballs)
                .isInstanceOf(Baseballs.class);
        assertThat(baseballs.size()).isEqualTo(3);
        assertThat(baseballs.getBaseballs()).isEqualTo(baseballList);
    }

    @Test
    @DisplayName("생성한 Baseball이 3개 이상인 경우 예외를 반환한다")
    void validBaseballCountExceptionTest() {
        List<Baseball> baseballList = List.of(
                Baseball.create(1, 0),
                Baseball.create(2, 1),
                Baseball.create(3, 2),
                Baseball.create(4, 3)
        );

        assertThatThrownBy(() -> Baseballs.from(baseballList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_COUNT_EXCEPTION);
    }

    @Test
    @DisplayName("생성한 Baseball 에 중복되는 숫자가 있는 경우 예외를 반환한다.")
    void validDuplicateExceptionTest() {
        List<Baseball> baseballList = List.of(
                Baseball.create(1, 0),
                Baseball.create(2, 1),
                Baseball.create(2, 2)
        );

        assertThatThrownBy(() -> Baseballs.from(baseballList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_NUMBER_EXCEPTION);
    }

    @Test
    @DisplayName("두 Baseballs 를 비교하여 스트라이크 갯수를 반환한다.")
    void testStrike() {
        List<Baseball> actualBaseballs = List.of(
                Baseball.create(1, 0),
                Baseball.create(2, 1),
                Baseball.create(3, 2)
        );

        List<Baseball> expectedBaseballs = List.of(
                Baseball.create(1, 0),
                Baseball.create(2, 1),
                Baseball.create(3, 2)
        );

        Baseballs actual = Baseballs.from(actualBaseballs);
        Baseballs expected = Baseballs.from(expectedBaseballs);

        assertThat(actual.getStrikeCount(expected)).isEqualTo(3);
    }

    @Test
    @DisplayName("두 Baseballs 를 비교하여 볼 갯수를 반환한다.")
    void testBall() {
        List<Baseball> actualBaseballs = List.of(
                Baseball.create(1, 0),
                Baseball.create(2, 1),
                Baseball.create(3, 2)
        );

        List<Baseball> expectedBaseballs = List.of(
                Baseball.create(1, 2),
                Baseball.create(2, 0),
                Baseball.create(3, 1)
        );

        Baseballs actual = Baseballs.from(actualBaseballs);
        Baseballs expected = Baseballs.from(expectedBaseballs);

        assertThat(actual.getBallCount(expected)).isEqualTo(3);
    }
}