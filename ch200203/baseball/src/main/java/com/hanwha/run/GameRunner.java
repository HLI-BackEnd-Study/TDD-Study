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

        while (true) {
            // 1. 게임 플레이
            gameService.playGame(generator);

            // 2. 게임 성공
            OutputView.gameSuccess();

            // 3. 게임종료 || 재시작
            if (askQuit()) {
                break;
            }
        }

        OutputView.end();
        System.exit(0);
    }

    private boolean askQuit() {
        return inputView.inputCommand() == GameCommand.QUIT;
    }
}
