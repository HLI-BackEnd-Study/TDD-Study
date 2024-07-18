package com.hanwha.view;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import com.hanwha.service.JudgeService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePlay {

    private Computer computer;
    private JudgeService judgeService;
    private boolean isSuccess;
    private Scanner scanner;

    public void init() {
        isSuccess = false;
        computer = new Computer();
        judgeService = new JudgeService();
        scanner = new Scanner(System.in);
    }

    public void play() {
        // 1. 설정 초기화
        init();

        // 2. 컴퓨터가 정답 수를 만들어냄
        String answer = String.valueOf(computer.getNumber());

        // 3. 성공이 발생할 때까지 while문 수행
        while (!isSuccess) {
            // 3-1. 사용자로부터 숫자 입력받기
            String input = getUserInput();

            // 3-2. 성공실패여부 판단
            isSuccess = judge(answer, input);

            // 3-3. 결과 출력
            printScore(answer, input);
        }

        // 4. 게임 종료여부 판단
        finish();
    }

    public String getUserInput() {
        User user;
        while (true) {
            try {
                MessageConstant.printMessage(MessageConstant.USER_INPUT);
                int input = scanner.nextInt();
                user = new User(input);
                user.checkValidInput();
                break;
            } catch (InputMismatchException e) {
                MessageConstant.printMessage(MessageConstant.INPUT_MISMATCH);
                scanner = new Scanner(System.in);
            } catch (IllegalArgumentException e) {
                MessageConstant.printMessage(e.getMessage());
            }
        }
        return String.valueOf(user.number());
    }

    public boolean judge(String answer, String input) {
        return judgeService.isSuccess(answer, input);
    }

    public void printScore(String answer, String input) {
        if (isSuccess) {
            MessageConstant.printMessage(MessageConstant.SUCCESS);
            return;
        }
        int strike = judgeService.getStrikeCount(answer, input);
        int ball = judgeService.getBallCount(answer, input);
        boolean nothing = judgeService.isNothing(strike, ball);
        if (nothing) {
            MessageConstant.printMessage(MessageConstant.NOTHING);
            return;
        }
        MessageConstant.printMessage(strike + MessageConstant.STRIKE + ", " + ball + MessageConstant.BALL);
    }

    public void finish() {
        while (true) {
            try {
                MessageConstant.printMessage(MessageConstant.RETRY);
                int retry = scanner.nextInt();
                handleRetry(retry);
                break;
            } catch (InputMismatchException e) {
                MessageConstant.printMessage(MessageConstant.INPUT_MISMATCH);
                scanner = new Scanner(System.in);
            } catch (Exception e) {
                MessageConstant.printMessage(e.getMessage());
            }
        }
    }

    private void handleRetry(int retry) {
        if (retry == 2) {
            System.exit(0);
        } else if (retry == 1) {
            play();
        } else {
            throw new IllegalArgumentException(String.format("%s는 알맞지 않은 명령어입니다.", retry));
        }
    }
}
