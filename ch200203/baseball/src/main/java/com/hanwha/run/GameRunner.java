package com.hanwha.run;

import com.hanwha.domain.GameCommand;
import com.hanwha.generator.BaseballNumberGenerator;
import com.hanwha.generator.RandomBaseballNumberGenerator;
import com.hanwha.service.GameService;
import com.hanwha.view.InputView;

public class GameRunner {

    private final InputView inputView = InputView.getInstance();

    public void run() {
        BaseballNumberGenerator generator = new RandomBaseballNumberGenerator();
        GameService gameService = new GameService();

        do {
            gameService.playGame(generator);
        } while (askToRestart());

    }

    private boolean askToRestart() {
        return inputView.inputCommand() == GameCommand.RESTART;
    }
}
