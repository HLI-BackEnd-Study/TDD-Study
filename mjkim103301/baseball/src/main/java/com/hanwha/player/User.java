package com.hanwha.player;

public record User(int number) {

    public boolean isValid() {
        if (number < 100 || number > 999) {
            return false;
        }
        String temp = String.valueOf(number);
        for (char ch : temp.toCharArray()) {
            if (ch == '0') {
                return false;
            }
        }
        return true;
    }
}
