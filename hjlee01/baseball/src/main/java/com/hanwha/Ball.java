package com.hanwha;

import java.util.Objects;

public class Ball {
    private int strike;
    private int ball;

    public Ball(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    @Override
    public boolean equals(Object o) {
        Game game = (Game) o;
        return strike == game.strike && ball == game.ball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strike, ball);
    }
}
