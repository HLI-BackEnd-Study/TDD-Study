package com.hanwha.run;

import com.hanwha.domain.GameCommand;
import com.hanwha.generator.BaseballNumberGenerator;
import com.hanwha.generator.RandomBaseballNumberGenerator;
import com.hanwha.service.GameService;
import com.hanwha.view.InputView;
import com.hanwha.view.OutputView;

public class GameRunner {

    private final InputView inputView = InputView.getInstance();

    public void run() {
        BaseballNumberGenerator generator = new RandomBaseballNumberGenerator();
        GameService gameService = new GameService();

        do {
            gameService.playGame(generator);
            OutputView.gameSuccess();
        } while (askToRestart());

        OutputView.end();
    }

    private boolean askToRestart() {
        return inputView.inputCommand() == GameCommand.RESTART;
    }
}
