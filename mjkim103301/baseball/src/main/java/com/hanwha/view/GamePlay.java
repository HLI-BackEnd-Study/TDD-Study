package com.hanwha.view;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import com.hanwha.service.Solution;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePlay {

    private Computer computer;
    private Solution solution;
    private boolean isSuccess;
    private Scanner scanner;

    public void init() {
        isSuccess = false;
        computer = new Computer();
        solution = new Solution();
        scanner = new Scanner(System.in);
    }

    public void play() {
        init();
        String answer = String.valueOf(computer.getNumber());
        while (!isSuccess) {
            String input = getUserInput();
            isSuccess = judge(answer, input);
            printScore(answer, input);
        }
        finish();
    }

    public String getUserInput() {
        User user;
        while (true) {
            try {
                println(MessageConstant.USER_INPUT);
                int input = scanner.nextInt();
                user = new User(input);
                user.checkValidInput();
                break;
            } catch (InputMismatchException e) {
                println(MessageConstant.INPUT_MISMATCH);
                scanner = new Scanner(System.in);
            } catch (IllegalArgumentException e) {
                println(e.getMessage());
            }
        }
        return String.valueOf(user.number());
    }

    public boolean judge(String answer, String input) {
        return solution.isSuccess(answer, input);
    }

    public void printScore(String answer, String input) {
        if (isSuccess) {
            println(MessageConstant.SUCCESS);
            return;
        }
        int strike = solution.getStrikeCount(answer, input);
        int ball = solution.getBallCount(answer, input);
        boolean nothing = solution.isNothing(strike, ball);
        if (nothing) {
            println(MessageConstant.NOTHING);
            return;
        }
        println(strike + MessageConstant.STRIKE + ", " + ball + MessageConstant.BALL);
    }

    public void finish() {
        while (true) {
            try {
                println(MessageConstant.RETRY);
                int retry = scanner.nextInt();
                handleRetry(retry);
                break;
            } catch (InputMismatchException e) {
                println(MessageConstant.INPUT_MISMATCH);
                scanner = new Scanner(System.in);
            } catch (Exception e) {
                println(e.getMessage());
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

    private void println(String message) {
        System.out.println(message);
    }

}
