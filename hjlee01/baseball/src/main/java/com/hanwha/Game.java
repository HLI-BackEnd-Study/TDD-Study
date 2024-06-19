package com.hanwha;

import java.util.stream.Stream;

public abstract class Game {
    int balls;
    int ball1;
    int ball2;
    int ball3;

    public Game(int ball1, int ball2, int ball3) {
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.ball3 = ball3;
    }

    public Game(int balls) {
        this.balls = balls;

        if (isValidThreeBalls()) {
            var threeBall = Stream.of(String.valueOf(balls).split("")).mapToInt(Integer::parseInt).toArray();
            this.ball1 = threeBall[0];
            this.ball2 = threeBall[1];
            this.ball3 = threeBall[2];
        }
    }

    /**
     * 3자리 수인지 검증
     */
    public boolean isValidThreeBalls() {
        if (111 <= this.balls && this.balls <= 999) {
            return true;
        }

        return false;
    }

    /**
     * 각각 다른 수의 공인지 검증
     * @return
     */
    public boolean isValidDiffrentBalls() {
        if (this.ball1 != this.ball2 && this.ball2 != ball3 && ball1 != ball3) {
            return true;
        }

        return false;
    }

    int strike = 0;
    int ball = 0;

    /**
     * 위치 결과
     * @param o
     * @return
     */
    public Game locate(Object o) {
        Game game = (Game) o;
        if (this.ball1 == game.ball1) strike++;
        if (this.ball2 == game.ball2) strike++;
        if (this.ball3 == game.ball3) strike++;
        if (this.ball1 == game.ball2 || this.ball1 == game.ball3) ball++;
        if (this.ball2 == game.ball1 || this.ball2 == game.ball3) ball++;
        if (this.ball3 == game.ball1 || this.ball3 == game.ball2) ball++;

        return this;
    }
}
