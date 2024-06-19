package com.hanwha.domain;

import com.hanwha.consts.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 야구 게임에 사용 하는 공 객체
 */
public class Ball {
    private final int ball;
    private final int firstBall;
    private final int secondBall;
    private final int thirdBall;
    private final List<Integer> balls = new ArrayList<>();

    public Ball(int ball) {

        if (ball < 100 || ball > 999) {
            throw new IllegalArgumentException(Message.BALL_HAS_THREE_DIGITS);
        }

        this.ball = ball;
        this.firstBall = ball / 100;
        this.secondBall = (ball - (firstBall * 100)) / 10;
        this.thirdBall = ball - (firstBall * 100) - (secondBall * 10);

        if (!isPositive()) {
            throw new IllegalArgumentException(Message.BALL_RANGE_1_TO_9);
        }

        if (!isDifference()) {
            throw new IllegalArgumentException(Message.DUPLICATION_NOT_ALLOWED);
        }

        balls.add(this.firstBall);
        balls.add(this.secondBall);
        balls.add(this.thirdBall);
    }

    public int getBall() {
        return ball;
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

    public List<Integer> getBalls() {
        return balls;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ball ball1 = (Ball) o;
        return ball == ball1.ball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ball);
    }
}
