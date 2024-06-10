package com.hanwha.view;

import com.hanwha.player.Computer;
import com.hanwha.player.User;
import com.hanwha.service.Solution;

import java.util.Scanner;

public class GamePlay {
    static Computer computer;
    static User user;
    static Solution solution;
    static boolean isSuccess;
    static Scanner scanner;

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
            System.out.println(Message.USER_INPUT);
            int input = scanner.nextInt();
            user = new User(input);
            if (solution.isSuccess(answer, String.valueOf(user.number()))) {
                isSuccess = true;
                finish();
                break;
            }
            printScore(answer, String.valueOf(user.number()));
        }
    }

    public void printScore(String answer, String input) {
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
        System.out.println(Message.SUCCESS);
        System.out.println(Message.RETRY);
        int retry = scanner.nextInt();
        if (retry == 2) {
            return;
        }
        play();
    }

}
