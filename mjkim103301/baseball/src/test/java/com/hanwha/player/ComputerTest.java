package com.hanwha.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComputerTest {

    @Test
    void makeRandomNumber() {
        // given
        Computer computer = new Computer();
        boolean isInZero = false;

        // when
        String number = String.valueOf(computer.getNumber());
        for (char ch : number.toCharArray()) {
            if (ch == '0') {
                isInZero = true;
                break;
            }
        }

        // then
        assertEquals(3, number.length());
        assertFalse(isInZero);
    }
}
