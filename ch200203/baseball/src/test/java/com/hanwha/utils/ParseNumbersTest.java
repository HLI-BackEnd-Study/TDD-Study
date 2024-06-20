package com.hanwha.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParseNumbersTest {

    @Test
    @DisplayName("사용자의 입력값을 Baseball 에 맞춰 변환한다.")
    void parseNumbers() {
        String input = "123";
        var expected = List.of(1,2,3);
        assertThat(ParseNumbers.parseNumbers(input)).isEqualTo(expected);
    }
}
