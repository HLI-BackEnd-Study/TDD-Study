package com.hanwha.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.hanwha.constant.MessageConstant.DUPLICATE_NUMBER_EXCEPTION;
import static com.hanwha.constant.MessageConstant.NUMBER_COUNT_EXCEPTION;
import static org.assertj.core.api.Assertions.*;
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

}