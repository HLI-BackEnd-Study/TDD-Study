package com.hanwha.domain;

import com.hanwha.domain.Judge.Result;

public class Catcher {
    private Judge judge;
    private final Ball goalBall;

    public Catcher() {
        Goal goal = new Goal();
        goalBall = goal.getBall();
    }

    public Result catchBall(Ball ball) {
        judge = new Judge(goalBall);
        return judge.compareBall(ball);
    }
}
