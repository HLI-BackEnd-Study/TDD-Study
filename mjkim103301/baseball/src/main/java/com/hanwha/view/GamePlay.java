package com.hanwha.view;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import com.hanwha.service.Solution;

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
        do {
            System.out.println(Message.USER_INPUT);
            int input = scanner.nextInt();
            user = new User(input);
        } while (!user.isValid());
        return String.valueOf(user.number());
    }

    public boolean judge(String answer, String input) {
        return solution.isSuccess(answer, input);
    }

    public void printScore(String answer, String input) {
        if (isSuccess) {
            System.out.println(Message.SUCCESS);
            return;
        }
        int strike = solution.getStrikeCount(answer, input);
        int ball = solution.getBallCount(answer, input);
        boolean nothing = solution.isNothing(strike, ball);
        if (nothing) {
            System.out.println(Message.NOTHING);
            return;
        }
        System.out.println(strike + Message.STRIKE + ", " + ball + Message.BALL);
    }

    public void finish() {
        System.out.println(Message.RETRY);
        int retry = scanner.nextInt();
        if (retry == 2) {
            return;
        }
        play();
    }

}
