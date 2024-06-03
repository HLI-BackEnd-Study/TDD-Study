package com.hanwha.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComputerTest {

    @Test
    makeRandomNumber() {
        // given
        Computer computer = new Computer();
        boolean isInZero = false;

        // when
        String number = computer.number();
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '0') {
                isInZero = true;
                break;
            }
        }

        // then
        assertEquals(3, computer.number.size());
        assertFalse(isInZero);
    }
}
