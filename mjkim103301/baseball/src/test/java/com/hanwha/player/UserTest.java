package com.hanwha.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("잘못된 사용자 입력 테스트: 중복 숫자 존재")
    @Test
    void userInputTest() {
        User user = new User(113);
        assertThatThrownBy(user::checkValidInput)
            .isInstanceOf(IllegalArgumentException.class);
    }
}
