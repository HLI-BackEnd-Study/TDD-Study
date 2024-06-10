package com.hanwha.service;

public class Solution {


    public int getStrikeCount(String answer, String input) {
        int strikeCount = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == input.charAt(i)) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    public int getBallCount(String answer, String input) {
        int ballCount = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) != input.charAt(i) && input.indexOf(answer.charAt(i)) >= 0) {
                ballCount++;
            }
        }
        return ballCount;
    }

    public boolean isNothing(int strike, int ball) {
        return strike == 0 && ball == 0;
    }
}
