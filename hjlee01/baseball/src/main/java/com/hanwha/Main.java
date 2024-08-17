package com.hanwha;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Game game;
    static Target target = new Target();
    static int innings = 0;
    static int newGame = 1;

    public static void main(String[] args) {

        while (newGame == 1) {
            // 게임 시작
            start();
            // 게임 진행
            game();
            // 성공 확인
            success();
            // 게임 오버
            gameOver();
        }
    }

    public static void start() {
        if (innings == 0) {
            target.randomTarget();
        }
    }

    public static void game() {
        System.out.println("숫자를 입력해 주세요 : ");

        Direction direction = new Direction(sc.nextInt());
        if (direction.isValidDiffrentBalls()) {
            game = direction.locate(target);
            System.out.println(game.strike + "S " + game.ball + "B");
        }

        innings++;
    }

    public static void success() {
        if (game.strike == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            finish();
        }
    }

    public static void gameOver() {
        if (innings == 9) {
            System.out.println("3개의 숫자를 맞히지 못했습니다. 게임 종료");
            finish();
        }
    }

    public static void finish() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        newGame = sc.nextInt();
        innings = 0;
    }
}
