package com.hanwha.service;

import com.hanwha.domain.ComputerBaseballs;
import com.hanwha.domain.GameResult;
import com.hanwha.domain.UserBaseballs;
import com.hanwha.generator.BaseballNumberGenerator;
import com.hanwha.view.InputView;
import com.hanwha.view.OutputView;

import java.util.List;

import static com.hanwha.utils.ParseNumbers.parseNumbers;

public class GameService {
    private final InputView inputView = InputView.getInstance();

    public void playGame(BaseballNumberGenerator generator) {
        GameResult gameResult;
        OutputView.welcomeMessage();
        // 1. 컴퓨터 난수 생성
        ComputerBaseballs computerBaseballs = ComputerBaseballs.generateComputerBaseballs(generator);
        while (true) {
            // 2. 유저 숫자 입력
            List<Integer> userInput = parseNumbers(inputView.inputNumbers());
            UserBaseballs userBaseballs = UserBaseballs.generateUserBaseballs(userInput);

            // 3. 두 수를 비교
            gameResult = new BaseballComparator().compare(userBaseballs, computerBaseballs);
            // 4. 결과 확인
            OutputView.gameResult(gameResult.toString());

            // 치트~ (로컬에서 빠른 확인을 위해...)
            cheat(computerBaseballs);

            if (gameResult.isWin()) {
                break;
            }
        }
    }

    /**
     *
     * @param computerBaseballs
     */
    private void cheat(ComputerBaseballs computerBaseballs) {
        computerBaseballs.getBaseballs().getBaseballs().forEach(System.out::println);
    }

}
