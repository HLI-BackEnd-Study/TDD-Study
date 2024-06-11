package com.hanwha.run;

import com.hanwha.generator.BaseballNumberGenerator;
import com.hanwha.generator.RandomBaseballNumberGenerator;
import com.hanwha.service.GameService;
import com.hanwha.view.InputView;
import com.hanwha.view.OutputView;

public class GameRunner {

    public void run() {
        BaseballNumberGenerator generator = new RandomBaseballNumberGenerator();
        GameService gameService = new GameService();
        InputView inputView = new InputView();
    }

    private void playGame() {

    }

    private void askToRestart() {

    }
}
