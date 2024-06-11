package com.hanwha.domain;

import com.hanwha.domain.Judge.Result;
import java.util.Optional;

public class Catcher {
    private Judge judge;
    private final Goal goal;
    private Ball goalBall;

    public Catcher() {
        goal = new Goal();
        goalBall = Optional.ofNullable(goalBall).orElse(goal.getRandomBall());
    }

    public void setRandomBall() {
        goalBall = goal.getRandomBall();
    }

    public Result catchBall(Ball ball) {
        judge = new Judge(goalBall);
        return judge.compareBall(ball);
    }
}
