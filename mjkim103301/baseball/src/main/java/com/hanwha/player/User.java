package com.hanwha.player;

public record User(int number) {

    public boolean isValid() {
        String input = String.valueOf(number);
        if (input.length() != 3) {
            return false;
        }

        boolean[] used = new boolean[10];
        for (char ch : input.toCharArray()) {
            int index = ch - '0';
            if (index == 0 || used[index]) {
                return false;
            }
            used[index] = true;
        }
        return true;
    }
}
