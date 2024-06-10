package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PitcherTest {
    @Test
    @DisplayName("볼을 던진다.")
    void test1() {
        Pitcher pitcher = new Pitcher();
        assertThat(pitcher.pitch(123)).isEqualTo(new Ball(123));

        assertThatThrownBy(() -> {
            pitcher.pitch(1234);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("볼은 3자리 숫자만 입력할 수 없습니다.");

        assertThatThrownBy(() -> {
            pitcher.pitch(1);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("볼은 3자리 숫자만 입력할 수 없습니다.");


        assertThatThrownBy(() -> {
            pitcher.pitch(102);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("각 자리 수는  1 ~ 9 까지만 가능합니다.");

        assertThatThrownBy(() -> {
            pitcher.pitch(112);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("각 자리 수는 중복될 수 업습니다.");
    }
}
