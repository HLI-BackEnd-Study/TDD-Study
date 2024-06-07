package com.hanwha.domain;

public class Ball {
    private final int totalBall;
    public Ball(int totalBall) {
        if (totalBall < 100 || totalBall > 999) {
            throw new IllegalArgumentException("볼은 3자리 숫자만 입력할 수 없습니다.");
        }
        this.totalBall = totalBall;
    }

    public int getTotalBall() {
        return totalBall;
    }

    public int getFirstBall() {
        return totalBall / 100;
    }

    public int getSecondBall() {
        return (totalBall - (getFirstBall() * 100)) / 10;
    }

    public int getThirdBall() {
        return totalBall - (getFirstBall() * 100) - (getSecondBall() * 10);
    }
}
