package com.hanwha.service;

import com.hanwha.domain.Baseballs;
import com.hanwha.domain.ComputerBaseballs;
import com.hanwha.domain.GameResult;
import com.hanwha.domain.UserBaseballs;

public class BaseballComparator {

    public GameResult compare(UserBaseballs userBaseballs, ComputerBaseballs computerBaseballs) {
        Baseballs userBalls = userBaseballs.getBaseballs();
        Baseballs computerBalls = computerBaseballs.getBaseballs();
        int strikes = userBalls.getStrikeCount(computerBalls);
        int balls = userBalls.getBallCount(computerBalls);

        return new GameResult(strikes, balls);
    }

}
