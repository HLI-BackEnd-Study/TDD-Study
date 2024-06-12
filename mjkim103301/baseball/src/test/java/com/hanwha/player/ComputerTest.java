package com.hanwha.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ComputerTest {

    @DisplayName("컴퓨터에서 만들어낸 숫자가 3자리인지 테스트")
    @Test
    void threeLengthNumber() {
        // given
        Computer computer = new Computer();

        // when
        int length = String.valueOf(computer.getNumber()).length();

        // then
        assertEquals(3, length);
    }

    @DisplayName("컴퓨터에서 만들어낸 숫자에 0이 없는지 테스트")
    @Test
    void withoutZeroTest() {
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
        assertFalse(isInZero);
    }

    @DisplayName("컴퓨터에서 만들어낸 숫자가 모두 다른 숫자인지 테스트")
    @Test
    void differentNumberTest() {
        // given
        Computer computer = new Computer();

        // when
        String number = String.valueOf(computer.getNumber());
        boolean result = isDifferentNumber(number);

        // then
        assertTrue(result);
    }

    private boolean isDifferentNumber(String number) {
        boolean[] used = new boolean[10];
        for (char ch : number.toCharArray()) {
            int index = ch - '0';
            if (used[index]) {
                return false;
            }
            used[index] = true;
        }
        return true;
    }

}
