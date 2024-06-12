package com.hanwha.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class UserBaseballsTest {
    @Test
    @DisplayName("유저가 입력한 숫자 리스트로 UserBaseballs 객체를 생성한다")
    void generateUserBaseballsTest() {
        List<Integer> inputNumbers = List.of(1, 2, 3);

        UserBaseballs userBaseballs = UserBaseballs.generateUserBaseballs(inputNumbers);
        Baseballs baseballs = userBaseballs.getBaseballs();
        List<Baseball> expectedBaseballs = List.of(
                Baseball.create(1, 0), Baseball.create(2, 1), Baseball.create(3, 2)
        );
        Baseballs expected = Baseballs.from(expectedBaseballs);

        assertSoftly(softly -> {
            softly.assertThat(baseballs.getBaseballs()).hasSize(3);
            softly.assertThat(baseballs.getBaseballs())
                    .extracting(Baseball::getBaseBallNumber)
                    .extracting(BaseballNumber::getNumber)
                    .containsExactly(1, 2, 3);

            softly.assertThat(userBaseballs.getBaseballs().equals(expected)).isTrue();
        });
    }

    @Test
    @DisplayName("유효하지 않은 숫자 리스트로 UserBaseballs 객체를 생성할 때 예외를 발생시킨다")
    void generateUserBaseballsInvalidNumbersTest() {
        List<Integer> inputNumbers = List.of(1, 1, 2);

        assertThatThrownBy(() -> UserBaseballs.generateUserBaseballs(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
