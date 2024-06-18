package com.hanwha.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void userInputTest() {
        User user = new User(113);
        assertThatThrownBy(user::checkValidInput)
            .isInstanceOf(IllegalArgumentException.class);
    }
}
