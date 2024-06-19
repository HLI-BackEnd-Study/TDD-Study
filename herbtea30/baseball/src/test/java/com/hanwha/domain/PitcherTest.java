package com.hanwha.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hanwha.consts.Message;
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
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(Message.BALL_HAS_THREE_DIGITS);

        assertThatThrownBy(() -> {
            pitcher.pitch(1);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Message.BALL_HAS_THREE_DIGITS);


        assertThatThrownBy(() -> {
            pitcher.pitch(102);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Message.BALL_RANGE_1_TO_9);

        assertThatThrownBy(() -> {
            pitcher.pitch(112);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Message.DUPLICATION_NOT_ALLOWED);
    }
}
