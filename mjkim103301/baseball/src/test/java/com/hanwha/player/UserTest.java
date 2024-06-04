package com.hanwha.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void userInputTest() {
        User user = new User(123);
        assertTrue(user.valid());
    }

}
