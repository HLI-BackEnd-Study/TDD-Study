package com.hanwha.domain;

import com.hanwha.service.BaseBallService;

/**
 * 야구 게임의 입력 및 출력 메세징을 담당하는 게임 객체
 */
public class Game {

    /**
     * 게임을 시작
     */
    public void playBall() {
        BaseBallService baseBallService = new BaseBallService();
        baseBallService.play();
    }

    /**
     * 새로운 게임을 생성
     * @param judge
     * @param goal
     */
    public static void createNewGame(Judge judge, Goal goal) {
        judge.setGoalBall(goal.getRandomBall());
    }
}
