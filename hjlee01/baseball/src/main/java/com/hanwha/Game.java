package com.hanwha;

public abstract class Game {
    int ball1;
    int ball2;
    int ball3;

    public Game(int ball1, int ball2, int ball3) {
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.ball3 = ball3;
    }

    int strike = 0;
    int ball = 0;

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
