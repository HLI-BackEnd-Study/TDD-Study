package com.hanwha.domain;

/**
 * 랜덤 난수를 가지는 객체
 */
public class Ball {
    private final int randomBall;
    private final int firstBall;
    private final int secondBall;
    private final int thirdBall;
    public Ball(int randomBall) {

        if (randomBall < 100 || randomBall > 999) {
            throw new IllegalArgumentException("볼은 3자리 숫자만 입력할 수 없습니다.");
        }

        this.randomBall = randomBall;
        this.firstBall = randomBall / 100;
        this.secondBall = (randomBall - (firstBall * 100)) / 10;
        this.thirdBall = randomBall - (firstBall * 100) - (secondBall * 10);

        if (!isPositive()) {
            throw new IllegalArgumentException("각 자리 수는  1 ~ 9 까지만 가능합니다.");
        }

        if (!isDifference()) {
            throw new IllegalArgumentException("각 자리 수는 중복될 수 업습니다.");
        }
    }

    public int getRandomBall() {
        return randomBall;
    }

    public int getFirstBall() {
        return firstBall;
    }

    public int getSecondBall() {
        return secondBall;
    }

    public int getThirdBall() {
        return thirdBall;
    }

    /**
     * 각 자리 수가 다른지 여부
     * 모두 다르면 true
     * 하나라도 같으면 false
     * @return
     */
    public boolean isDifference() {
        return firstBall != secondBall && secondBall != thirdBall && firstBall != thirdBall;
    }

    /**
     * 각 자리 수가 양수 여부
     * @return
     */
    public boolean isPositive() {
        return firstBall > 0 && secondBall > 0 && thirdBall > 0;
    }
}
